package com.gengine.testing;

import com.gengine.client.camera.Camera;
import com.gengine.client.layer.layers.GuiLayer;
import com.gengine.common.logging.Logger;
import com.gengine.testing.layers.TestLayer;
import com.gengine.testing.states.TestState;
import lombok.Getter;
import com.gengine.client.GEngine;
import com.gengine.client.IClientApplication;
import com.gengine.client.layer.IRenderLayer;
import com.gengine.client.window.Window;

public class TestGame implements IClientApplication {


    @Getter
    private Window window;

    @Getter
    private Logger logger = new Logger(TestGame.class);

    @Getter
    private Camera camera = new Camera();

    @Override
    public void boot() {
        logger.info("Starting up.");

    }

    @Override
    public Window createWindow() {
        window = new Window(1080, 720, "GEngine-Testing");
        return window.create();
    }

    @Override
    public void startLoop() {
        window.setVisible(true);

        GEngine.getInstance().setGameState(this,new TestState());




        while (!window.isCloseRequested()) {
            GEngine.getInstance().update(this,window);
            GEngine.getInstance().render(this,window);
        }
    }



    @Override
    public void shutdown() {

        GEngine.getInstance().getGameState().destroy();




    }


    public static void main(String[] args) {
        GEngine.getInstance().bootApplication(new TestGame());
    }

}
