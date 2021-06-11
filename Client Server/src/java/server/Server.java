package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;

import Utility.*;

public class Server {
    public static void main(String[] args) {
        // Инициализация порта.
        int serverPort = Utility.getPort();
        if (args.length > 0)
            serverPort = Integer.parseInt(args[0]);

        // Создание сокета.
        try {
            System.out.println("MultithreadedEchoServer: awaiting clients on port " + serverPort + " ...");
            int i = 1;
            ServerSocket s = new ServerSocket(serverPort);

            // Запуск потока.
            while (true) {
                Socket incoming = s.accept();
                MySocket mySocket = new MySocket(incoming);
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(mySocket, i);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class ThreadedEchoHandler implements Runnable {

    private final MySocket incoming;
    private final Integer id;

    /**
     * Constructs a handler.
     *
     * @param socket - the incoming socket
     */
    ThreadedEchoHandler(MySocket socket, Integer id) {
        incoming = socket;
        this.id = id;
    }

    // Поток с обработкой информации от клиента.
    public void run() {
        try {
            // Приветственное сообщение клиенту.
            incoming.sendMessage("Welcome to this server! Send me help to get command list");
            try {
                boolean done = false;
                // Проверка команд.
                while (!done) {
                    String line = incoming.receiveMessage();
                    System.out.println("Client " + id + " message: " + line + " | time: " + Date.from(Instant.now()));

                    // Введена команда help.
                    if (line.trim().equalsIgnoreCase("help")) {
                        incoming.sendMessage("5");
                        incoming.sendMessage("help : to get command list");
                        incoming.sendMessage("dir : to get available server directory");
                        incoming.sendMessage("load -filename : to download file from server directory");
                        incoming.sendMessage("hello : to say hello to server");
                        incoming.sendMessage("bye : to stop connection with server");
                    }

                    // Введена команда dir.
                    if (line.trim().equalsIgnoreCase("dir")) {
                        String currentPath = new java.io.File(".").getCanonicalPath();
                        File directoryPath = new File(currentPath);
                        String[] contents = directoryPath.list();
                        incoming.sendMessage("" + contents.length);
                        for (int i = 0; i < contents.length; i++) {
                            Path temp = Paths.get(contents[i]);
                            incoming.sendMessage(contents[i] + " | Size: " + Files.size(temp) + " bytes");
                        }
                    }

                    // Введена команда load.
                    if (line.contains("load")) {
                        try {
                            String currentPath = new java.io.File(".").getCanonicalPath();
                            File directoryPath = new File(currentPath);
                            String[] contents = directoryPath.list();

                            String path = line.split("-")[1];
                            File file = new File(path);

                            boolean sent = false;
                            if (!Utility.fileChecker(line)) {
                                incoming.sendMessage("404");
                                throw new IllegalArgumentException("Wrong filename symbol!");
                            }

                            for (int i = 0; i < contents.length; i++) {
                                if (Utility.dataChecker(contents[i], path)) {
                                    incoming.sendMessage("200");
                                    incoming.sendFile(file);
                                    System.out.println("Client " + id + " received: " + path + " | time: "
                                            + Date.from(Instant.now()) + " | size: " + Files.size(Path.of(path)) + " bytes");
                                    sent = true;
                                    break;
                                }
                            }
                            if (!sent)
                                incoming.sendMessage("404");
                        } catch (Exception e) {
                        }
                    }

                    // Введена команда hello.
                    if (line.trim().equalsIgnoreCase("hello")) {
                        incoming.sendMessage("HI!");
                    }

                    // Введена команда bye.
                    if (line.trim().equalsIgnoreCase("BYE")) {
                        done = true;
                        incoming.sendMessage("Bye!");
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
