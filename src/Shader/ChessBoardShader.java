package Shader;

import java.util.function.BiPredicate;

public class ChessBoardShader extends Shader{

    private int squareSize;
    int T;
    BiPredicate<Integer,Integer> onLikeA1;
    public ChessBoardShader(int squareSize) {
        this.squareSize = squareSize;
        this.T = 2 * squareSize;
        this.onLikeA1 = (j_x, i_x) ->
                (j_x % T >= squareSize && i_x % T < squareSize)
                        ||
                (j_x % T < squareSize && i_x % T >= squareSize);
    }
    @Override
    public double calcMask(int j, int i) {
        return onLikeA1.test(j, i) ? 0 : 1;
    }
}
