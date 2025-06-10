package br.com.alura.cepfinder.services;

import br.com.alura.cepfinder.models.Address;
import br.com.alura.cepfinder.models.AddressViaCep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViacepRequestService {
    private HttpClient client;
    private Gson gson;

    public ViacepRequestService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Address requestViaCEP(String zip) throws IOException, InterruptedException {
        String url = "https://viacep.com.br/ws/" + zip.replace("-", "") + "/json/";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 400) {
            throw new BadRequestException("Cep digitado errado!");
        }
        AddressViaCep address = gson.fromJson(response.body(), AddressViaCep.class);
        if (address.cep() == null) {
            throw new BadRequestException("Cep não encontrado!");
        }
        return new Address(address);
    }
}
