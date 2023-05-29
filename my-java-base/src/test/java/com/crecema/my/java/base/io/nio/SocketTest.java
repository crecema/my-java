package com.crecema.my.java.base.io.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketTest {

    @Test
    public void testSimpleSocket() throws InterruptedException {
        Runnable server = () -> {
            try (var selector = Selector.open(); var serverSocketChannel = ServerSocketChannel.open()) {
                // 非阻塞模式
                serverSocketChannel.configureBlocking(false);
                // 绑定服务器地址和端口号
                serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8080));

                // 注册ServerSocketChannel到Selector并监听ACCEPT事件
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                while (!Thread.currentThread().isInterrupted()) {
                    selector.select(); // 阻塞等待事件发生
                    selector.selectedKeys().forEach(key -> {
                        try {
                            if (key.isAcceptable()) {
                                // 获取ServerSocketChannel
                                ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                                // 获取SocketChannel
                                SocketChannel socketChannel = serverSocketChannel1.accept();
                                // 非阻塞模式
                                socketChannel.configureBlocking(false);
                                // 注册SocketChannel到Selector并监听READ事件
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            } else if (key.isReadable()) {
                                // 获取SocketChannel
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                // 读取数据
                                ByteBuffer buffer = ByteBuffer.allocate(32);
                                int size;
                                while ((size = socketChannel.read(buffer)) != -1) {
                                    if (size == 0) {
                                        break;
                                    }
                                    buffer.flip();
                                    byte[] bytes = new byte[buffer.remaining()];
                                    buffer.get(bytes);
                                    String request = new String(bytes);
                                    System.out.println("server--> request: " + request);
                                    Thread.sleep(1000); // mock handle request
                                    String response = "hi " + request;
                                    System.out.println("server--> response: " + response);
                                    socketChannel.write(ByteBuffer.wrap(response.getBytes()));
                                    buffer.clear();
                                }
                                System.out.println("server--> bye");
                                socketChannel.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable client = () -> {
            try (var clientChannel = SocketChannel.open()) {
                // 连接服务器
                clientChannel.connect(new InetSocketAddress("localhost", 8080));
                // 发送消息给服务器
                String message = "Hello, Server!";
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                clientChannel.write(buffer);

                // 读取服务器响应
                buffer.clear();
                int bytesRead = clientChannel.read(buffer);
                buffer.flip();
                byte[] responseBytes = new byte[bytesRead];
                buffer.get(responseBytes);
                String response = new String(responseBytes);
                System.out.println("Server response: " + response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread server1 = new Thread(server);
        server1.start();

        Thread client1 = new Thread(client);
        client1.start();

        Thread client2 = new Thread(client);
        client2.start();

        server1.join();
        client1.join();
        client2.join();

    }
}
