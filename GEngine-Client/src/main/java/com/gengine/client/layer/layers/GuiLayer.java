package com.gengine.client.layer.layers;

import com.gengine.client.IClientApplication;
import com.gengine.client.io.IOUtils;
import com.gengine.client.layer.IRenderLayer;
import com.gengine.client.window.Window;
import com.gengine.common.logging.Logger;
import lombok.SneakyThrows;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.gengine.client.utils.ColorUtils.rgba;
import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;
import static org.lwjgl.opengl.GL11.*;

public class GuiLayer implements IRenderLayer {

    private long vg;
    private NVGColor color;

    private final String FONT_NAME = "Robotto";
    private ByteBuffer fontBuffer;
    private int font;

    private Logger logger = new Logger(GuiLayer.class);
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @SneakyThrows
    public void setup() {
        vg = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
        if (vg == 0) {
            logger.error("Failed to init nanoVG!");
            return;
        }
        color = NVGColor.create();

        fontBuffer = IOUtils.ioResourceToByteBuffer("/fonts/Roboto.ttf", 150 * 1024);
        font = nvgCreateFontMem(vg, FONT_NAME, fontBuffer, 0);
        if(font == -1) {
            logger.error("Failed to create font "+FONT_NAME);
            return;
        }

    }

    public void renderTextAlfa(int x, int y, String text, float fontSize, float alfa) {
        nvgFontSize(vg, fontSize);
        nvgFontFace(vg, FONT_NAME);
        nvgTextAlign(vg, NVG_ALIGN_LEFT | NVG_ALIGN_TOP);
        nvgFillColor(vg, rgba(255, 0, 0, alfa, color));
        nvgText(vg, x, y, text);

    }



    @Override
    public void update() {

    }

    @Override
    public void render(IClientApplication app) {

        Window window = app.getWindow();


        nvgBeginFrame(vg, window.getWidth(), window.getHeight(), 1);
        renderTextAlfa(10,10, "NanoVG", 24,255 );
        nvgEndFrame(vg);


        glDisable(GL_CULL_FACE);

        glEnable(GL_DEPTH_TEST);

    }

    @Override
    public void destroy() {
        if (fontBuffer != null) {
            MemoryUtil.memFree(fontBuffer);
        }
        nvgDelete(vg);
    }
}
