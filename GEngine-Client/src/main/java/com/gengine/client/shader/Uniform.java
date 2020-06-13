package com.gengine.client.shader;

import lombok.Getter;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

@Getter
public abstract class Uniform<T> {

    private int location = -1;
    private String name;

    public Uniform(String name) {
        this.name = name;
    }
    public void load(ShaderProgram shader) {
        this.location = glGetUniformLocation(shader.getProgram(), name);
        if(location < 0) {
            ShaderProgram.getLogger().error(String.format("Failed to find uniform %s", name));
        }
    }
    public abstract void store(T toStore);

}
