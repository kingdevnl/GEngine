package com.gengine.testing.layers;

import com.gengine.testing.shaders.TestShader;
import nl.kingdev.gengine.client.IClientApplication;
import nl.kingdev.gengine.client.layer.IRenderLayer;
import nl.kingdev.gengine.client.mesh.Mesh;
import nl.kingdev.gengine.client.shader.ShaderProgram;
public class TestLayer implements IRenderLayer {

    private ShaderProgram shader;
    private Mesh mesh;

    @Override
    public void setup() {
        shader = new TestShader();
        mesh = new Mesh(new float[]{
                -1, -1, -2f,
                1, -1, -2f,
                0, 1, -2f,
        });
    }


    @Override
    public void render(IClientApplication app) {
        shader.bind();
        mesh.render();
        shader.unbind();
    }


    @Override
    public void update() {
    }

    @Override
    public void destroy() {
        mesh.destroy();
        shader.destroy();
    }
}
