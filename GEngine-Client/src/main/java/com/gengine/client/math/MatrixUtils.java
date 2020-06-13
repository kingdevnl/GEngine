package com.gengine.client.math;

import com.gengine.client.IClientApplication;
import com.gengine.client.gameobject.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MatrixUtils {

    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;
    private static final float FOV = (float) Math.toRadians(60.0f);

    public static Matrix4f projectionMatrix = new Matrix4f();

    private static Matrix4f modelMatrix = new Matrix4f();


    public static void setup(IClientApplication app) {
        float aspect = (float) app.getWindow().getWidth() / app.getWindow().getHeight();
        projectionMatrix = projectionMatrix.perspective(FOV, aspect, Z_NEAR, Z_FAR);

        System.out.println("MatrixUtils.setup");
    }

    public static Matrix4f getModelMatrix(GameObject gameObject) {

        Vector3f rotation = gameObject.getRotation();

        return modelMatrix.translation(gameObject.getPosition()).
                rotateX((float)Math.toRadians(rotation.x)).
                rotateY((float)Math.toRadians(rotation.y)).
                rotateZ((float)Math.toRadians(rotation.z)).
                scale(gameObject.getScale());
    }

}
