import java.awt.image.BufferedImage;
import java.util.Map;
import Shader.*;

public class z2 {

    public static void main(String[] args) {
        BufferedImage image = MyUtils.load("dragon.jpeg");
        int image_w = image.getWidth();
        int image_h = image.getHeight();


        int packedPickedColor =
                MyUtils.int2RGB(22, 22, 22);
//                MyUtils.int2RGB(Integer.parseInt( args[0].trim() ), Integer.parseInt( args[1].trim() ), Integer.parseInt( args[2].trim() ));

        for (Map.Entry<Shaders, Shader> entry: MyUtils.shadersMap.entrySet()) {
            Shaders k = entry.getKey();
            Shader v = entry.getValue();

            if (v instanceof RingShader) {
                ((RingShader) v).setX_c(image_w / 2);
                ((RingShader) v).setY_c(image_h / 2);
            }

            MyUtils.save("z2/"+k.name() + ".jpg", Mask.merge(image, packedPickedColor, false, v));
        }
    }
}
