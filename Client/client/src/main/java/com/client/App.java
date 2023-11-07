package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
       try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Client inizializzato");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner lettura = new Scanner(System.in);

            String risposta = "";

            do{
                System.out.println("Inserisci D per vedere la quantità di biglietti rimanente;");
                System.out.println("Inserisci A per acquistare un biglietto;");
                System.out.println("Inserisci Q per terminare la connessione;");
                System.out.print("La tua scelta: ");
                String richiesta = lettura.nextLine();

                output.writeBytes(richiesta + "\n");
                risposta = input.readLine();
                
                if(risposta.equals("a")){
                    System.out.println("Il numero di biglietti disponibili e': " + input.readLine() + "\n");
                }else if(risposta.equals("b")){
                    System.out.println("Biglietto acquistato con successo!");
                }else if(risposta.equals("c")){
                    System.out.println("BIGLIETTI ESAURITI!");
                }else if(risposta.equals("d")){
                    System.out.println("Grazie per aver testato il Server!");
                }
            }while(!risposta.equals("d"));
            lettura.close();
       } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
       }

    }
}
