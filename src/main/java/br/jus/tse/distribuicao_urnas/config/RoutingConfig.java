package br.jus.tse.distribuicao_urnas.config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FlagEncoderFactory;

/**
 * Configuration bean that creates a GraphHopper instance and allows to
 * configure the path to OSM file through environment.
 */
@Component
class RoutingConfig {

	private static final Logger logger = LoggerFactory.getLogger(RoutingConfig.class);

//	@Value("app.routing.osm-dir")
	private final Path osmDir;
//	@Value("app.routing.osm-file")
	private final Path osmFile;
//	@Value("app.routing.osm-download-url")
//	private final Optional<String> osmDownloadUrl;
//	@Value("app.routing.gh-dir")
	private final Path graphHopperDir;
	private final Path graphDir;

	@Autowired
	RoutingConfig(@Value("${app.routing.osm-dir}") String osmDir, @Value("${app.routing.osm-file}") String osmFile,
			@Value("${app.routing.gh-dir}") String graphHopperDir) {
		this.osmDir = Paths.get(osmDir).toAbsolutePath();
		this.osmFile = this.osmDir.resolve(osmFile).toAbsolutePath();
//		this.osmDownloadUrl = osmDownloadUrl;
		this.graphHopperDir = Paths.get(graphHopperDir);
		String regionName = osmFile.replaceFirst("\\.osm\\.pbf$", "");
		this.graphDir = this.graphHopperDir.resolve(regionName).toAbsolutePath();
	}

	/**
	 * Avoids creating a real GraphHopper instance when running a @QuarkusTest.
	 *
	 * @return real GraphHopper
	 */
	@Profile("!test")
	@Bean
	GraphHopperOSM graphHopper() {
		GraphHopperOSM graphHopper = ((GraphHopperOSM) new GraphHopperOSM().forServer());
		graphHopper.setGraphHopperLocation(graphDir.toString());

		if (graphDirIsNotEmpty()) {
			logger.info("Loading existing GraphHopper graph from: {}", graphDir);
		} else {
			if (Files.notExists(osmFile)) {
				initDirs();

//				if (!osmDownloadUrl.isPresent() || osmDownloadUrl.get().trim().isEmpty()) {
//					throw new IllegalStateException("The osmFile (" + osmFile + ") does not exist"
//							+ " and no download URL was provided.\n"
//							+ "Download the OSM file from https://download.geofabrik.de/ first"
//							+ " or provide an OSM file URL" + " using the app.routing.osm-download-url property.");
//				}
//				downloadOsmFile(osmDownloadUrl.get(), osmFile);
			}
			logger.info("Importing OSM file: {}", osmFile);
			graphHopper.setOSMFile(osmFile.toString());
		}

		graphHopper.setEncodingManager(EncodingManager.create(FlagEncoderFactory.CAR));
		graphHopper.importOrLoad();
		logger.info("GraphHopper graph loaded");
		return graphHopper;
	}

	/**
	 * Decide whether the graph can be loaded.
	 *
	 * @return true if the graph directory exists and is not empty
	 */
	private boolean graphDirIsNotEmpty() {
		if (Files.notExists(graphDir)) {
			return false;
		}
		try (Stream<Path> graphDirFiles = Files.list(graphDir)) {
			// Defensive programming. Check if the graph dir is empty. That happens if the
			// import fails
			// for example due to OutOfMemoryError.
			return graphDirFiles.findAny().isPresent();
		} catch (IOException e) {
			throw new RoutingEngineException("Cannot read contents of the graph directory (" + graphDir + ")", e);
		}
	}

	private void initDirs() {
		try {
			Files.createDirectories(osmDir);
			Files.createDirectories(graphHopperDir);
		} catch (IOException e) {
			throw new RoutingEngineException("Can't create directory for storing OSM download", e);
		}
	}

	static void downloadOsmFile(String urlString, Path osmFile) {
		HttpURLConnection con;
		URL url;
		try {
			url = new URL(urlString);
			con = (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RoutingEngineException("The OSM file URL is malformed", e);
		} catch (IOException e) {
			throw new RoutingEngineException("The OSM file cannot be downloaded", e);
		}
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			throw new IllegalStateException("Can't set request method", e);
		}

		con.setConnectTimeout(10000);
		con.setReadTimeout(10000);

		logger.info("Downloading OSM file from {}", urlString);
		try {
			Files.copy(con.getInputStream(), osmFile);
		} catch (IOException e) {
			throw new RoutingEngineException("OSM file download failed", e);
		}
		logger.info("File saved to {}", osmFile);
	}
}
