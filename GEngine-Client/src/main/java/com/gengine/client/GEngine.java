package com.gengine.client;

import com.gengine.client.math.MatrixUtils;
import com.gengine.client.state.GameState;
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
    private IClientApplication clientApplication;


    @Getter
    private GameState gameState;

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


        gameState.update(application);


        window.update();

    }

    public void render(IClientApplication application, Window window) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        gameState.render(application);
        GLFW.glfwSwapBuffers(window.getHandle());
    }


    public void setGameState(IClientApplication app, GameState state) {
        this.gameState = state;
        this.gameState.setup(app);
    }
}
