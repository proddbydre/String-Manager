package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    // Client
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        String frase; 
        String operazione;
        try {
            System.out.println("Client avviato!");
            Socket s = new Socket("localhost", 3000);
            System.out.println("Client connesso!");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            do {
                // Menu Client
                System.out.println("1 Maiuscolo, 2 Minuscolo, 3 inverti, 4 Conta Caratteri, 0 Chiudi la connessione:");
                System.out.println("Scegli operazione: ");
                operazione = scan.nextLine();

                // Se viene inviato !, allora chiusura della connessione
                if (operazione.equals("0")) 
                {
                    System.out.println("Il client sta terminando");
                    out.writeBytes("!" + "\n");
                    break;
                }
                System.out.println("Inserisci la frase: ");
                frase = scan.nextLine();
                // Invio al server l'operazione
                // E la frase da manipolare 
                out.writeBytes(operazione + "\n");
                out.writeBytes(frase + "\n");
                // Resto in attesa di risposta da parte del server
                frase = in.readLine();
                System.out.println("Il server ha risposto con: " + frase);
            } while (true);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scan.close();
    }
}