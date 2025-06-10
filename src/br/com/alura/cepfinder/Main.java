package br.com.alura.cepfinder;

import br.com.alura.cepfinder.models.Address;
import br.com.alura.cepfinder.services.BadRequestException;
import br.com.alura.cepfinder.services.ViacepRequestService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ViacepRequestService viacep = new ViacepRequestService();
        Scanner sc = new Scanner(System.in);
        String res;
        List<Address> addresses = new ArrayList<>();
        System.out.println("Bem vindo ao CEP-Finder");
        System.out.print("Digite o cep que você deseja buscar (ou sair para sair): ");
        res = sc.nextLine();

        while (!res.contains("sair")) {
            try {
                Address address = viacep.requestViaCEP(res);
                addresses.add(address);
                System.out.println(address);
            } catch (BadRequestException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Ocorreu um erro ao buscar o CEP, verifique o cep informado.");
            }
            catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado. Tente novamente.");
                System.out.println(e);
            }
            System.out.print("Digite o cep que você deseja buscar (ou sair para sair): ");
            res = sc.nextLine();
        }

        System.out.println("Cep's adicionados: ");
        System.out.print("[   ");
        for (Address address : addresses) {
            System.out.print(address.getZip() + "   ");
        }
        System.out.println("]");


    }
}