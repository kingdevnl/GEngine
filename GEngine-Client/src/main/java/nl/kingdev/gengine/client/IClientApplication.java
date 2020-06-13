package nl.kingdev.gengine.client;

import nl.kingdev.gengine.client.window.Window;

public interface IClientApplication {

     void boot();

    Window createWindow();
    Window getWindow();

    void shutdown();


    void startLoop();

}
