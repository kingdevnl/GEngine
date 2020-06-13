package com.gengine.client.shader;

import com.gengine.common.interfaces.IDestroyable;
import com.gengine.common.logging.Logger;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
public class ShaderProgram implements IDestroyable {

    private List<Shader> shaders = new ArrayList<>();

    private List<Uniform<?>> uniforms = new ArrayList<>();

    @Getter
    private int program;

    private static Logger logger = new Logger(ShaderProgram.class);


    public ShaderProgram(String vertex, String fragment) {
        shaders.add(new Shader(GL_VERTEX_SHADER, vertex));
        shaders.add(new Shader(GL_FRAGMENT_SHADER, fragment));


        program = glCreateProgram();

        shaders.forEach(shader -> {
            glAttachShader(program, createShader(shader));
        });
        glLinkProgram(program);

        shaders.forEach(shader -> {
            glDetachShader(program, shader.id);
            glDeleteShader(shader.id);
        });
    }



    protected void setup() {

    }
    protected void loadUniform(Uniform uniform) {
        uniform.load(this);
        uniforms.add(uniform);
    }

    private int createShader(Shader shader) {
        int shaderID = shader.id = glCreateShader(shader.type);
        StringBuilder shaderSource = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/assets/shaders/"+shader.file)))) {
            String line;
            shaderSource.append("#version 330 core").append("\r\n");
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        glShaderSource(shaderID, shaderSource.toString());
        glCompileShader(shaderID);
        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            logger.error("Failed to compile shader "+glGetShaderInfoLog(shaderID));
        }

        return  shaderID;
    }

    public void bind() {
        glUseProgram(program);
    }
    public void unbind() {
        glUseProgram(0);
    }

    @Override
    public void destroy() {
        glDeleteProgram(program);
        shaders.clear();
    }


    public static Logger getLogger() {
        return logger;
    }

    private static class Shader {
        protected int id, type;
        protected String file;

        public Shader(int type, String file) {
            this.type = type;
            this.file = file;
        }
    }

}
