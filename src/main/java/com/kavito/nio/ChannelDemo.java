package com.kavito.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: Channel通道：
 * FileChannel	从文件中读写数据
 * DatagramChannel	 能通过UDP读写网络中的数据。
 * SocketChannel	能通过TCP读写网络中的数据。
 * ServerSocketChannel	可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 *
 * @Author: kavito
 * @Date: 2019/12/30 2:51 下午
 */
public class ChannelDemo {
    public static void finleChannleDemo() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 数据从channel(通道)读取到buffer(缓冲区)，也可以从缓冲区写入到channel
        int bytesRead = inChannel.read(buf);

        while(bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();
    }

    public static void main(String[] args) {
        try {
            finleChannleDemo();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
