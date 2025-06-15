package br.com.alura.cepfinder.services;

import br.com.alura.cepfinder.models.Address;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGeneratorService {
    File file;
    Gson gson;

    public FileGeneratorService(String path) {
        file = new File(path);
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create(); // Transforma as keys em upperCamelCase
    }

    public void saveAddresses(List<Address> addresses){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(gson.toJson(addresses));
            fw.close();
            System.out.println("Endereços salvos!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os endereços, tente novamente mais tarde.");
        }

    }
}
