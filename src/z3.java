import Shader.*;

import java.awt.image.BufferedImage;
import java.util.Map;

public class z3 {
    public static void main(String[] args) {
        BufferedImage image1 = MyUtils.load("dragon.jpeg");
        BufferedImage image2 = MyUtils.load("city.jpg");

        int image_w = Math.min(image1.getWidth(), image2.getWidth());
        int image_h = Math.min(image1.getHeight(), image2.getHeight());

        for (Map.Entry<Shaders, Shader> entry: MyUtils.shadersMap.entrySet()) {
            Shaders k = entry.getKey();
            Shader v = entry.getValue();

            if (v instanceof RingShader) {
                ((RingShader) v).setX_c(image_w / 2);
                ((RingShader) v).setY_c(image_h / 2);
            }

            MyUtils.save("z3/"+k.name() + ".jpg", Mask.merge(image1, image2, false, v));
        }
    }
}
