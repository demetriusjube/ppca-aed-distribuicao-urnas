# DistribuicaoUrnas

This app was created with Bootify.io - more documentation [can be found here](https://bootify.io/docs/). Feel free to contact us for further questions.

## Rodando o jar do SpringBoot

Se já tiver acesso ao jar executável do Spring Boot, faça o seguinte:

- Copie o conteúdo da pasta `distribuicao-urnas-files` para o diretório padrão do usuário (Geralmente C:\Users\<login usuário>)
- Rode o seguinte comando na pasta onde o jar está (É necessário ter o Java 17 instalado e  no path): `java -jar ppca-distribuicao-urnas-exec.jar`
- A aplicação vai subir um servidor que estará monitorando a porta 8080, e pode ser acessada com `http://localhost:8080` 



## Prerequisites

* Java 17 or later - https://jdk.java.net/18/
* Node - https://nodejs.org/en/download/
* Maven - https://maven.apache.org/download.cgi


## Development

During development it is recommended to use the profile `local`. In IntelliJ, `-Dspring.profiles.active=local` can be added in the VM options of the Run Configuration after enabling this property in "Modify options".

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For this, in IntelliJ install the Lombok plugin and enable annotation processing - [learn more](https://bootify.io/intellij/spring-boot-with-lombok.html).

In addition to the Spring Boot application, the DevServer must also be started. [Node.js](https://nodejs.org/) has to be available on the system - the latest LTS version is recommended. Only once the dependencies have to be installed:

```
npm install
```

The DevServer can now be started as follows:

```
npm run devserver
```

Using a proxy the whole application is now accessible under `localhost:8081`. All changes to the templates and JS/CSS files are immediately visible in the browser.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Node.js is automatically downloaded using the `frontend-maven-plugin` and the final JS/CSS files are integrated into the jar.

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/ppca-distribuicao-urnas.jar
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
* [Thymeleaf docs](https://www.thymeleaf.org/documentation.html)  
* [Webpack concepts](https://webpack.js.org/concepts/)  
* [npm docs](https://docs.npmjs.com/)  
* [Bootstrap docs](https://getbootstrap.com/docs/5.2/getting-started/introduction/)  
