import java.io.*;
import java.net.Socket;

public class Cliente {
    final String HOST = "localhost"; //Host para la conexión
    final int PUERTO = 1234; //Puerto para la conexión
    protected Socket cs; //Socket del cliente
    protected BufferedReader entrada; //Flujo de datos de entrada
    protected PrintWriter salida; //Flujo de datos de salida
    String respuesta; //Mensaje de respuesta del servidor

    public Cliente() throws IOException //Constructor
    {
        //Socket para el cliente en localhost en puerto 1234
        cs = new Socket(HOST, PUERTO);

        //Flujo de datos de entrada desde el servidor
        entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));

        // Flujo de datos de salida hacia el servidor
        salida = new PrintWriter(cs.getOutputStream(), true);
    }

    public void startClient() //Método para iniciar el cliente
    {
        try {
            //Se escribe en el servidor usando su flujo de datos
            salida.println("time");

            //Se lee la respuesta del servidor
            respuesta = entrada.readLine();

            //Se muestra por pantalla la respuesta del servidor
            System.out.println("Respuesta del servidor: " + respuesta);

            //Se cierra la conexión con el servidor
            cs.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        try {
            Cliente cli = new Cliente(); //Se crea el cliente
            cli.startClient(); //Se inicia el cliente

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
