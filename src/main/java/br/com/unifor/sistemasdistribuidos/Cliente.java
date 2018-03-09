package br.com.unifor.sistemasdistribuidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente implements Runnable {

  private String sentence;
  private String modifiedSentence;
  private String ip;
  private Integer porta;

  public Cliente(String ip, String porta) {
    this.ip = ip;
    this.porta = Integer.parseInt(porta);
    System.out.println("CLIENTE EM EXECUCAO!!!");
  }

  @Override public void run() {
    try {
      // Cria um buffer que armazenará as informações de entrada do teclado
      BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));

      // Cria um Socket cliente passando como parâmetro o ip e a porta do servidor
      Socket client = new Socket(ip, porta);

      // Cria um stream de saída
      DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());

      // Cria um buffer que armazenará as informações retornadas pelo servidor
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

      // Atribui as informações armazenadas no buffer do teclado à variável "sentence"
      System.out.println("Escreva:");
      sentence = inFromUSer.readLine();

      // Disponibiliza as informações contidas em "sentence" para a stream de saída do cliente
      outToServer.writeBytes(sentence + "\n");

      // Atribui as informações modificadas pelo servidor na variável "modifiedSentence"
      modifiedSentence = inFromServer.readLine();

      // Imprime no console do cliente a informação retornada pelo servidor
      System.out.println("From Server: " + modifiedSentence);

      // Fecha o Socket
      client.close();

    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}