export interface FetchResponse<T> {
    status: number;
    body: T;
}

export const mockFetchWith = <T>(configs: Map<string, FetchResponse<T>>) => {
    global.fetch = jest.fn().mockImplementation((uri, options) => Promise.resolve({
        status: configs.get(uri)!.status,
        json: () => Promise.resolve<T | undefined>(configs.get(uri)!.body)
    }));
}