package com.gengine.testing.layers;

import com.gengine.testing.shaders.TestShader;
import com.gengine.client.IClientApplication;
import com.gengine.client.gameobject.GameObject;
import com.gengine.client.layer.IRenderLayer;
import com.gengine.client.math.MatrixUtils;
import com.gengine.client.mesh.Mesh;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class TestLayer implements IRenderLayer {

    private TestShader shader;
    private Mesh mesh;

    private GameObject gameObject;

    @Override
    public void setup() {
        shader = new TestShader();
        mesh = new Mesh(new float[]{
                // VO
                -0.5f,  0.5f,  0.5f,
                // V1
                -0.5f, -0.5f,  0.5f,
                // V2
                0.5f, -0.5f,  0.5f,
                // V3
                0.5f,  0.5f,  0.5f,
                // V4
                -0.5f,  0.5f, -0.5f,
                // V5
                0.5f,  0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,
        }, new int[]{
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                4, 0, 3, 5, 4, 3,
                // Right face
                3, 2, 7, 5, 3, 7,
                // Left face
                6, 1, 0, 6, 0, 4,
                // Bottom face
                2, 1, 6, 2, 6, 7,
                // Back face
                7, 6, 4, 7, 4, 5,
        });
        gameObject = new GameObject(mesh);

        gameObject.setPosition(0,0, -2.4f);

    }


    @Override
    public void render(IClientApplication app) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        shader.bind();

        shader.modelMatrix.store(MatrixUtils.getModelMatrix(gameObject));
        gameObject.render(app, shader);




        gameObject.getRotation().y +=0.5f;
        gameObject.getRotation().z +=0.5f;

        shader.unbind();

        GL11.glDisable(GL11.GL_DEPTH_TEST);

    }


    @Override
    public void update() {
    }

    @Override
    public void destroy() {
        gameObject.destroy();
        shader.destroy();
    }
}
