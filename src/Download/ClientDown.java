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

package Download;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientDown {

  public static void main (String [] args ) throws IOException {
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    String caminho = "/";
    String nomeArquivo = null;
    try {
      sock = new Socket("10.204.23.203", 2424);
      System.out.println("Conectando no Servidor...");
      Scanner scanner = new Scanner(System.in);
      
      caminho = JOptionPane.showInputDialog(null,
			  "Escreva o caminho de para salvar o ARQUIVO:",
			  "DOWNLOAD",
		      JOptionPane.INFORMATION_MESSAGE);
      
  nomeArquivo = JOptionPane.showInputDialog(null,
			  "Escreva o nome do ARQUIVO:",
			  "DOWNLOAD",
		      JOptionPane.INFORMATION_MESSAGE);
      
      new File("c:/z" + caminho).mkdirs();
      
  	JOptionPane.showMessageDialog(null, "Fazendo Download...");
      
      byte [] mybytearray  = new byte [6022386];
      InputStream is = sock.getInputStream();
      
      boolean existe = (new File("c:/z" + caminho)).exists();
      if(!existe) {
          JFrame frame = new JFrame();
    	  JOptionPane.showMessageDialog(frame,
      	        "Não foi possivel fazer Download, não existe um arquivo com esse nome na pasta inserida: " + "/c:" + caminho + nomeArquivo,
      	        "Erro ao escrever o arquivo",
      	        JOptionPane.INFORMATION_MESSAGE);
    	  
    	  System.exit(0);
      }
      
      fos = new FileOutputStream("c:/z" + caminho + nomeArquivo);
      bos = new BufferedOutputStream(fos);
      
      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame,
    	        "Download realizado para o caminho" + " C:/z/" + caminho + nomeArquivo,
    	        "DOWNLOAD",
    	        JOptionPane.INFORMATION_MESSAGE);
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }

}