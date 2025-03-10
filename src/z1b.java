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
import javax.imageio.*;

// java .\src\z1b.java 200 200 "grid.jpg" 23 23 23 45 255 255 10 10 2

public class z1b
{
    public static void main(String[] args)
    {
        System.out.println("Grid pattern synthesis");

        BufferedImage  image;

        // Image resolution
        int x_res, y_res;

        int  squareColor, gridColor;

        // Loop variables - indices of the current row and column
        int  i, j;

        final int spaceBetweenLinesY = Integer.parseInt( args[9].trim() );
        final int spaceBetweenLinesX = Integer.parseInt( args[10].trim() );
        final int lineWidth = Integer.parseInt( args[11].trim() );

        x_res = Integer.parseInt( args[0].trim() );
        y_res = Integer.parseInt( args[1].trim() );

        image = new BufferedImage( x_res, y_res,
                BufferedImage.TYPE_INT_RGB);

        squareColor = int2RGB( Integer.parseInt( args[3].trim() ), Integer.parseInt( args[4].trim() ), Integer.parseInt( args[5].trim() ) );
        gridColor = int2RGB( Integer.parseInt( args[6].trim() ), Integer.parseInt( args[7].trim() ), Integer.parseInt( args[8].trim() ) );



        // tutaj mialem problem ((coor - fi) % period) < lineWidth nie dzialalo samo z siebie
        // (chciałem przesunąć o - fi a dziala tylko przesuniecie o T - fi)
        BiPredicate<Integer,Integer> onGrid = (coor, spaceBetweenLines) ->
                ((coor + spaceBetweenLines / 2 + lineWidth) % (spaceBetweenLines + lineWidth)) // coor + (T - fi) mod T
                        // if the above expression less than lineWidth, the predicate is true
                        < lineWidth;

        // Process the image, pixel by pixel
        for ( i = 0; i < y_res; i++) for ( j = 0; j < x_res; j++)
        {
            if (onGrid.test(i, spaceBetweenLinesY) || onGrid.test(j, spaceBetweenLinesX))
                image.setRGB( j, i, gridColor);
            else
                image.setRGB( j, i, squareColor);

        }
        // Save the created image in a graphics file
        try
        {
            ImageIO.write( image, "bmp", new File( args[2]) );
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