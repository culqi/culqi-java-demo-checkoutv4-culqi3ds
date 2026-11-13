# DEMO - Culqi Java + Checkout V4 + Culqi 3DS

La demo integra Culqi Java, Checkout V4 , Culqi 3DS y es compatible con la v2.0 del Culqi API, con esta demo podrás generar tokens, cargos, clientes, cards.

## Requisitos

* Java 1.8+
* Afiliate [aquí](https://afiliate.culqi.com/).
* Para probar esta demo, obtén tus llaves de prueba desde [aquí](https://integ-panel.culqi.com/#/registro).

> Recuerda que para obtener tus llaves debes ingresar a tu CulqiPanel > Desarrollo > ***API Keys***.

![alt tag](http://i.imgur.com/NhE6mS9.png)

> Recuerda que las credenciales son enviadas al correo que registraste en el proceso de afiliación.

* Para encriptar el payload debes generar un id y llave RSA ingresando a CulqiPanel > Desarrollo > RSA Keys.

## Configurar las pruebas y ejemplos

Usa tus propias llaves de prueba `pk_test_` y `sk_test_`, junto con tu RSA ID y llave pública RSA. Reemplaza los placeholders `INSERTE_SU_LLAVE_PUBLICA`, `INSERTE_SU_LLAVE_PRIVADA`, `INSERTE_SU_LLAVE_RSA` e `INSERTE_SU_RSA_ID` en los archivos de configuración. No hagas commit de llaves. Los ejemplos pueden realizar llamadas reales a la API de Culqi.

## Instalación

Agregar la siguientes configuraciones al archivo pom.xml del proyecto 

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

```xml
<dependency>
	<groupId>com.github.culqi</groupId>
	<artifactId>culqi-java</artifactId>
	<version>2.0.2</version>
</dependency>
```

## Configuración backend

Primero se tiene que modificar los valores del archivo `application.properties` que se encuentra en `src/main/resources/`. A continuación un ejemplo.

```
app.culqi_api.public-api.url: Url base de la api CULQI (https://api.culqi.com)
app.culqi.public-key: Llave pública del comercio (INSERTE_SU_LLAVE_PUBLICA)
app.culqi.secret-key: Llave secreta del comercio (INSERTE_SU_LLAVE_PRIVADA)
app.culqi.rsa-id: INSERTE_SU_RSA_ID de la llave RSA
app.culqi.rsa-public-key: INSERTE_SU_LLAVE_RSA
```
## Configuración frontend

Para configurar los datos del cargo, llave pública, RSA ID, llave pública RSA y datos del cliente se tiene que modificar en el archivo `src/main/resources/public/js/config/index.js`.

```js
export default Object.freeze({
    TOTAL_AMOUNT: monto de pago,
    CURRENCY: tipo de moneda,
    PUBLIC_KEY: INSERTE_SU_LLAVE_PUBLICA,
    RSA_ID: INSERTE_SU_RSA_ID,
    RSA_PUBLIC_KEY: INSERTE_SU_LLAVE_RSA,
    COUNTRY_CODE: iso code del país(Ejemplo PE)
});

export const customerInfo = {
    firstName: "Fernando",
    lastName: "Chullo",
    address: "Coop. Villa el Sol",
    phone: "945737476",
}
```

## Inicializar la demo
Abrir la terminal (Bash/CMD) y ubicarse dentro del proyecto para ejecutar los siguientes comandos.

```bash
mvn clean package
mvn spring-boot:run
```

## Probar la demo

Para poder visualizar el frontend de la demo ingresar a la siguiente URL:

- Para probar cargos: `http://localhost:8080`
- Para probar creación de cards: `http://localhost:8080/index_card.html`


## Documentación

- [Referencia de Documentación](https://docs.culqi.com/)
- [Referencia de API](https://apidocs.culqi.com/)
