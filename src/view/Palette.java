package view;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Palette {
    public final static Color foregroundColor = new Color(187, 173, 160);
    public final static Color backgroundColor = new Color(205, 193, 180);

    public final static Color fontColor1 = new Color(119, 110, 101);
    public final static Color fontColor2 = new Color(249, 246, 242);

    public static Map<String, Color> tileColors = new HashMap<String, Color>() {
        {
            put("2", new Color(238, 228, 218));
            put("4", new Color(238, 225, 201));
            put("8", new Color(243, 178, 122));
            put("16", new Color(246, 150, 100));
            put("32", new Color(247, 124, 95));
            put("64", new Color(247, 95, 59));
            put("128", new Color(237, 208, 115));
            put("256", new Color(237, 204, 98));
            put("512", new Color(237, 201, 80));
            put("1024", new Color(237, 197, 63));
            put("2048", new Color(237, 194, 46));
        }
    };
}
