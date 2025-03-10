package Shader;

public class RingShader extends Shader {
    private int y_c;
    private int x_c;
    private int w;
    private boolean isGradient;


    public RingShader(int y_c, int x_c, int w, boolean isGradient) {
        this.y_c = y_c;
        this.x_c = x_c;
        this.w = w;
        this.isGradient = isGradient;
    }

    @Override
    public double calcMask(int j, int i) {
        if (!isGradient)
            return harshSwitch(j, i);
        else
            return softSwitch(j, i);
    }

    public double softSwitch(int j, int i) {
        double d;

        d = Math.sqrt( (i-y_c)*(i-y_c) + (j-x_c)*(j-x_c) );

        return Math.min(
            1,
            ((Math.sin(Math.PI * d / w) + 1) / 2)
        );
    }

    public double harshSwitch(int j, int i) {
        double d;
        int    r;
        // Calculate distance to the image center
        d = Math.sqrt( (i-y_c)*(i-y_c) + (j-x_c)*(j-x_c) );
        // Find the ring index
        r = (int)d / w;
        // Make decision on the pixel color
        // based on the ring index
        return r % 2 == 0 ? 1 : 0;

    }

    public void setX_c(int x_c) {
        this.x_c = x_c;
    }

    public void setY_c(int y_c) {
        this.y_c = y_c;
    }
}
