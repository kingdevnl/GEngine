package com.gengine.client.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import static org.lwjgl.system.MemoryUtil.memAlloc;
import static org.lwjgl.system.MemoryUtil.memRealloc;

public class IOUtils {

    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize)
            throws IOException {
        ByteBuffer buffer;

        File file = new File(resource);
        if (file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();

            buffer = memAlloc((int) fc.size() + 1);

            while (fc.read(buffer) != -1) {
            }

            fis.close();
            fc.close();
        } else {
            int size = 0;
            try (InputStream source = IOUtils.class.getResourceAsStream(resource)) {
                buffer = memAlloc(source.available());


                if (source == null)
                    throw new FileNotFoundException(resource);
                try (ReadableByteChannel rbc = Channels.newChannel(source)) {
                    while (true) {
                        int bytes = rbc.read(buffer);
                        if (bytes == -1)
                            break;
                        size += bytes;
                        if (!buffer.hasRemaining()) {
                            buffer = memRealloc(buffer, size * 2);
                        }
                    }
                }
            }
            buffer = memRealloc(buffer, size + 1);
        }
        buffer.put((byte) 0);
        buffer.flip();

        return buffer;
    }
}