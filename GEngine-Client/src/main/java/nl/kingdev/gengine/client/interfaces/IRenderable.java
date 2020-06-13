package nl.kingdev.gengine.client.interfaces;

import nl.kingdev.gengine.client.IClientApplication;
import nl.kingdev.gengine.client.shader.ShaderProgram;

public interface IRenderable {

    public void render(IClientApplication app, ShaderProgram shader);
}
