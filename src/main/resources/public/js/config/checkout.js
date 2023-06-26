import config from "./index.js";

Culqi.publicKey = config.PUBLIC_KEY;

xculqirsaid: '2ab335ad-c40d-4375-8dad-3ea315de23b0';
rsapublickey: '-----BEGIN PUBLIC KEY-----'+
'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9hD00BnivDj73/1SKZw5AyQvw'+
'FpvR/DKzW7Jqg1iwFWXrX6k1r57qZJH2wF1tZ9T3wTyw1we6BYgwPNRVC1IXe+E8'+
'B6xAWG8ta7BCZK/a6IFL+l9Q9BhkHBeVTD7qGEfCjhnB7QtyrTQwmytoNBKk1Tl7'+
'kbz8NO7jeiUxkZm75wIDAQAB'+
'-----END PUBLIC KEY-----';

excludencryptoperations: [''];

Culqi.settings({
  title: "Culqi 3DS TEST",
  currency: config.CURRENCY,
  description: "Polo/remera Culqi lover",
  amount: config.TOTAL_AMOUNT,
});

Culqi.options({
  lang: "auto",
  installments: true,
  paymentMethods: {
    tarjeta: true,
    bancaMovil: false,
    agente: false,
    billetera: false,
    cuotealo: false,
    yape: false,
  },
  style: {
    //logo: 'https://culqi.com/LogoCulqi.png',
    bannerColor: "", // hexadecimal
    buttonBackground: "", // hexadecimal
    menuColor: "", // hexadecimal
    linksColor: "", // hexadecimal
    buttonText: "", // texto que tomará el botón
    buttonTextColor: "", // hexadecimal
    priceColor: "", // hexadecimal
  },
});
