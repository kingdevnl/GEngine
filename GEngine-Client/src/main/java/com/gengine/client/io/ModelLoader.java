package com.gengine.client.io;

import com.gengine.client.mesh.Mesh;
import com.gengine.common.logging.Logger;
import com.gengine.common.utils.ArrayUtils;
import lombok.SneakyThrows;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import org.lwjgl.system.MemoryUtil;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.assimp.Assimp.*;

public class ModelLoader {

    private static Logger logger = new Logger(ModelLoader.class);

    @SneakyThrows
    public static Mesh loadMesh(String file) {

        InputStream resourceAsStream = ModelLoader.class.getResourceAsStream(file);

        byte[] bytes = resourceAsStream.readAllBytes();

        ByteBuffer buffer = MemoryUtil.memAlloc(bytes.length);
        buffer.put(bytes).flip();

        AIScene aiScene  = aiImportFileFromMemory(buffer,aiProcess_JoinIdenticalVertices | aiProcess_Triangulate, "obj");


        if (aiScene == null) {
            String s = aiGetErrorString();
            logger.error(String.format("Failed to load file %s %s", file, s));
        }

        PointerBuffer mMeshes = aiScene.mMeshes();

        AIMesh aiMesh = AIMesh.create(mMeshes.get(0));

        return proccess(aiMesh);
    }

    private static Mesh proccess(AIMesh aiMesh) {
        List<Float> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        List<Float> textcoords = new ArrayList<>();

        processVertices(aiMesh, vertices);
        processIndices(aiMesh, indices);
        processTextCoords(aiMesh, textcoords);


        return new Mesh(ArrayUtils.floatList(vertices), ArrayUtils.intList(indices), ArrayUtils.floatList(textcoords));
    }

    private static void processIndices(AIMesh aiMesh, List<Integer> indices) {

        int mNunFaces = aiMesh.mNumFaces();
        AIFace.Buffer aiFaces = aiMesh.mFaces();
        for (int i = 0; i < mNunFaces; i++) {
            AIFace aiFace = aiFaces.get(i);
            IntBuffer buff = aiFace.mIndices();
            while (buff.remaining() > 0) {
                indices.add(buff.get());
            }
        }
    }
    private static void processTextCoords(AIMesh aiMesh, List<Float> textures) {
        AIVector3D.Buffer textCoords = aiMesh.mTextureCoords(0);
        int numTextCoords = textCoords != null ? textCoords.remaining() : 0;
        for (int i = 0; i < numTextCoords; i++) {
            AIVector3D textCoord = textCoords.get();
            textures.add(textCoord.x());
            textures.add(1 - textCoord.y());
        }
    }

    private static void processVertices(AIMesh aiMesh, List<Float> vertices) {
        AIVector3D.Buffer aiVerts = aiMesh.mVertices();
        while (aiVerts.remaining() > 0) {
            AIVector3D aiVector3D = aiVerts.get();
            vertices.add(aiVector3D.x());
            vertices.add(aiVector3D.y());
            vertices.add(aiVector3D.z());
        }
    }
}
