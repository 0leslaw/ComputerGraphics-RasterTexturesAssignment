import Shader.Shader;

import java.awt.image.BufferedImage;

public class Mask {
    public static BufferedImage merge(BufferedImage mergedImage, int mergedColorPacked, boolean switched, Shader shader) {
        int res_x = mergedImage.getWidth();
        int res_y = mergedImage.getHeight();

        BufferedImage completeImage = new BufferedImage(res_x, res_y, BufferedImage.TYPE_INT_RGB);

        for (int j = 0; j < res_x; j++) for (int i = 0; i < res_y; i++)
        {
            if (switched)
                completeImage.setRGB(j, i, mixPixels(mergedColorPacked, mergedImage.getRGB(j, i), shader.calcMask(j, i)));
            else
                completeImage.setRGB(j, i, mixPixels(mergedImage.getRGB(j, i), mergedColorPacked, shader.calcMask(j, i)));

        }

        return completeImage;

    }

    public static BufferedImage merge(BufferedImage mergedImage1, BufferedImage mergedImage2, boolean switched, Shader shader) {
        int res_x = Math.min(mergedImage1.getWidth(), mergedImage2.getWidth());
        int res_y = Math.min(mergedImage1.getHeight(), mergedImage2.getHeight());

        BufferedImage completeImage = new BufferedImage(res_x, res_y, BufferedImage.TYPE_INT_RGB);

        for (int j = 0; j < res_x; j++) for (int i = 0; i < res_y; i++)
        {
            if (switched)
                completeImage.setRGB(j, i, mixPixels( mergedImage2.getRGB(j, i), mergedImage1.getRGB(j, i), shader.calcMask(j, i)));
            else
                completeImage.setRGB(j, i, mixPixels( mergedImage1.getRGB(j, i), mergedImage2.getRGB(j, i), shader.calcMask(j, i)));

        }

        return completeImage;

    }
    public static int mixPixels(int packed1, int packed2, double shadeValue) {
        int[] unpacked1 = MyUtils.RGB2int(packed1);
        int[] unpacked2 = MyUtils.RGB2int(packed2);
        int[] complete = new int[3];
        for (int i = 0; i < 3; i++) {
            complete[i] = (int)(shadeValue * unpacked1[i] + (1 - shadeValue) * unpacked2[i]);
        }
        return MyUtils.int2RGB(complete[0], complete[1], complete[2]);
    }
}
