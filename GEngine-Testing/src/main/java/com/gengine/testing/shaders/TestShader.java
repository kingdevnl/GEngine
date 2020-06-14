package com.gengine.testing.shaders;

import com.gengine.client.math.MatrixUtils;
import com.gengine.client.shader.ShaderProgram;
import com.gengine.client.shader.uniforms.BooleanUniform;
import com.gengine.client.shader.uniforms.IntegerUniform;
import com.gengine.client.shader.uniforms.MatrixUniform;
import com.gengine.client.shader.uniforms.TextureSamplerUniform;
import org.joml.Matrix4f;

public class TestShader extends ShaderProgram {

    public MatrixUniform projectionMatrix = new MatrixUniform("projectionMatrix");
    public MatrixUniform modelMatrix = new MatrixUniform("modelMatrix");

    public TextureSamplerUniform textureSampler = new TextureSamplerUniform("texture_sampler");
    public BooleanUniform hasTexture = new BooleanUniform("hasTexture");

    public TestShader() {
        super("test/vertex.glsl", "test/fragment.glsl");

        setup();
    }

    @Override
    protected void setup() {

        bind();
        loadUniform(projectionMatrix, modelMatrix, textureSampler, hasTexture);

        projectionMatrix.store(MatrixUtils.projectionMatrix);
        modelMatrix.store(new Matrix4f());
        textureSampler.store(0);
        hasTexture.store(false);
        unbind();
    }
}
