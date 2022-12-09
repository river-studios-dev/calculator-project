import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        String operacao;
        float n1, n2, total = 0.0f;

        ServerSocket servidor = new ServerSocket (9998);
        System.out.println ("Porta Servidor Aberta");

        System.out.println ("Aguardando ...");
        Socket cliente = servidor.accept();

        System.out.println("nova conexao "+ cliente.getInetAddress().getHostAddress());

        ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

        n1 = input.readFloat();
        n2 = input.readFloat();
        operacao = input.readUTF();

        switch(operacao){
            case "+":
                total = n1 + n2;
                break;
            case "-":
                total = n1 - n2;
                break;
            case "*":
                total = n1 * n2;
                break;
            case "/":
                total = n1 / n2;
                break;
            default:
                System.out.println("operacao invalida");
        }

        output.writeFloat(total);
        output.flush();
        servidor.close();

    }
}



