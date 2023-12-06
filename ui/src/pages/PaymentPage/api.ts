import endpoint from "../../endpoint";

const apiBaseUrl = endpoint.url();


type PaymentResponse = {
    uuid: string;
}

const createNewPayment = async () => {
    const response = await fetch(`${apiBaseUrl}/payments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({}),
    });
    return await response.json() as PaymentResponse;
}

const api = {
    createNewPayment
}

export default api;


