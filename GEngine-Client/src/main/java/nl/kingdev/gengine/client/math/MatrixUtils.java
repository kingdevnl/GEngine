package nl.kingdev.gengine.client.math;

import nl.kingdev.gengine.client.IClientApplication;
import org.joml.Matrix4f;

public class MatrixUtils {

    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;
    private static final float FOV = (float) Math.toRadians(60.0f);

    public static Matrix4f projectionMatrix;


    public static void setup(IClientApplication app) {
        float aspect = (float) app.getWindow().getWidth() / app.getWindow().getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspect, Z_NEAR, Z_FAR);

        System.out.println("MatrixUtils.setup");
    }


}
