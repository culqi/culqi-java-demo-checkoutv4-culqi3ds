import config from "./index.js";

Culqi.publicKey = config.PUBLIC_KEY;

xculqirsaid: '2ab335ad-c40d-4375-8dad-3ea315de23b0';
rsapublickey: "INSERTE_SU_LLAVE_RSA";

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
