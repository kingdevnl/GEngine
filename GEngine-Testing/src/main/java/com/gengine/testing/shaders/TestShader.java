package com.gengine.testing.shaders;

import nl.kingdev.gengine.client.math.MatrixUtils;
import nl.kingdev.gengine.client.shader.ShaderProgram;
import nl.kingdev.gengine.client.shader.uniforms.MatrixUniform;

public class TestShader extends ShaderProgram {

    public MatrixUniform projectionMatrix = new MatrixUniform("projectionMatrix");

    public TestShader() {
        super("test/vertex.glsl", "test/fragment.glsl");

        setup();
    }

    @Override
    protected void setup() {

        bind();
        loadUniform(projectionMatrix);
        projectionMatrix.store(MatrixUtils.projectionMatrix);
        unbind();
    }
}
