package com.gengine.client.texture;

import com.gengine.common.interfaces.IDestroyable;
import com.gengine.common.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.opengl.GL15;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

@AllArgsConstructor
@Getter
public class Texture implements IDestroyable {

    private final int id;

    private static final Logger logger = new Logger(Texture.class);

    @Override
    public void destroy() {
        GL15.glDeleteTextures(id);
    }

    public static Texture loadTexture(String name) {
        try {
            PNGDecoder decoder = new PNGDecoder(Texture.class.getResourceAsStream(name));
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
            decoder.decode(byteBuffer, decoder.getWidth()*4, PNGDecoder.Format.RGBA);
            byteBuffer.flip();
            int id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            float glLin = 0x2601;
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, glLin);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, glLin);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);
            glGenerateMipmap(GL_TEXTURE_2D);

            logger.info(String.format("Loaded texture %s ID: %d", name, id));
            return new Texture(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
