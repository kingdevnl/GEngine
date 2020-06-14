package com.gengine.testing.states;

import com.gengine.client.IClientApplication;
import com.gengine.client.layer.layers.GuiLayer;
import com.gengine.client.state.GameState;
import com.gengine.testing.layers.TestLayer;

public class TestState extends GameState {


    @Override
    public void setup(IClientApplication app) {
        pushLayer(new TestLayer());
        pushLayer(new GuiLayer());
        super.setup(app);
    }

}
