package com.gengine.client.interfaces;

import com.gengine.client.IClientApplication;
import com.gengine.client.shader.ShaderProgram;

public interface IRenderable {

    public void render(IClientApplication app, ShaderProgram shader);
}
