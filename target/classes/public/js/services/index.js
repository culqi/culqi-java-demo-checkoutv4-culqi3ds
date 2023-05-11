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

  generateCharge = async (bodyCharges) => {
    return this.#http({ endPoint: "generateCharge", body: bodyCharges });
  };

  createCustomer = async (bodyCustomers) => {
    return this.#http({ endPoint: "createCustomer", body: bodyCustomers });
  };

  createCard = async (bodyCard) => {
    return this.#http({ endPoint: "createCard", body: bodyCard });
  };
}

export default Service;
