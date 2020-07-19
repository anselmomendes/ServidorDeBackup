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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServDown {

  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    String caminho = "/";
    String nomeArquivo = null;
    
    try {
    	servsock = new ServerSocket(2424);
    	
    	System.out.println("Conectando...");
        Scanner scanner = new Scanner(System.in);
        
    	
    		caminho = JOptionPane.showInputDialog(null,
    				  "Escreva o caminho de origem para e o ARQUIVO: (ex: /documentos/notas)",
    				  "DOWNLOAD",
    			      JOptionPane.INFORMATION_MESSAGE);

    		nomeArquivo = JOptionPane.showInputDialog(null,
  				  "Escreva o nome do ARQUIVO: (ex: joãoPessoa.jpg)",
  				  "DOWNLOAD",
  			      JOptionPane.INFORMATION_MESSAGE);
    		
        boolean Existe = (new File("c:/z" + caminho)).exists();
        if(!Existe) {
          JFrame frame = new JFrame();

          JOptionPane.showMessageDialog(frame,
        	        "Não foi possivel fazer Download, não existe um arquivo com esse nome na pasta inserida: c:/z/" + caminho + nomeArquivo + " + ",
        	        "Erro ao escrever o arquivo",
        	        JOptionPane.INFORMATION_MESSAGE);
      	  System.exit(0);
        }
    	
      while (true) {
    	  
        try {
          

          JOptionPane.showMessageDialog(null, "Aguardando Cliente!");
          
          sock = servsock.accept();
          
      	  JOptionPane.showMessageDialog(null, "Cliente Conectado: " + sock);
          
          File myFile = new File ("c:/z" + caminho + nomeArquivo);
                   
          byte [] mybytearray  = new byte [(int)myFile.length()];
          
          fis = new FileInputStream(myFile);
          
          bis = new BufferedInputStream(fis);
          
          bis.read(mybytearray, 0, mybytearray.length);
          
          os = sock.getOutputStream();
          
          System.out.println("Enviando Arquivo");
                    
          os.write(mybytearray, 0, mybytearray.length);
          
          os.flush();
          
          System.out.println("Arquivo Enviado.");
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