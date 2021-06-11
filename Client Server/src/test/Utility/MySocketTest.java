package Utility;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

class MySocketTest {

    // Перед запуском тестов необходимо запустить сервер на порт 3456.

    @Test
    void helloCommandTest() {
        try {
            MySocket mySocket = new MySocket(
                    InetAddress.getByName("localhost"), 3456);

            assertEquals(mySocket.receiveMessage(),"Welcome to this server! Send me help to get command list");
            mySocket.sendMessage("hello");
            assertEquals(mySocket.receiveMessage(),"HI!");
            mySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void dirCommandTest() {
        try {
            MySocket mySocket = new MySocket(
                    InetAddress.getByName("localhost"), 3456);

            assertEquals(mySocket.receiveMessage(),"Welcome to this server! Send me help to get command list");
            mySocket.sendMessage("dir");
            Integer temp = Integer.parseInt(mySocket.receiveMessage());
            assertTrue(temp != null);
            mySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendFile() {
        try {
            MySocket mySocket = new MySocket(
                    InetAddress.getByName("localhost"), 3456);

            assertEquals(mySocket.receiveMessage(),"Welcome to this server! Send me help to get command list");
            mySocket.sendMessage("load -test1.txt");
            assertEquals(mySocket.receiveMessage(),"200");
            File file = new File("src\\test\\test2.txt");
            mySocket.receiveFile(file);


            mySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void helpCommand() {
        try {
            MySocket mySocket = new MySocket(
                    InetAddress.getByName("localhost"), 3456);

            assertEquals(mySocket.receiveMessage(),"Welcome to this server! Send me help to get command list");
            mySocket.sendMessage("help");
            assertEquals(mySocket.receiveMessage(),"5");
            assertTrue(mySocket.receiveMessage().contains("help"));
            assertTrue(mySocket.receiveMessage().contains("dir"));
            assertTrue(mySocket.receiveMessage().contains("load"));
            assertTrue(mySocket.receiveMessage().contains("hello"));
            assertTrue(mySocket.receiveMessage().contains("bye"));

            mySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}