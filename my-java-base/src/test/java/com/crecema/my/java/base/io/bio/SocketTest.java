package com.crecema.my.java.base.io.bio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketTest {

    @Test
    public void testSimpleSocket() throws InterruptedException {
        Runnable server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8080)) {
                System.out.println("server--> server started, listening on port: " + serverSocket.getLocalPort());
                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    byte[] buffer = new byte[32];
                    int size;
                    while ((size = inputStream.read(buffer)) != -1) {
                        String request = new String(buffer, 0, size);
                        Thread.sleep(1000); // mock handle request
                        String response = "hi " + request;
                        System.out.println("server--> response: " + response);
                        outputStream.write(response.getBytes());
                    }
                    System.out.println("server--> bye");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Runnable client = new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 8080)) {
                int port = socket.getLocalPort();
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                for (int i = 0; i < 2; i++) {
                    System.out.format("client(%d)--> request: %d\n", port, port);
                    outputStream.write((String.valueOf(port)).getBytes());
                    byte[] buffer = new byte[32];
                    int size;
                    while ((size = inputStream.read(buffer)) != -1) {
                        String response = new String(buffer, 0, size);
                        break;
                    }
                    try {
                        Thread.sleep(1000); // mock send request
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.format("client(%d)--> bye\n", port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread serverThread = new Thread(server);
        serverThread.start();
        Thread.sleep(100);
        Thread clientThread = new Thread(client);
        clientThread.start();
        Thread clientThread2 = new Thread(client);
        clientThread2.start();

        clientThread.join();
        clientThread2.join();

        serverThread.isInterrupted();
    }

    @Test
    public void testThreadSocket() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8080)) {
                System.out.println("server--> server started, listening on port: " + serverSocket.getLocalPort());
                while (true) {
                    Socket socket = serverSocket.accept();

                    // 为每个连接创建一个线程处理
                    executorService.execute(() -> {
                        InputStream inputStream = null;
                        try {
                            inputStream = socket.getInputStream();
                            OutputStream outputStream = socket.getOutputStream();
                            byte[] buffer = new byte[32];
                            int size;
                            while ((size = inputStream.read(buffer)) != -1) {
                                String request = new String(buffer, 0, size);
                                Thread.sleep(1000); // mock handle request
                                String response = "hi " + request;
                                System.out.println("server--> response: " + response);
                                outputStream.write(response.getBytes());
                            }
                            System.out.println("server--> bye");
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Runnable client = new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 8080)) {
                int port = socket.getLocalPort();
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                for (int i = 0; i < 2; i++) {
                    System.out.format("client(%d)--> request: %d\n", port, port);
                    outputStream.write((String.valueOf(port)).getBytes());
                    byte[] buffer = new byte[32];
                    int size;
                    while ((size = inputStream.read(buffer)) != -1) {
                        String response = new String(buffer, 0, size);
                        break;
                    }
                    try {
                        Thread.sleep(1000); // mock send request
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.format("client(%d)--> bye\n", port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread serverThread = new Thread(server);
        serverThread.start();
        Thread.sleep(100);
        Thread clientThread = new Thread(client);
        clientThread.start();
        Thread clientThread2 = new Thread(client);
        clientThread2.start();

        clientThread.join();
        clientThread2.join();
        serverThread.join();
    }

    private static class server {
        public void start() throws IOException {
            try (ServerSocket serverSocket = new ServerSocket(8080)) {
                System.out.println("server started, listening on port: " + serverSocket.getLocalPort());
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("client connect success: " + socket.getRemoteSocketAddress());

                    InputStream IS = socket.getInputStream();
                    byte[] buffer = new byte[32];
                    int size;
                    while ((size = IS.read(buffer)) != -1) {
                        System.out.println("receive request: " + new String(buffer, 0, size));
                    }
                }
            }
        }
    }

    private static class client {
        private static final String HOST = "127.0.0.1";
        private static final int PORT = 8080;
        private static final String[] REQUESTS = {"hello ", "I am ", "V ", "!!!"};
        public void start() throws IOException {
            try (Socket socket = new Socket(HOST, PORT)) {
                for (String request : REQUESTS) {
                    socket.getOutputStream().write(request.getBytes());
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
//        new Thread(() -> {
//            try {
//                new server().start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();

        new Thread(() -> {
            try {
                new client().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new client().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new client().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
