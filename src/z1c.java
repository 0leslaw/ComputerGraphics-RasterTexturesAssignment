/*
2:    * Computer graphics courses at Wroclaw University of Technology
3:    * (C) Wroclaw University of Technology, 2010
4:    *
 5:    * Description:
 6:    *   This demo shows basic raster operations on raster image
 7:    *   represented by BufferedImage object. Image is created
 8:    *   on pixel-by-pixel basis and then stored in a file.
 9:    *
10:    */

import java.io.*;
import java.awt.image.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.imageio.*;

// java .\src\z1c.java 1 "chessboard.jpg" 23 23 100 200 200 255

public class z1c
{
    public static void main(String[] args)
    {
        System.out.println("Chessboard pattern synthesis");

        BufferedImage  image;

        // Image resolution
        int x_res, y_res;

        int  a1SquareColor, b1SquareColor;

        // Loop variables - indices of the current row and column
        int  i, j;

        final int tileSize = Integer.parseInt( args[0].trim() );

        final int x_y_res = tileSize * 8;
;

        image = new BufferedImage( x_y_res, x_y_res,
                BufferedImage.TYPE_INT_RGB);

        a1SquareColor = int2RGB( Integer.parseInt( args[2].trim() ), Integer.parseInt( args[3].trim() ), Integer.parseInt( args[4].trim() ) );
        b1SquareColor = int2RGB( Integer.parseInt( args[5].trim() ), Integer.parseInt( args[6].trim() ), Integer.parseInt( args[7].trim() ) );



        int T = 2 * tileSize;
        BiPredicate<Integer,Integer> onLikeA1 = (j_x, i_x) ->
                (j_x % T >= tileSize && i_x % T < tileSize)
                        ||
                (j_x % T < tileSize && i_x % T >= tileSize);

        // Process the image, pixel by pixel
        for ( i = 0; i < x_y_res; i++) for ( j = 0; j < x_y_res; j++)
        {
            if(onLikeA1.test(j, i))
                image.setRGB(j, i, a1SquareColor);
            else
                image.setRGB(j, i, b1SquareColor);

        }
        // Save the created image in a graphics file
        try
        {
            ImageIO.write( image, "bmp", new File( args[1]) );
            System.out.println( "Ring image created successfully");
        }
        catch (IOException e)
        {
            System.out.println( "The image cannot be stored" );
        }
    }

    // This method assembles RGB color intensities into single
    // packed integer. Arguments must be in <0..255> range
    static int int2RGB( int red, int green, int blue)
    {
        // Make sure that color intensities are in 0..255 range
        red   = red   & 0x000000FF;
        green = green & 0x000000FF;
        blue  = blue  & 0x000000FF;

        // Assemble packed RGB using bit shift operations
        return (red << 16) + (green << 8) + blue;
    }

}