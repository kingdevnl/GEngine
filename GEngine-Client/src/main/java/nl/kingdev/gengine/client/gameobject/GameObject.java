package nl.kingdev.gengine.client.gameobject;

import com.gengine.common.interfaces.IDestroyable;
import lombok.Getter;
import lombok.Setter;
import nl.kingdev.gengine.client.IClientApplication;
import nl.kingdev.gengine.client.interfaces.IRenderable;
import nl.kingdev.gengine.client.mesh.Mesh;
import nl.kingdev.gengine.client.shader.ShaderProgram;
import org.joml.Vector3f;

public class GameObject implements IRenderable, IDestroyable {

    @Getter
    @Setter
    private Mesh mesh;


    @Getter
    private Vector3f position, rotation;
    @Getter
    private float scale;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        this.position = new Vector3f(0, 0, 0);
        this.rotation = new Vector3f(0, 0, 0);
        this.scale = 1f;
    }

    public void setPosition(float x, float y, float z) {
        getPosition().x = x;
        getPosition().y = y;
        getPosition().z = z;
    }

    public void setRotation(float x, float y, float z) {
        getRotation().x = x;
        getRotation().y = y;
        getRotation().z = z;
    }


    @Override
    public void render(IClientApplication app, ShaderProgram shader) {
        if (mesh != null) {
            mesh.render(app, shader);
        }
    }

    @Override
    public void destroy() {
        if (mesh != null) {
            mesh.destroy();
        }
    }
}
