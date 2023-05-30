package com.crecema.my.java.base.io.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;

public class SocketTest {

    @Test
    public void testSimpleSocket() throws InterruptedException {
        Runnable server = () -> {
            // 创建Selector 打开ServerSocketChannel
            try (var selector = Selector.open(); var serverSocketChannel = ServerSocketChannel.open()) {
                // 切换非阻塞模式
                serverSocketChannel.configureBlocking(false);

                // 绑定服务器地址和端口号
                serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8080));

                // 注册ServerSocketChannel到Selector并监听ACCEPT事件
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                // 阻塞等待事件发生;
                while (selector.select() > 0) {
                    // 获取事件
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    // 处理事件
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            // 获取SocketChannel
                            SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                            // 切换非阻塞模式
                            socketChannel.configureBlocking(false);
                            // 注册SocketChannel到Selector并监听READ事件
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            // 获取SocketChannel
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            // 读取数据
                            String request;
                            ByteBuffer buffer = ByteBuffer.allocate(32);
                            int size;
                            while ((size = socketChannel.read(buffer)) > 0) {
                                assert true;
                            }
                            if (size == -1) {
                                socketChannel.close();
                                System.out.println("server--> bye");
                                continue;
                            } else {
                                buffer.flip();
                                request = new String(buffer.array(), 0, buffer.remaining());
                                buffer.clear();
                                // 响应数据
                                String response = "hi " + request;
                                buffer.put(response.getBytes());
                                buffer.flip();
                                socketChannel.write(buffer);
                                buffer.clear();
                            }
                        }
                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable client = () -> {
            try {
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try (var clientChannel = SocketChannel.open()) {
                // 连接服务器
                clientChannel.connect(new InetSocketAddress("localhost", 8080));
                int port = getPort(clientChannel.getLocalAddress());
                // 发送消息给服务器
                for (int i = 0; i < 2; i++) {
                    String request = String.valueOf(port);
                    ByteBuffer buffer = ByteBuffer.allocate(32);
                    buffer.put(request.getBytes());
                    buffer.flip();
                    clientChannel.write(buffer);
                    buffer.clear();
                    System.out.format("client(%d)--> request: %d\n", port, port);
                    // 读取服务器响应
                    int bytesRead = clientChannel.read(buffer);
                    buffer.flip();
                    byte[] responseBytes = new byte[bytesRead];
                    buffer.get(responseBytes);
                    String response = new String(responseBytes);
                    System.out.format("client(%d)--> response: %s\n", port, response);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.format("client(%d)--> bye\n", port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        LocalDateTime start = LocalDateTime.now();

        Thread serverThread = new Thread(server);
        serverThread.start();
        Thread.sleep(100);

        Thread clientThread1 = new Thread(client);
        clientThread1.start();

        Thread clientThread2 = new Thread(client);
        clientThread2.start();

        Thread clientThread3 = new Thread(client);
        clientThread3.start();

        clientThread1.join();
        clientThread2.join();
        clientThread3.join();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("time cost: " + Duration.between(start, end).toMillis() + "ms");

    }

    private int getPort(SocketAddress socketAddress) {
        return ((InetSocketAddress) socketAddress).getPort();
    }
}
