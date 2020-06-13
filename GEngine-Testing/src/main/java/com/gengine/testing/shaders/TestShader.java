package com.gengine.testing.shaders;

import nl.kingdev.gengine.client.math.MatrixUtils;
import nl.kingdev.gengine.client.shader.ShaderProgram;
import nl.kingdev.gengine.client.shader.uniforms.MatrixUniform;
import org.joml.Matrix4f;

public class TestShader extends ShaderProgram {

    public MatrixUniform projectionMatrix = new MatrixUniform("projectionMatrix");
    public MatrixUniform modelMatrix = new MatrixUniform("modelMatrix");

    public TestShader() {
        super("test/vertex.glsl", "test/fragment.glsl");

        setup();
    }

    @Override
    protected void setup() {

        bind();
        loadUniform(projectionMatrix);
        loadUniform(modelMatrix);
        projectionMatrix.store(MatrixUtils.projectionMatrix);
        modelMatrix.store(new Matrix4f());
        unbind();
    }
}
