package br.com.unifor.sistemasdistribuidos;

import java.io.IOException;
import java.net.InetAddress;

public class ExisteIP {
  public Boolean ping(String  ip) throws IOException {
    return InetAddress.getByName(ip).isReachable(3000);
  }
}
