package br.com.unifor.sistemasdistribuidos;

import java.util.Scanner;

public class Application {
  public static void main(String... args) {
    try {
      Servidor servidor = new Servidor();
      new Thread(servidor).start();

      System.out.print("Digite o ip do cliente: ");
      Scanner s = new Scanner(System.in);
      String entrada = s.nextLine();
      if (entrada.contains(":")) {
        String ip = entrada.split(":")[0];
        String porta = entrada.split(":")[1];

        if (!new ExisteIP().ping(ip)) {
          System.out.println("Não foi possível estabelecer uma conexão com este ip!");
        } else {
          new Thread(new Cliente(ip, porta)).start();
        }
      } else {
        System.out.println("Informar porta!!!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}