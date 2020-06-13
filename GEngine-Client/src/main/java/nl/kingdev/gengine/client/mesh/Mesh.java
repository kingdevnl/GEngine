package nl.kingdev.gengine.client.mesh;

import com.gengine.common.interfaces.IDestroyable;
import lombok.Getter;
import nl.kingdev.gengine.client.IClientApplication;
import nl.kingdev.gengine.client.interfaces.IRenderable;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh implements IDestroyable, IRenderable {
    @Getter
    private int vao, vbo, idxVbo, vertexCount;

    public Mesh(float[] positions, int[] indices) {
        FloatBuffer verticesBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
            verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
            vertexCount = indices.length;
            verticesBuffer.put(positions).flip();


            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();

            vao = glGenVertexArrays();
            glBindVertexArray(vao);

            vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glBindBuffer(GL_ARRAY_BUFFER, 0);


            idxVbo = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVbo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
            glBindVertexArray(0);
        } finally {
            if (verticesBuffer != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
            if(indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }


    @Override
    public void render(IClientApplication app) {
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    @Override
    public void destroy() {
        glDisableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vbo);
        glDeleteBuffers(idxVbo);
        glDeleteVertexArrays(vao);

    }

}
