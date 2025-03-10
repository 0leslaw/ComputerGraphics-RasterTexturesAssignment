import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import Shader.*;

public class MyUtils {



    public static Map<Shaders, Shader> shadersMap = Map.of(
        Shaders.RINGHARSH, new RingShader(0, 0, 10, false),
        Shaders.RINGSOFT, new RingShader(0, 0, 10, true),
        Shaders.GRID, new GridShader(20, 20, 5),
        Shaders.CHESSBOARD, new ChessBoardShader(20)
    );
    public static void save(String fileName, BufferedImage image) {
        try
        {
            ImageIO.write( image, "bmp", new File("creations/"+fileName) );
            System.out.println( "Image " + fileName + " created successfully");
        }
        catch (IOException e)
        {
            System.out.println( "The image cannot be stored" );
        }
    }

    public static BufferedImage load(String fileName) {
        File file = new File("assets/" + fileName);

        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static int grayify( int grayness)
    {
        return int2RGB(grayness, grayness, grayness);
    }

    static int int2RGB( int red, int green, int blue)
    {
        // Make sure that color intensities are in 0..255 range
        red   = red   & 0x000000FF;
        green = green & 0x000000FF;
        blue  = blue  & 0x000000FF;

        // Assemble packed RGB using bit shift operations
        return (red << 16) + (green << 8) + blue;
    }

    static int[] RGB2int( int packed) {
        return new int[]{(packed & 0x00FF0000) >> 16, (packed & 0x0000FF00) >> 8, (packed & 0x000000FF)};
    }

}
