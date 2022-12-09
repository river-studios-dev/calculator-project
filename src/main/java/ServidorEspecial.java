import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEspecial extends Thread {

    public static void main(String[] args) throws IOException {

        String operacao;
        float n1, n2, total = 0.0f;

        ServerSocket servidor = new ServerSocket (9999);
        System.out.println ("Porta Servidor Especialista Aberta");

        System.out.println ("Aguardando ...");
        Socket cliente = servidor.accept();

        System.out.println("nova conexao "+ cliente.getInetAddress().getHostAddress());

        ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

        n1 = input.read();
        n2 = input.read();
        operacao = input.readUTF();

        switch(operacao){
            case "%":
                total = (n1 * n2 / 100);
                break;
            case "r":
                total = (float) Math.sqrt(n1);
                break;
            case "ˆ":
                total = (float) Math.pow(n1, n2);
                break;
            default:
                System.out.println("operacao invalida");
        }

        output.writeFloat(total);
        output.flush();
        servidor.close();

    }
}
