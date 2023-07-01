class Service {
  #BASE_URL = "http://localhost:8080/culqi";

  #http = async ({ endPoint, method = "POST", body = {}, headers = {} }) => {
    try {
      const response = await fetch(`${this.#BASE_URL}/${endPoint}`, {
        headers: { "Content-Type": "application/json", ...headers },
        body: JSON.stringify(body),
        method,
      });
      const responseJSON = await response.json();
      return { statusCode: response.status, data: responseJSON };
    } catch (err) {
      return { statusCode: 502, data: err };
    }
  };
  
  #http2 = async ({ endPoint, method = "POST", body = {}, headers = {} }) => {
    const response = await $.ajax({
      type: 'POST',
      url: `${this.#BASE_URL}/${endPoint}`,
      headers: { "Content-Type": "application/json", ...headers },
      data: JSON.stringify(body),
    });
    const responseJSON = await response;
    if (responseJSON.object === "order") {
      return { statusCode: 200, data: responseJSON }
    } else {
      return { statusCode: 401, data: responseJSON }
    }
  }

  createOrder = async (bodyOrder) => {
    return this.#http2({ endPoint: "generateOrder", body: bodyOrder });
  }
  
  generateCharge = async (bodyCharges) => {
    return this.#http({ endPoint: "generateCharge", body: bodyCharges });
  };

  generateChargeEncrypt = async (bodyCharges) => {
    return this.#http({ endPoint: "generateChargeEncrypt", body: bodyCharges });
  };

  createCustomer = async (bodyCustomers) => {
    return this.#http({ endPoint: "createCustomer", body: bodyCustomers });
  };

  createCard = async (bodyCard) => {
    return this.#http({ endPoint: "createCard", body: bodyCard });
  };
}

export default Service;
