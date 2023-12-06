import environment from "./environment";


const endpoint = {
    url: (): string => {
        return environment.URL_API + environment.URL_API_PREFIX;
    }
}

export default endpoint;
