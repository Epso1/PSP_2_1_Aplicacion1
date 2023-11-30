import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {
    private final int PUERTO = 1234; //Puerto para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaCliente; //Flujo de datos de salida
    protected BufferedReader entrada; //Flujo de datos de entrada
    protected PrintWriter salida; //Flujo de datos de salida

    // Constructor
    public Servidor() throws IOException {
        //Se crea el socket para el servidor en puerto 1234
        ss = new ServerSocket(PUERTO);
        //Socket para el cliente
        cs = new Socket();
    }

    // Método para iniciar el servidor
    public void startServer(){
        final int puerto = 1234;

        try {
            // Se espera una conexión de un cliente
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {

                // Cuando se recibe una conexión, se crea un nuevo socket para comunicarse con el cliente
                cs = ss.accept();

                // Se muestra información sobre el cliente
                System.out.println("Cliente conectado desde " + cs.getInetAddress());

                // Se obtienen los flujos de escritura y lectura
                entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                salida = new PrintWriter(cs.getOutputStream(), true);

                // Se lee la petición del cliente
                mensajeServidor = entrada.readLine();

                // Se escribe la respuesta al cliente
                if (mensajeServidor != null && mensajeServidor.equals("time")) {
                    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
                    String horaActual = formatoHora.format(new Date());
                    salida.println("La hora actual es: " + horaActual);
                }

                // Se cierra la conexión con el cliente
                cs.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
            servidor.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
