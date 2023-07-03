import config, { customerInfo } from "./config/index.js";
import culqiConfig from "./config/checkout.js";
import "./config/culqi3ds.js";
import {
  generateChargeImpl,
  createCustomerImpl,
  createCardImpl,
  generateOrderImpl,
} from "./services/impl/index.js";
import * as selectors from "./helpers/selectors.js";

const { statusCode, data } = await generateOrderImpl();
let jsonParams = {
  installments: paymenType === "cargo" ? true : false,
  orderId: paymenType === "cargo" ? await generarOrder() : '',
}

async function generarOrder(){
  const { statusCode, data } = await generateOrderImpl();
  if (statusCode === 200) {
    console.log("Order",data);
    return data.id;
  } else {
    console.log('No se pudo obtener la orden');
  }
  return '';
}
/*
if (statusCode === 200) {
  jsonParams.orderId = data.id;
} else {
  console.log('No se pudo obtener la orden');
}
console.log("Order",data);
*/
culqiConfig(jsonParams);


const deviceId = await Culqi3DS.generateDevice();
if (!deviceId) {
  console.log("Ocurrio un error al generar el deviceID");
}

let tokenId,
  email,
  customerId = null;
window.addEventListener(
  "message",
  async function (event) {
    if (event.origin === window.location.origin) {
      const { parameters3DS, error } = event.data;

      if (parameters3DS) {
        let statusCode = null;
        let objRespone = null;
        if (paymenType === "cargo") {
          const responseCharge = await generateChargeImpl({
            deviceId,
            email,
            tokenId,
            parameters3DS,
          }); //2da llamada a cargo
          objRespone = responseCharge.data.object;
          statusCode = responseCharge.statusCode;
        } else {
          const responseCard = await createCardImpl({
            customerId,
            tokenId,
            deviceId,
            parameters3DS,
          }); //2da llamada a creacion de CARD, validacion de 1 sol
          objRespone = responseCard.data.object;
          statusCode = responseCard.statusCode;
        }

        if (statusCode === 200) {
          if (objRespone == "charge") {
            $("#response_card").text("COMPRA REALIZADA EXITOSAMENTE");
          } else {
            $("#response_card").text("TARJETA CREADA EXITOSAMENTE");
          }
          selectors.loadingElement.style.display = "none";
          Culqi3DS.reset();
        } else {
          selectors.paymentFailElement.style.display = "block";
          Culqi3DS.reset();
        }
      }

      if (error) {
        console.log("Ocurrio un error", error);
        selectors.loadingElement.style.display = "none";
      }
    }
  },
  false
);

window.culqi = async () => {
  console.log(paymenType);
  if (Culqi.token) {
    Culqi.close();
    tokenId = Culqi.token.id;
    email = Culqi.token.email;
    selectors.loadingElement.style.display = "block";

    if (paymenType === "cargo") {
      console.log("pagosss");
      const { statusCode, data } = await generateChargeImpl({
        deviceId,
        email,
        tokenId,
      }); //1ra llamada a cargo
      if (statusCode === 200) {
		if(data.action_code === "REVIEW"){
			validationInit3DS({ email, statusCode, tokenId });
		}else{
			$("#response_card").text("ERROR AL REALIZAR EL CARGO");
		}
	   } else if (statusCode === 201) {
			$("#response_card").text("CARGO EXITOSO - SIN 3DS");
	      	Culqi3DS.reset();
       } else {
	      $("#response_card").text("CARGO FALLIDO - SIN 3DS");
	      	Culqi3DS.reset();
	   }
    } else {
      customerId = selectors.customerCustomFormElement.value;
      const { statusCode, data } = await createCardImpl({ customerId, tokenId }); // 1ra llamada a creacion de CARDS

      if (statusCode === 200) {
		if(data.action_code === "REVIEW"){
			validationInit3DS({ email, statusCode, tokenId });
		}else{
			$("#response_card").text("ERROR AL REALIZAR LA CREACION DE TARJETA");
		}
	  } else if (statusCode === 201) {
			$("#response_card").text("TAJETA EXITOSA - SIN 3DS");
	      	Culqi3DS.reset();
       } else {
	      $("#response_card").text("TARJETA FALLIDA - SIN 3DS");
	      	Culqi3DS.reset();
	   }
    }
  } else {
    console.log(Culqi.error);
    alert(Culqi.error.user_message);
  }
};

const validationInit3DS = ({ statusCode, email, tokenId }) => {
  if (statusCode === 200) {
    Culqi3DS.settings = {
      charge: {
        totalAmount: config.TOTAL_AMOUNT,
        returnUrl: config.URL_BASE,
      },
      card: {
        email: email,
      },
    };
    Culqi3DS.initAuthentication(tokenId);
  } else if (statusCode === 201) {
    selectors.loadingElement.style.display = "none";
    Culqi3DS.reset();
  } else {
    selectors.loadingElement.style.display = "none";
    Culqi3DS.reset();
  }
};

async function createCustomer() {
  const dataCustomer = await createCustomerImpl({
    ...customerInfo,
  });
  $("#response_customer").text(dataCustomer.data.id);
  console.log(dataCustomer);
}

$("#loadCustomerExampleData").click(function () {
  selectors.customersNameElement.value = customerInfo.firstName;
  selectors.customersLastNameElement.value = customerInfo.lastName;
  selectors.customersEmailElement.value = customerInfo.email;
  selectors.customersAddressElement.value = customerInfo.address;
  selectors.customersPhoneElement.value = customerInfo.phone;
  selectors.customersAddressCityElement.value = customerInfo.address_c;
});

$("#crearCustomer").click(function () {
  customerInfo.firstName = selectors.customersNameElement.value;
  customerInfo.lastName = selectors.customersLastNameElement.value;
  customerInfo.email = selectors.customersEmailElement.value;
  customerInfo.address = selectors.customersAddressElement.value;
  customerInfo.phone = selectors.customersPhoneElement.value;
  customerInfo.address_c = selectors.customersAddressCityElement.value;
  createCustomer();
});

$("#crearCard").click(function () {
  Culqi.open();
});
