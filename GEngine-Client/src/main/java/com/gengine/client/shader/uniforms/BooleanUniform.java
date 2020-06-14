package com.gengine.client.shader.uniforms;

import com.gengine.client.shader.Uniform;
import org.lwjgl.opengl.GL20;

public class BooleanUniform extends Uniform<Boolean> {

    public BooleanUniform(String name) {
        super(name);
    }

    @Override
    public void store(Boolean toStore) {
        GL20.glUniform1i(getLocation(), toStore ? 1 : 0);
    }
}
