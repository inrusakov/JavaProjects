package client;

import java.io.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Utility.*;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome!");
        String serverHost = Utility.getHost();
        int serverPort = Utility.getPort();

        if (args.length > 1) {
            serverHost = args[0];
            serverPort = Integer.parseInt(args[1]);
        }

        MySocket mySocket = null;
        try {
            System.out.println("Client: started...");
            System.out.println("Client: socket created...");
            mySocket = new MySocket(
                    InetAddress.getByName(serverHost), serverPort);
            System.out.println("Client: connection established...");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHost);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: " + serverHost);
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println(mySocket.receiveMessage());

        while ((userInput = stdIn.readLine()) != null) {
            try {
                if (userInput.contains("load")) {
                    System.out.println("Print Y to confirm downloading");
                    String ans = stdIn.readLine();
                    if (!ans.equals("Y"))
                        throw new IllegalArgumentException();
                    mySocket.sendMessage(userInput);
                    Integer code = Integer.parseInt(mySocket.receiveMessage());
                    if (code == 200) {
                        System.out.println("Print downloading path: ");
                        String path = stdIn.readLine();
                        File file = new File(path);
                        mySocket.receiveFile(file);
                        System.out.println("File "+path+" loaded.");
                    }
                    if (code == 404)
                        System.out.println("File not found!");
                    continue;
                }
                mySocket.sendMessage(userInput);
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else if (userInput.equalsIgnoreCase("dir")) {
                    mySocket.dirCommand();
                } else if (userInput.equalsIgnoreCase("help")) {
                    System.out.println("Server available commands: ");
                    int size = mySocket.helpCommand();
                    for (int i = 0; i < size; i++){
                        System.out.println(mySocket.receiveMessage());
                    }
                }  else if (userInput.contains("hello")) {
                    System.out.println(mySocket.receiveMessage());
                } else {
                    System.out.println("Unknown command!\n");
                }
                System.out.println("\nEnter new command: ");
            } catch (EOFException e) {
                System.out.println("No such file!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong command format!");
            } catch (SocketException e) {
                System.out.println("Connection troubles");
            } catch (IllegalArgumentException e){
                System.out.println("Loading declined!");
            } catch (FileNotFoundException e){
                System.out.println("Invalid path!");
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
                break;
            }
        }
        stdIn.close();
        mySocket.close();
    }
}
