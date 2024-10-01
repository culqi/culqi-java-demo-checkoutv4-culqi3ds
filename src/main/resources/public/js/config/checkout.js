import config from "./index.js";

const culqiConfig = (jsonParams) => {
	
	Culqi.publicKey = config.PUBLIC_KEY;
	let config = {
		title: "Culqi 3DS TEST",
		order: jsonParams.orderId,
		currency: config.CURRENCY,
		description: "Polo/remera Culqi lover",
		amount: jsonParams.amount,
		xculqirsaid: config.RSA_ID,
		rsapublickey: config.RSA_PUBLIC_KEY,
		//excludencryptoperations: [''],
	}

	if(config.ACTIVE_ENCRYPT){
		config.xculqirsaid = config.RSA_ID;
		config.rsapublickey =  config.RSA_PUBLIC_KEY;
	}
	
	Culqi.settings(config);
	
	Culqi.options({
	  lang: "auto",
	  installments: jsonParams.installments,
	  paymentMethods: {
	    tarjeta: true,
	    bancaMovil: true,
	    agente: true,
	    billetera: true,
	    cuotealo: true,
	    yape: true,
	  },
	  style: {
	    //logo: 'https://culqi.com/LogoCulqi.png',
	    bannerColor: "", // hexadecimal
	    buttonBackground: "", // hexadecimal
	    menuColor: "", // hexadecimal
	    linksColor: "", // hexadecimal
	    buttonText: jsonParams.buttonTex, // texto que tomará el botón
	    buttonTextColor: "", // hexadecimal
	    priceColor: "", // hexadecimal
	  },
	});
}

export default culqiConfig;