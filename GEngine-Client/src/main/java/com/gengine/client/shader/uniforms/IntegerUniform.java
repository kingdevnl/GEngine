package com.gengine.client.shader.uniforms;

import com.gengine.client.shader.Uniform;
import org.lwjgl.opengl.GL20;


public class IntegerUniform extends Uniform<Integer> {

    public IntegerUniform(String name) {
        super(name);
    }

    @Override
    public void store(Integer toStore) {
        GL20.glUniform1i(getLocation(), toStore);
    }
}
