package com.gengine.client.layer;

import com.gengine.common.interfaces.IDestroyable;
import com.gengine.client.IClientApplication;

public interface IRenderLayer extends IDestroyable {
    public void setup();

    public void update(IClientApplication app);
    public void render(IClientApplication app);
}
