package telegramaservidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConexaoUsu implements Runnable
{
    private TelaServidorController tela;
    private Socket conexao;
    private String mens,ip,usuario;
    private ArrayList<DatUsuario> arrayUsu;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int aux;
    
    public ConexaoUsu(TelaServidorController tela, Socket conexao, ArrayList<DatUsuario> arrayUsu, ObjectOutputStream output, ObjectInputStream input) {
        this.tela = tela;
        this.conexao = conexao;
        this.arrayUsu = arrayUsu;
        this.output = output;
        this.input = input;
        aux = -1;
    }
    
    @Override
    public void run()
    {
        try
       {
           int usu = 0;
            ObjectOutputStream output = new ObjectOutputStream(conexao.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(conexao.getInputStream());
            do
            {  mens = ( String ) input.readObject();  // ler mensagem
               if(usu == 0)
               {
                   usu++;
                   tela.getTabela().getItems().add(new Usuario(ip,mens));
               }
               else
               {
                   output.writeObject(mens); 
                   
               }
                try{Thread.sleep(1000);}catch(Exception e){}
               /// opcionalmente pode devolver uma mensagem ao cliente
               //output.writeObject("Servidor XYZ recebeu a informacao"); 
            } while ( !mens.equals( "FIM" ));
            
            // cliente desconectou, começa processo de retirar o ip do cliente da tabela
            int i=0;
            
            while(i <tela.getTabela().getItems().size() && !tela.getTabela().getItems().get(i).toString().equals(ip))
                i++;
            tela.getTabela().getItems().remove(i-1);
            // fecha as conexões
            output.close();
            input.close(); 
            conexao.close();
       }catch(Exception e){}
    }
    
}
