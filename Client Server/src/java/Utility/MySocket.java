package Utility;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class MySocket {
    private Socket socket;

    public DataInputStream input;

    public DataOutputStream output;

    /**
     * Конструктор для создание сокета через имя хоста и порт.
     * @param acceptorHost Имя хоста
     * @param acceptorPort Порт
     * @throws SocketException
     * @throws IOException
     */
    public MySocket(InetAddress acceptorHost, int acceptorPort)
            throws SocketException, IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }

    /**
     * Создание кастомного сокета через уже имеющийся.
     * @param socket Готовый сокет.
     * @throws IOException
     */
    public MySocket(Socket socket) throws IOException {
        this.socket = socket;
        setStreams();
    }

    /**
     * Подключения потоков передачи данных.
     * @throws IOException
     */
    private void setStreams() throws IOException {
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Метод для передачи сообщения.
     * @param message Сообщение.
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        output.writeUTF(message);
        output.flush();
    }

    /**
     * Метод для получения сообщения.
     * @return Переданное сообщение.
     * @throws IOException
     */
    public String receiveMessage() throws IOException {
        String message = input.readUTF();
        return message;
    }

    /**
     * Закрывает соединения.
     * @throws IOException
     */
    public void close() throws IOException {
        socket.close();
    }

    /**
     * Метод для отправки файла.
     * @param file Файл для отправки.
     * @throws IOException
     */
    public void sendFile(File file) throws IOException {
        FileInputStream fileIn = new FileInputStream(file);
        byte[] buf = new byte[Short.MAX_VALUE];
        int bytesRead;
        while( (bytesRead = fileIn.read(buf)) != -1 ) {
            output.writeShort(bytesRead);
            output.write(buf,0,bytesRead);
        }
        output.writeShort(-1);
        fileIn.close();
    }

    /**
     * Метод получения файла по указанному пути.
     * @param file Место для получения файла.
     * @throws IOException
     */
    public void receiveFile(File file) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        byte[] buf = new byte[Short.MAX_VALUE];
        int bytesSent;
        while( (bytesSent = input.readShort()) != -1 ) {
            input.readFully(buf,0,bytesSent);
            fileOut.write(buf,0,bytesSent);
        }
        fileOut.close();
    }

    /**
     * Команда выдающая файлы в директории сервера.
     * @throws IOException
     */
    public void dirCommand() throws IOException {
        int size = Integer.parseInt(receiveMessage());
        System.out.println("Server directory size: "+ size);
        for (int i = 0; i < size; i++){
            System.out.println(receiveMessage());
        }
    }

    /**
     * Возможные команды сервера.
     * @throws IOException
     */
    public int helpCommand() throws IOException {
        int size = Integer.parseInt(receiveMessage());
        return size;
    }
}
