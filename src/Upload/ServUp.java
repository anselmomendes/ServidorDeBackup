/*
 Universidade Federal do sul e Sudeste do Pará
 Facudade de Computação e Engenharia Elétrica
 Disciplina: Rede de Computadores
 Docente: Prof. Dr. Warley Muricy Valente Jr.
 Trabalho Final - Redes de Computadores
 Discentes: Anselmo Mendes Oliveira
 Fernando da Silva Freire
 Leoni Martins Oliveira Jr.
 */

package Upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServUp {

  public static void main (String [] args ) throws IOException {
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    String caminho = "/";
    String nomeArquivo = "null";
    try {
      sock = new Socket("10.204.23.203", 5555);
      System.out.println("Connecting...");
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("Escreva o caminho para salvar o ARQUIVO: ");
      caminho = scanner.nextLine();
      
      System.out.println("Digite o nome do arquivo que a ser SALVO: ");
      nomeArquivo = scanner.nextLine();
      
      new File("c:/z" + caminho).mkdirs();
      
      System.out.println("Connecting...");
      
      byte [] mybytearray  = new byte [6022386];
      InputStream is = sock.getInputStream();
      
      boolean existe = (new File("c:/z" + caminho + "/" + nomeArquivo)).exists();
      if(existe) {
    	  System.out.println("Não foi possivel fazer uploud, já exite um arquivo com esse nome na pasta.");
    	  System.exit(0);
      }
      
      fos = new FileOutputStream("c:/z" + caminho + "/" + nomeArquivo);
      bos = new BufferedOutputStream(fos);

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("Download realizado para o caminho C:/z" + caminho + "/" +nomeArquivo);
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }

}