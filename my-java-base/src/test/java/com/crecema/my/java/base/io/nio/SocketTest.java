package com.crecema.my.java.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketTest {

    private static class server {
        public void start() throws IOException {
            try (var selector = Selector.open(); var serverSocketChannel = ServerSocketChannel.open()) {
                serverSocketChannel.configureBlocking(false);
                // 绑定服务器地址和端口号
                // serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8080));
                serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8080));
                // 注册ServerSocketChannel到Selector并监听ACCEPT事件
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("服务器启动，等待客户端连接...");

                while (true) {
                    // 阻塞等待事件发生
                    selector.select();
                    // 获取所有发生的事件
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectedKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            // 处理连接事件
                            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                            // 获取客户端连接
                            SocketChannel clientChannel = channel.accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            // 处理读事件
                            SocketChannel channel = (SocketChannel) key.channel();
                            // 读取数据
                            ByteBuffer buffer = ByteBuffer.allocate(32);
                            int size;
                            while ((size = channel.read(buffer)) != -1) {
                                buffer.flip();
                                System.out.println("receive request: " + new String(buffer.array(), 0, size));
                                buffer.clear();
                            }
                        }
                        // 移除已处理的事件
                        iterator.remove();
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class client {
        private static final String HOST = "127.0.0.1";
        private static final int PORT = 8080;
        private static final String[] REQUESTS = {"hello ", "I am ", "V ", "!!!"};
        public void start() throws IOException {
            try (SocketChannel socketChannel = SocketChannel.open()) {
                System.out.println("client started, connecting to server: " + HOST + ":" + PORT);
                socketChannel.connect(new InetSocketAddress(HOST, PORT));
                System.out.println("client connect success: " + socketChannel.getRemoteAddress());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                new SocketTest.server().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new SocketTest.client().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
