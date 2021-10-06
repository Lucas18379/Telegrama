package telegramaservidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaServidorController implements Initializable
{
    ServerSocket server;
    Thread ThreadServer;
    Conexao con;
    boolean status = false;
    @FXML
    private Button btLigaServer;
    @FXML
    private Button btDesligaServer;
    @FXML
    private Button btSair;
    @FXML
    private TableColumn <String, String> colIP;
    @FXML
    private TableColumn <String, String> colUsuario;
    @FXML
    private Label lbStatus;
    @FXML
    private TableView <DatUsuario> Tabela;
    @FXML
    private Button bAvisar;
    @FXML
    private Button bAvisarTodos;

    
    @FXML
    private void clkLigaServer(ActionEvent event) throws IOException
    {
        server = new ServerSocket(8081,40);
        con = new Conexao(this,server);
        ThreadServer = new Thread(con);
        ThreadServer.start(); // inicia a thread
        status=true;
        btLigaServer.setDisable(true);
        btDesligaServer.setDisable(false);
        lbStatus.setText("Servidor em "+server.getInetAddress().getCanonicalHostName());
    }

    @FXML
    private void clkDesligaServer(ActionEvent event) throws IOException
    {
        server.close();
        ThreadServer.interrupt();
        status=false; // seta o flag e faz com que todas as threads servidoras sejam finalizadas
        btLigaServer.setDisable(false);
        btDesligaServer.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        colIP.setCellValueFactory(new PropertyValueFactory<>("ip"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException
    {
        if(server!=null)
            clkDesligaServer(event);
        Platform.exit();
    }
    public TableView getTabela()
    {
        return Tabela;
    }
    public boolean getStatus()
    {
        return status;
    }

    @FXML
    private void clkAvisar(ActionEvent event) {
    }

    @FXML
    private void clkAvisarTodos(ActionEvent event) {
    }
}
