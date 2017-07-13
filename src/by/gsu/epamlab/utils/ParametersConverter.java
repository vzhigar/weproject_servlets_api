package by.gsu.epamlab.utils;

import java.util.ArrayList;
import java.util.List;

public class ParametersConverter {
    public static List<Integer> convert(String[] parameters) {
        List<Integer> params = new ArrayList<>();
        for (String p : parameters) {
            params.add(Integer.parseInt(p));
        }
        return params;
    }
}

