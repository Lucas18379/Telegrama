package telegramacliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ThreadClient implements Runnable
{
    private TelaClienteController window;
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String menssage;
    private TextArea taDialog;

    public ThreadClient(Socket client, ObjectOutputStream output, ObjectInputStream input, TextArea taDialog, TelaClienteController window)
    {
        this.client = client;
        this.output = output;
        this.input = input;
        this.window = window;
        this.taDialog = taDialog;
    }
    
    @Override
    public void run()
    {
        try
        {
            do
            {
               menssage = (String) input.readObject();
               taDialog.setText(taDialog.getText() + "\n" +  menssage);
            }while(!menssage.equalsIgnoreCase("Servidor Desconectado"));
            
           /* //Finalizar clientes
            window.getTfName().setDisable(false);
            window.getTfName().clear();
            Platform.runLater(()->{window.getBtConnect().setDisable(false);}); 
            window.getBtDisconnect().setDisable(true);
            window.getBtSend().setDisable(true);
            window.setLbStatus("Offline"); //ESSA MERDA DO CARALHO NÃO FUNCIONA
            window.getTfMenssage().setDisable(true);
            window.getTaDialog().setDisable(true);
            window.setConectou(false);*/
            
            output.close();
            input.close();
            client.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro RUN threadClient: " + "\n" + e.getMessage());
        }
    }

    
    
}
