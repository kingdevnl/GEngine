package com.gengine.testing;

import com.gengine.client.layer.layers.GuiLayer;
import com.gengine.common.logging.Logger;
import com.gengine.testing.layers.TestLayer;
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
        GEngine.getInstance().pushLayer(new TestLayer());

        GEngine.getInstance().pushLayer(new GuiLayer());




        while (!window.isCloseRequested()) {
            GEngine.getInstance().update(this,window);
            GEngine.getInstance().render(this,window);
        }
    }



    @Override
    public void shutdown() {

        for (int i = 0; i < GEngine.getInstance().getLayers().size(); i++) {
            IRenderLayer iRenderLayer = GEngine.getInstance().getLayers().get(i);
            iRenderLayer.destroy();
            GEngine.getInstance().getLayers().remove(iRenderLayer);

        }

    }



    public static void main(String[] args) {
        GEngine.getInstance().bootApplication(new TestGame());
    }

}
