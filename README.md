# CULQI3DS-DEMO-CHECKOUT

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



### Documentación de servicios

Para poder visualizar la documentación de los servicios/modelos ingresar a la siguiente URL: 

- `http://localhost:8080/swagger-ui.html`

### Demo Checkout + Culqi3DS

Para poder visualizar el formulario de pago de la demo ingresar a la siguiente URL:

- `http://localhost:8080`


## Inicializar del proyecto
Abrir la terminal (Bash/CMD) y ubicarse dentro del proyecto para ejecutar los siguientes comandos.

```bash
mvn clean package
mvn spring-boot:run
```

