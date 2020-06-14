package com.gengine.testing.layers;

import com.gengine.client.io.ModelLoader;
import com.gengine.client.texture.Texture;
import com.gengine.testing.shaders.TestShader;
import com.gengine.client.IClientApplication;
import com.gengine.client.gameobject.GameObject;
import com.gengine.client.layer.IRenderLayer;
import com.gengine.client.math.MatrixUtils;
import com.gengine.client.mesh.Mesh;
import org.lwjgl.opengl.GL11;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public class TestLayer implements IRenderLayer {

    private TestShader shader;
    private Mesh mesh;

    private GameObject gameObject;

    @Override
    public void setup() {
        shader = new TestShader();
        mesh = ModelLoader.loadMesh("/assets/models/block.obj");
        mesh.setTexture(Texture.loadTexture("/assets/textures/UV.png"));

        gameObject = new GameObject(mesh);

        gameObject.setPosition(0,0, -25f);


    }


    @Override
    public void render(IClientApplication app) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        shader.bind();

        shader.modelMatrix.store(MatrixUtils.getModelMatrix(gameObject));
        shader.hasTexture.store(gameObject.getMesh().getTexture() != null);
        gameObject.render(app, shader);




        gameObject.getRotation().y +=0.5f;
        gameObject.getRotation().z +=0.5f;

        shader.unbind();


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
