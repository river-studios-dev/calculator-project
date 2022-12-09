import spark.ModelAndView;
import spark.Request;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
        port(8081);

        post("/calc", (req, res) -> resolve(req));
    }

    public static float resolve(Request request) {

        float n1 = Float.parseFloat(request.queryParams("n1"));
        float n2 = Float.parseFloat(request.queryParams("n2"));
        String ope = request.queryParams("op");

        boolean isSpecial = false;
        final String host = "127.0.1.1";

        if (ope == "r" || ope == "ˆ" || ope == "%")
            isSpecial = true;

        int port = 9998;
        float response = 0f;

        if (isSpecial) {
            port = 9999;
        }

        try {
            Socket client = null;
            float total = 0.0f;

            client = new Socket(host, port);

            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            output.write(String.valueOf(n1).getBytes());
            output.write(String.valueOf(n2).getBytes());
            output.write(ope.getBytes());

            response = input.read();

            client.close();
        }
        catch (Exception err) {
            System.err.println(err);
        }

        return response;
    }
}