package com.gengine.client.math;

import com.gengine.client.IClientApplication;
import com.gengine.client.camera.Camera;
import com.gengine.client.gameobject.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MatrixUtils {

    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;
    private static final float FOV = (float) Math.toRadians(60.0f);

    public static Matrix4f projectionMatrix = new Matrix4f();

    private static Matrix4f modelMatrix = new Matrix4f();
    private static Matrix4f viewMatrix = new Matrix4f();


    public static void setup(IClientApplication app) {
        float aspect = (float) app.getWindow().getWidth() / app.getWindow().getHeight();
        projectionMatrix = projectionMatrix.perspective(FOV, aspect, Z_NEAR, Z_FAR);

        System.out.println("MatrixUtils.setup");
    }

    public static Matrix4f getModelMatrix(GameObject gameObject, IClientApplication app) {

        Vector3f rotation = gameObject.getRotation();

        return modelMatrix.set(getViewMatrix(app.getCamera())).translate(gameObject.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameObject.getScale());
    }

    public static Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();

        viewMatrix.identity();
        // First do the rotation so camera rotates over its position
        viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
                .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        // Then do the translation
        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }
}
