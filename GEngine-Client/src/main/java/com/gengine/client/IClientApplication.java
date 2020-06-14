package com.gengine.client;

import com.gengine.client.camera.Camera;
import com.gengine.client.window.Window;

public interface IClientApplication {

     void boot();

    Window createWindow();
    Window getWindow();

    Camera getCamera();


    void shutdown();


    void startLoop();

}
