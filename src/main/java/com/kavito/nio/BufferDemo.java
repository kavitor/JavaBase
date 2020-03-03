package com.kavito.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: Buffer(缓冲区)
 * ByteBuffer
 * MappedByteBuffer
 * CharBuffer
 * DoubleBuffer
 * FloatBuffer
 * IntBuffer
 * LongBuffer
 * ShortBuffer
 *
 * buffer的三个重要属性：https://blog.csdn.net/qq_42917336/article/details/89093529
 *  **https://blog.csdn.net/pfnie/article/details/52829549
 * capacity
 * position
 * limit
 *
 *
 * 当向buffer写入数据时，buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。
 * 在读模式下，可以读取之前写入到buffer的所有数据。
 *
 * 一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。
 * clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。
 * 任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
 *
 * @Author: kavito
 * @Date: 2019/12/30 3:11 下午
 */
public class BufferDemo {
    public static void bufferDemo() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        // create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);
        // read into buffer.
        int bytesRead = inChannel.read(buf);

        while(bytesRead != -1) {
            // make buffer ready for read
            buf.flip();

            // Tells whether there are any elements between the current position and the limit.
            while(buf.hasRemaining()) {
                // read 1 byte at a time
                System.out.print((char) buf.get());
            }

            // make buffer ready for writing
            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();
    }

    public static void main(String[] args) {
        try {
            bufferDemo();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}