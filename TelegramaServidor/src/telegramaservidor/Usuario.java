package telegramaservidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Usuario
{
    private String ip, usuario;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public Usuario(String ip, String usuario)
    {
        this.ip = ip;
        this.usuario = usuario;
    }

    public String getIp()
    {
        return ip;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }
    
}
