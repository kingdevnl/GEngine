package com.gengine.testing.layers;

import com.gengine.testing.shaders.TestShader;
import nl.kingdev.gengine.client.IClientApplication;
import nl.kingdev.gengine.client.layer.IRenderLayer;
import nl.kingdev.gengine.client.mesh.Mesh;
import nl.kingdev.gengine.client.shader.ShaderProgram;
import org.lwjgl.opengl.GL11;

public class TestLayer implements IRenderLayer {

    private ShaderProgram shader;
    private Mesh mesh;

    @Override
    public void setup() {
        shader = new TestShader();
        mesh = new Mesh(new float[]{
                -1, -1, -3f,
                1, -1, -3f,
                0, 1, -3f,
        }, new int[]{0, 1, 2});

    }


    @Override
    public void render(IClientApplication app) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        shader.bind();
        mesh.render(app);
        shader.unbind();
        GL11.glDisable(GL11.GL_DEPTH_TEST);

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
