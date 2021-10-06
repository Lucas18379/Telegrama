/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramaservidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Lucas
 */
public class DatUsuario 
{
    private String ip;
    private String nome;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    public DatUsuario(String ip, String nome, Socket socket, ObjectOutputStream output, ObjectInputStream input) {
        this.ip = ip;
        this.nome = nome;
        this.socket = socket;
        this.output = output;
        this.input = input;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }
    
}
