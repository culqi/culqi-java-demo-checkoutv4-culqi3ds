# DEMO - Culqi Java + Checkout V4 + Culqi 3DS

La demo integra Culqi Java, Checkout V4 , Culqi 3DS y es compatible con la v2.0 del Culqi API, con esta demo podrás generar token, cargos, clientes, card.

## Requisitos

* Java 1.8+
* Afiliate [aquí](https://afiliate.culqi.com/).
* Si vas a realizar pruebas obtén tus llaves desde [aquí](https://integ-panel.culqi.com/#/registro), si vas a realizar transacciones reales obtén tus llaves desde [aquí](https://panel.culqi.com/#/registro) (1).

> Recuerda que para obtener tus llaves debes ingresar a tu CulqiPanel > Desarrollo > ***API Keys***.

![alt tag](http://i.imgur.com/NhE6mS9.png)

> Recuerda que las credenciales son enviadas al correo que registraste en el proceso de afiliación.

## Configuración

Primero se tiene que modificar los valores del archivo `application.properties` que se encuentra en `src/resource/`. A continuación un ejemplo.

```
app.culqi_api.public-api.url: BASE URL DE API DE CULQI (https://dev-api.culqi.xyz)
app.culqi.secret-key: LLAVE SECRETA DEL COMERCIO (sk_test_xxxxxxxxx)
```
### Datos del cliente y creación de token

Para configurar los datos del cargo, pk del comercio y datos del cliente se tiene que modificar en el archivo `src/resources/public/js/config/index.js`.

```js
export default Object.freeze({
    TOTAL_AMOUNT: monto de pago,
    CURRENCY: tipo de moneda,
    PUBLIC_KEY: llave publica del comercio (pk_test_xxxxx),
    COUNTRY_CODE: iso code del país
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


### Documentación de servicios

Para poder visualizar la documentación de los servicios/modelos ingresar a la siguiente URL: 

- `http://localhost:8080/swagger-ui.html`
