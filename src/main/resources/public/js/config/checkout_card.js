import config from "./index.js";

Culqi.publicKey = config.PUBLIC_KEY;
Culqi.settings({
  title: "Culqi 3DS TEST",
  currency: config.CURRENCY,
  description: "Polo/remera Culqi lover",
  amount: config.TOTAL_AMOUNT,
});

Culqi.options({
  lang: "auto",
  installments: false,
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
