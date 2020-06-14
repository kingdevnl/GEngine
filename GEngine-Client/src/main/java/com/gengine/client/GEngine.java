package com.gengine.client;

import com.gengine.client.math.MatrixUtils;
import lombok.Getter;
import com.gengine.client.layer.IRenderLayer;
import com.gengine.client.window.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GEngine {

    @Getter
    private static GEngine instance = new GEngine();
    @Getter
    private List<IRenderLayer> layers = new ArrayList<>();

    @Getter
    private IClientApplication clientApplication;

    public void bootApplication(IClientApplication app) {
        this.clientApplication = app;
        app.boot();
        app.createWindow();
        MatrixUtils.setup(app);
        app.startLoop();
        app.shutdown();
    }

    public void update(IClientApplication application, Window window) {
        window.clear();


        layers.forEach(iRenderLayer -> iRenderLayer.update(application));

        window.update();

    }

    public void render(IClientApplication application, Window window) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        layers.forEach(layer -> layer.render(application));

        GLFW.glfwSwapBuffers(window.getHandle());
    }

    public void pushLayer(IRenderLayer layer) {
        layer.setup();
        layers.add(layer);
    }
    public void popLayer(IRenderLayer layer) {
        layers.remove(layer);
    }
}
