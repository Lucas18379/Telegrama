package telegramacliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelegramaCliente extends Application
{
    
    @Override
    public void start(Stage palco) throws Exception
    {
        Parent base = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
        
        Scene cena = new Scene(base);
        
        palco.setScene(cena);
        palco.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
