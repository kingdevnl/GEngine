package com.gengine.common.utils;

import java.util.List;

public class ArrayUtils {

    public static float[] floatList(List<Float> floats) {
        float[] list = new float[floats.size()];
        for (int i = 0; i < floats.size(); i++) {
            list[i] = floats.get(i);
        }
        return list;
    }
    public static int[] intList(List<Integer> ints) {
        int[] list = new int[ints.size()];
        for (int i = 0; i < ints.size(); i++) {
            list[i] = ints.get(i);
        }
        return list;
    }
}
