const env = process.env;

export type Env = {
    URL_API: string;
    URL_API_PREFIX: string;
}

const environment: Env = {
    URL_API: env.REACT_APP_URL_API || "",
    URL_API_PREFIX: env.REACT_APP_URL_API_PREFIX || "/api/v1"
}

export default environment;