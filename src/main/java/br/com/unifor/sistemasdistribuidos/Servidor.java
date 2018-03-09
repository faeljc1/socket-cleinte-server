package br.com.unifor.sistemasdistribuidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
  private String mensage;
  private String capitalized;

  public Servidor() {
    System.out.println("SERVIDOR EM EXECUCAO!!!");
  }

  @Override public void run() {
    try {
      ServerSocket serverSocket = new ServerSocket(5000);

      while (true) {
        Socket socket = serverSocket.accept();

        // Cria uma buffer que irá armazenar as informações enviadas pelo cliente
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Cria uma stream de sáida para retorno das informações ao cliente
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

        // Faz a leitura das informações enviadas pelo cliente as amazenam na variável "clientSentence"
        mensage = inFromClient.readLine();

               /* Faz uma modificação na String enviada pelo cliente, simulando um processamento em "back-end"
                * antes de retorná-la ao cliente
                */
        capitalized = mensage.toUpperCase() + "\n";

        // Imprime a a String modificada no console do servidor
        System.out.println(capitalized);

        // Retorna as informações modificadas, ao cliente
        System.out.println("Responda:");
        outToClient.writeBytes(capitalized);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}