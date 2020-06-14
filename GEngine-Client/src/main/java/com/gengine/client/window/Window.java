package com.gengine.client.window;

import com.gengine.common.interfaces.IDestroyable;
import com.gengine.common.logging.Logger;
import lombok.Getter;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window implements IDestroyable {

    private static Logger logger = new Logger(Window.class);

    @Getter
    private int width, height;

    private String title;
    @Getter
    private long handle;


    private List<Integer> keysDown = new ArrayList<>();


    public Window(int width, int height, String title) {

        this.width = width;
        this.height = height;
        this.title = title;

    }

    public Window create() {
        logger.info("Setting up GLFW window.");

        if (!glfwInit()) {
            logger.error("Failed to init GLFW!");
            return null;
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        handle = glfwCreateWindow(width, height, title, 0, 0);

        glfwSetKeyCallback(handle, (window, key, scancode, action, mods) -> {
            if(action == GLFW_PRESS) {
                if(!keysDown.contains(key)) {
                    keysDown.add(key);
                }
            } else if(action == GLFW_RELEASE) {
                keysDown.remove((Object)key);
            }
        });
        return this;
    }

    public void clear() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

    }

    public void update() {
        glfwPollEvents();
    }

    @Override
    public void destroy() {
        glfwDestroyWindow(handle);
    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(handle);
    }


    public void setVisible(boolean v) {
        if(v) {
            glfwShowWindow(handle);
            glfwMakeContextCurrent(handle);
            GL.createCapabilities();

            return;
        }
        glfwHideWindow(handle);

    }

    public boolean isKeyDown(int key) {
        return keysDown.contains(key);
    }


}
