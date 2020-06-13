package nl.kingdev.gengine.client.mesh;

import com.gengine.common.interfaces.IDestroyable;
import lombok.Getter;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh implements IDestroyable {
    @Getter
    private int vao, vbo, idxVbo, vertexCount;

    public Mesh(float[] positions) {
        FloatBuffer verticesBuffer = null;
        try {
            verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
            vertexCount = positions.length / 3;
            verticesBuffer.put(positions).flip();

            vao = glGenVertexArrays();
            glBindVertexArray(vao);

            vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            glBindVertexArray(0);
        } finally {
            if (verticesBuffer  != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
        }
    }

    @Override
    public void destroy() {
        glDisableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vbo);
//        glDeleteBuffers(idxVbo);
        glDeleteVertexArrays(vao);

    }
    public void render() {
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glBindVertexArray(0);
    }
}
