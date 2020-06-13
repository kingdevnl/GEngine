package nl.kingdev.gengine.client;

import lombok.Getter;
import nl.kingdev.gengine.client.layer.IRenderLayer;
import nl.kingdev.gengine.client.math.MatrixUtils;
import nl.kingdev.gengine.client.window.Window;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class GEngine {

    @Getter
    private static GEngine instance = new GEngine();

    @Getter
    private List<IRenderLayer> layers = new ArrayList<>();


    public void bootApplication(IClientApplication app) {
        app.boot();
        app.createWindow();
        MatrixUtils.setup(app);
        app.startLoop();
        app.shutdown();
    }

    public void update(IClientApplication application, Window window) {
        window.clear();

        layers.forEach(IRenderLayer::update);

        window.update();

    }

    public void render(IClientApplication application, Window window) {
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
