package com.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
    protected Socket socket;
    protected int numeroBiglietti;
    protected BufferedReader input;
    protected DataOutputStream output;

    public ServerThread(Socket socket, int numeroBiglietti) throws Exception{
        this.socket = socket;
        this.numeroBiglietti = numeroBiglietti;
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.output = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run(){
        try {
            String richiesta;
            do{
                richiesta = input.readLine();
                if(richiesta.equals("D")){
                    output.writeBytes("a" + "\n");
                    output.writeBytes(numeroBiglietti + "\n");
                }
                if(richiesta.equals("A")){
                    if(numeroBiglietti > 0){
                        output.writeBytes("b" + "\n");
                        numeroBiglietti--;
                    }else{
                        output.writeBytes("c" + "\n");
                        return;
                    }
                }
            }while(!richiesta.equals("Q"));

            output.writeBytes("d" + "\n");
            socket.close();
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }        
    }
}
