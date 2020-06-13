package nl.kingdev.gengine.client.shader.uniforms;

import nl.kingdev.gengine.client.shader.Uniform;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class MatrixUniform extends Uniform<Matrix4f> {


    public MatrixUniform(String name) {
        super(name);
    }

    @Override
    public void store(Matrix4f toStore) {
        if(getLocation() != -1) {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                glUniformMatrix4fv(getLocation(), false, toStore.get(stack.mallocFloat(16)));
            }
        }
    }
}
