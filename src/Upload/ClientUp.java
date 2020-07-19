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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientUp {

  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    String caminho = "/";
    String nomeArquivo = "null";
    
    try {
    	servsock = new ServerSocket(5556);
    	
    	System.out.println("Connecting...");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Escreva o caminho de origem para salvar o ARQUIVO: ");
        caminho = scanner.nextLine();
        
        System.out.println("Digite o nome do arquivo que a ser SALVO: ");
        nomeArquivo = scanner.nextLine();

        boolean existe = (new File("c:/z" + caminho + "/" + nomeArquivo)).exists();
        if(existe) {
      	  System.out.println("O arquivo inserido não existe!");
      	  System.exit(0);
        }
    	
      while (true) {
        System.out.println("Waiting...");
        try {
          sock = servsock.accept();
          
          System.out.println("Accepted connection : " + sock);
          
          File myFile = new File ("c:/z" + caminho + nomeArquivo);
                   
          byte [] mybytearray  = new byte [(int)myFile.length()];
          
          fis = new FileInputStream(myFile);
          
          bis = new BufferedInputStream(fis);
          
          bis.read(mybytearray, 0, mybytearray.length);
          
          os = sock.getOutputStream();
          
          System.out.println("Sending...");
          
          os.write(mybytearray, 0, mybytearray.length);
          
          os.flush();
          
          System.out.println("Done.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
}