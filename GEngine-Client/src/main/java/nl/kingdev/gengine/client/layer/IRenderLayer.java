package nl.kingdev.gengine.client.layer;

import com.gengine.common.interfaces.IDestroyable;
import nl.kingdev.gengine.client.IClientApplication;

public interface IRenderLayer extends IDestroyable {
    public void setup();

    public void update();
    public void render(IClientApplication app);
}
