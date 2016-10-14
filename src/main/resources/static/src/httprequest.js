import { HttpClient, json } from 'aurelia-fetch-client';

export class HttpRequest {

    constructor() {
        this.url = "";
        this.response = null;
        this.client = new HttpClient()
            .configure(config => {
                config
                    .useStandardConfiguration()
                    .withBaseUrl('http://localhost:8080/api/')
            });
    }

    get(path) {
        let response = {tekst: "rob"};
        this.client.fetch(path)
            .then(response => response.json())
            .then(data => {
                response.tekst = data;
            });
        return response;
    }

    getWithParams(path, pathParams) {
        return this.get(path + "/" + pathParams);
    }

    post(path, bodyJson) {
        this.client.fetch(path, {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bodyJson)
        });
    }

    delete(path, pathParams) {
        this.client.fetch(path+ "/" + pathParams, {
            method: 'delete'
        });
    }

    put(path, pathParams, bodyJson) {
        this.client.fetch(path + "/" + pathParams, {
            method: 'put',
            body: bodyJson,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bodyJson)
        });
    }
}
