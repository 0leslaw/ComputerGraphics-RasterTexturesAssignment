package Shader;

import java.util.function.BiPredicate;

public class GridShader extends Shader{
    private int spaceBetweenLinesY;
    private int spaceBetweenLinesX;
    private int lineWidth;
    private BiPredicate<Integer,Integer> onGrid = (coor, spaceBetweenLines) ->
            ((coor + spaceBetweenLines / 2 + lineWidth) % (spaceBetweenLines + lineWidth)) // coor + (T - fi) mod T
                    // if the above expression less than lineWidth, the predicate is true
                    < lineWidth;

    public GridShader(int spaceBetweenLinesY, int spaceBetweenLinesX, int lineWidth) {
        this.spaceBetweenLinesX = spaceBetweenLinesX;
        this.spaceBetweenLinesY = spaceBetweenLinesY;
        this.lineWidth = lineWidth;
    }
    @Override
    public double calcMask(int j, int i) {
        return (onGrid.test(i, spaceBetweenLinesY) || onGrid.test(j, spaceBetweenLinesX)) ? 1 : 0;
    }
}
