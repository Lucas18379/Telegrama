package telegramaservidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javafx.application.Platform;

public class Conexao implements Runnable
{
    private TelaServidorController principal;
    private ServerSocket server;
    private Socket sock;
    private String usuario,ip;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ArrayList<DatUsuario> arrayUsu;
    private ConexaoUsu threadConecUsu;
    private Thread novoThread;
    
    Conexao(TelaServidorController principal,ServerSocket server)
    {
        this.server = server;
        this.principal = principal;
    }
    
    @Override
    public void run()
    {
        do
       {
            try
            {
                sock  = server.accept();
                output = new ObjectOutputStream(sock.getOutputStream());
                input = new ObjectInputStream(sock.getInputStream());

                usuario = (String) input.readObject();
                arrayUsu.add(new DatUsuario(sock.getInetAddress().getHostAddress(),usuario, sock, output, input)); //add novo cliente no arrayList de clientes
                
                //Platform.runLater(() ->{updateListView();});  //atualizando listViews
                Platform.runLater(()->{updateTaView();});
                threadConecUsu = new ConexaoUsu(principal, sock, arrayUsu, output, input);
                novoThread = new Thread(threadConecUsu);
                novoThread.start();
            }
            catch(Exception e) {}
            
       } while(principal.getStatus());
        output.flush();
        output.close();
        input.close();
        sock.close();
       /* while(principal.getStatus())
        {
            try
            {
                sock = server.accept();
                //ip do usuario
                String ip = sock.getInetAddress().getHostAddress();
                //ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
                //usuario = (String) input.readObject();
                //usuario = "Teste";
                //Platform.runLater(()->{principal.getTabela().getItems().add(new Usuario(ip,usuario));});
                //lança uma trhread para tratar exclusivamente esse o usuario
                
                ConexaoUsu con = new ConexaoUsu(principal,sock,usuario);
                Thread ThreadUsu = new Thread(con);
                // inicia a thread do usuario
                ThreadUsu.start(); 
            }catch(Exception e){}
        }*/
    }
    private void updateTaView()
    {
        if(!arrayUsu.isEmpty())
        {
            //principal.getLvConnected().getItems().clear();//limpando lisView
            principal.getTabela().getItems().addAll(arrayUsu);   
        }
            
        //window.getLvConnected().getItems().toString();
    }
}
