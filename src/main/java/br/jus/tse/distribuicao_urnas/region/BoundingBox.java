package br.jus.tse.distribuicao_urnas.region;

import java.util.Objects;

import br.jus.tse.distribuicao_urnas.domain.Localizacao;

/**
 * Bounding box.
 */
public class BoundingBox {

    private final Localizacao southWest;
    private final Localizacao northEast;

    /**
     * Create bounding box. The box must have non-zero dimensions and the corners must be south-west and north-east.
     *
     * @param southWest south-west corner (minimal latitude and longitude)
     * @param northEast north-east corner (maximal latitude and longitude)
     */
    public BoundingBox(Localizacao southWest, Localizacao northEast) {
        this.southWest = Objects.requireNonNull(southWest);
        this.northEast = Objects.requireNonNull(northEast);
        if (southWest.getLatitude().compareTo(northEast.getLatitude()) >= 0) {
            throw new IllegalArgumentException(
                    "South-west corner latitude ("
                            + southWest.getLatitude()
                            + "N) must be less than north-east corner latitude ("
                            + northEast.getLatitude()
                            + "N)");
        }
        if (southWest.getLongitude().compareTo(northEast.getLongitude()) >= 0) {
            throw new IllegalArgumentException(
                    "South-west corner longitude ("
                            + southWest.getLongitude()
                            + "E) must be less than north-east corner longitude ("
                            + northEast.getLongitude()
                            + "E)");
        }
    }

    /**
     * South-west corner of the bounding box.
     *
     * @return south-west corner (minimal latitude and longitude)
     */
    public Localizacao getSouthWest() {
        return southWest;
    }

    /**
     * North-east corner of the bounding box.
     *
     * @return north-east corner (maximal latitude and longitude)
     */
    public Localizacao getNorthEast() {
        return northEast;
    }
}
