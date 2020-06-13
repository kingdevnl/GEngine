package com.gengine.client;

import com.gengine.client.window.Window;

public interface IClientApplication {

     void boot();

    Window createWindow();
    Window getWindow();

    void shutdown();


    void startLoop();

}
