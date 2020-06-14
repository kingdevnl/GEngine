package com.gengine.client.state;

import com.gengine.client.IClientApplication;

import com.gengine.client.interfaces.IRenderable;
import com.gengine.client.interfaces.ITickable;
import com.gengine.client.layer.IRenderLayer;
import com.gengine.common.interfaces.IDestroyable;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements IDestroyable, ITickable {

    private List<IRenderLayer> layers = new ArrayList<>();

    public void setup(IClientApplication app) {
        layers.forEach(layer -> layer.setup());
    }

    public void render(IClientApplication app) {
        layers.forEach(layer -> layer.render(app));
    }

    @Override
    public void update(IClientApplication app) {
        layers.forEach(layer -> layer.update(app));
    }

    @Override
    public void destroy() {
        layers.forEach(iRenderLayer -> {
            iRenderLayer.destroy();
        });
    }
    protected void pushLayer(IRenderLayer layer) {
        layers.add(layer);
    }

    protected void popLayer(IRenderLayer layer) {
        layers.remove(layer);
    }

}
