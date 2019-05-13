import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunnable implements Runnable {

    private static Socket socket;
    private static ServerSocket serverSocket;
    private static BufferedReader incomingReader;
    private static BufferedWriter outgoingWriter;
    int a = 0;

    @Override
    public synchronized void run() {
        try {
            try {
                serverSocket = new ServerSocket(4004);
                System.out.println("Сервер запущен!");
                execute();
            } finally {
                //System.out.println("Сервер закрыт!");
                //serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private synchronized void execute() throws IOException {
        while (true) {
            try {Socket socket = serverSocket.accept();
                BufferedReader incomingReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter outgoingWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String word = incomingReader.readLine();
                System.out.println(word + " " + a);
                Runtime.getRuntime().exec(word);
                outgoingWriter.write("Client: " + word + "\n");
                outgoingWriter.flush();
                a++;
            } catch (IOException ex) {}
        }
    }
}
