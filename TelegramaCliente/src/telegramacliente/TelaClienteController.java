
package telegramacliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaClienteController implements Initializable
{
    private ThreadClient tc; // classe que guarda informações sobre o cliente
    private Thread threadRunning; //Thread do cliente
    
    private Socket Usuario;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    @FXML
    private TextField NomeUsuario;
    @FXML
    private Button btConectar;
    @FXML
    private Button btDesconectar;
    @FXML
    private Label lbStatus;
    @FXML
    private TextField MensagemUsuario;
    @FXML
    private Button btEnviar;
    @FXML
    private TextArea taMesagens;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    

    @FXML
    private void clkConectar(ActionEvent event) throws IOException
    {
        try
         {
            Usuario = new Socket("localhost" ,8081 );
            output = new ObjectOutputStream(Usuario.getOutputStream());
            input = new ObjectInputStream(Usuario.getInputStream());
            
            output.writeObject(NomeUsuario.getText());
            output.flush();
            
            tc = new ThreadClient(Usuario, output, input, taMesagens, this);
            threadRunning = new Thread(tc);
            threadRunning.start();
            
            NomeUsuario.setDisable(true);
            
            btConectar.setDisable(true);
            btDesconectar.setDisable(false);
            lbStatus.setText("Conectado");
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(ex.getMessage());
            a.showAndWait();
        } 
         
    }

    @FXML
    private void clkDesconectar(ActionEvent event) throws IOException
    {
        /*output.writeObject("FIM"); se n funfa tira coment
        output.flush(); */
        Usuario.close(); 
        output.close(); 
        input.close(); 
        NomeUsuario.setDisable(false);
        btConectar.setDisable(false);
        btDesconectar.setDisable(true);
        lbStatus.setText("Desconectado");
    }

    @FXML
    private void clkEnviarMensagem(ActionEvent event) throws IOException 
    {
        if(!MensagemUsuario.getText().trim().isEmpty())
        {
            try
            {
                output.writeObject(NomeUsuario.getText() + " diz: " + MensagemUsuario.getText());
                output.flush();
            }
            catch(Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro enviar");
                alert.setHeaderText("Não esta enviando menssagem !!" + " ERRO: " + e.getMessage());
                alert.showAndWait();

                NomeUsuario.requestFocus();
            }
        }
        
        MensagemUsuario.clear();
        MensagemUsuario.requestFocus();
    }
    
}
