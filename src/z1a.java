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
import javax.imageio.*;

// java .\src\z1a.java 300 500 "rings.jpg"

public class z1a
{
    public static void main(String[] args)
    {
        System.out.println("Soft ring pattern synthesis");

        BufferedImage image;

        // Image resolution
        int x_res, y_res;

        // Ring center coordinates
        int  x_c, y_c;

        // Loop variables - indices of the current row and column
        int  i, j;
        // Fixed ring width
        final int w = 10;
        // Get required image resolution from command line arguments
        x_res = Integer.parseInt( args[0].trim() );
        y_res = Integer.parseInt( args[1].trim() );
        // Initialize an empty image, use pixel format
        // with RGB packed in the integer data type
        image = new BufferedImage( x_res, y_res,
                BufferedImage.TYPE_INT_RGB);
        // Create packed RGB representation of black and white colors
        int color;

        // Find coordinates of the image center
        x_c = x_res / 2;
        y_c = y_res / 2;

        // Process the image, pixel by pixel
        for ( i = 0; i < y_res; i++) for ( j = 0; j < x_res; j++)
        {
            double d;

            d = Math.sqrt( (i-y_c)*(i-y_c) + (j-x_c)*(j-x_c) );

            color = grayify(
                    Math.min(
                            255,
                            (int)( 128 * (Math.sin(Math.PI * d / w) + 1))
                    )
            );

            image.setRGB( j, i, color );
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

    static int grayify( int grayness)
    {
        return int2RGB(grayness, grayness, grayness);
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