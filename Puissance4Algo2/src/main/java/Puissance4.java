import java.util.ArrayList;
import java.util.List;



public class Puissance4 {

    // Reste du code existant ...

    int bestCoup(int color) {
        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;
        List<Integer> validMoves = getValidMoves();
        for (int move : validMoves) {
            int line = getColumnHeight(move);
            set(move, line, color);
            int value = minMax(3, color, 0, 1, false,1);
            set(move, line, 0); // Undo the move
            System.out.println(value);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    int minMax(int depth, int color, int alpha, int beta, boolean maximizingPlayer, int value) {
        if(value == Integer.MAX_VALUE || value == Integer.MIN_VALUE)
            return value;
        if (depth == 0 || isFull()) {
            return getValueBoard(color);
        }

        List<Integer> validMoves = getValidMoves();
        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int move : validMoves) {
                int line = getColumnHeight(move);
                set(move, line, color);
                int eval = minMax(depth - 1, color, alpha, beta, false, value);
                set(move, line, 0); // Undo the move
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int move : validMoves) {
                int line = getColumnHeight(move);
                set(move, line, 3 - color);
                int eval = minMax(depth - 1, color, alpha, beta, true, value);
                set(move, line, 0); // Undo the move
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    int minimax(int depth, int color, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || isFull()) {
            return getValueBoard(color);
        }

        List<Integer> validMoves = getValidMoves();
        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int move : validMoves) {
                int line = getColumnHeight(move);
                set(move, line, color);
                int eval = minimax(depth - 1, color, alpha, beta, false);
                set(move, line, 0); // Undo the move
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int move : validMoves) {
                int line = getColumnHeight(move);
                set(move, line, 3 - color);
                int eval = minimax(depth - 1, color, alpha, beta, true);
                set(move, line, 0); // Undo the move
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    boolean isFull() {
        for (List<Integer> column : board) {
            if (column.get(5) == 0) {
                return false;
            }
        }
        return true;
    }

    List<Integer> getValidMoves() {
        List<Integer> validMoves = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (board.get(i).get(5) == 0) {
                validMoves.add(i);
            }
        }
        return validMoves;
    }

    int getColumnHeight(int col) {
        int height = 0;
        for (int value : board.get(col)) {
            if (value != 0) {
                height++;
            } else {
                break;
            }
        }
        return height;
    }

    List<List<Integer>> board = new ArrayList<>();

    void init(){
        for (int i = 0; i < 7; i++) {
            List<Integer> column = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                column.add(0);
            }
            board.add(column);
        }
    }

    void set(int col, int line, int value){
        board.get(col).set(line, value);
    }

    int getValueBoard(int color){
        List<PLace> places = getPlaces(color);
        int value = 0;
        for(PLace pLace : places){
            value += getValue(pLace);
        }
        return value;
    }

    private int getValue(PLace pLace) {
        int value = 0;
        value += getValueHorizontal(pLace);
        value += getValueVertical(pLace);
        value += getValueDiagonal(pLace);
        return value;
    }


    /**
     * **************************************** Diagonals ****************************************
     */

    /**
     * Value of the dick about the horizontal
     * @param pLace
     * @return
     */
    private int getValueDiagonal(PLace pLace) {
        int value = 0;
        int valueTopLeftBottomRight = 0;
        int valueTopRightBottomLeft = 0;
        if(possibleTopLeftBottomRight(pLace) && possibleTopRightBottomLeft(pLace)){
            valueTopLeftBottomRight=  getValueTopLeftBottomRight(pLace);
            valueTopRightBottomLeft =  getValueTopRightBottomLeft(pLace);
        } else if (possibleTopLeftBottomRight(pLace)){
            valueTopLeftBottomRight = getValueTopLeftBottomRight(pLace);
        } else if (possibleTopRightBottomLeft(pLace)){
            valueTopRightBottomLeft = getValueTopRightBottomLeft(pLace);
        }
        value = valueTopLeftBottomRight + valueTopRightBottomLeft;
        if(valueTopLeftBottomRight >= 4 || valueTopRightBottomLeft>=4){
            value = Integer.MAX_VALUE;
        }
        return value;
    }

    /**
     * Check if the diagonal top right bottom left is possible
     * @param pLace
     * @return
     */
    private boolean possibleTopRightBottomLeft(PLace pLace) {
        int cumul = 1;
        for (int i = 1; i < 4; i++) {
            if(pLace.x+i < 7 && pLace.y+i < 6){
                if(board.get(pLace.x+i).get(pLace.y+i) == pLace.value || board.get(pLace.x+i).get(pLace.y+i) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 4; i++) {
            if(pLace.x-i >= 0 && pLace.y-i >= 0){
                if(board.get(pLace.x-i).get(pLace.y-i) == pLace.value || board.get(pLace.x-i).get(pLace.y-i) == 0){
                    cumul++;
                }
            }
        }
        return cumul >= 4;
    }

    /**
     * Check if the diagonal top left bottom right is possible
     * @param pLace
     * @return
     */
    private boolean possibleTopLeftBottomRight(PLace pLace) {
        int cumul = 1;
        for (int i = 1; i < 4; i++) {
            if(pLace.x+i < 7 && pLace.y-i >= 0){
                if(board.get(pLace.x+i).get(pLace.y-i) == pLace.value || board.get(pLace.x+i).get(pLace.y-i) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 4; i++) {
            if(pLace.x-i >= 0 && pLace.y+i < 6){
                if(board.get(pLace.x-i).get(pLace.y+i) == pLace.value || board.get(pLace.x-i).get(pLace.y+i) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        return cumul >= 4;
    }

    /**
     * Return the value of the disk about the diagonal top left bottom right
     * @param pLace
     * @return
     */
    private int getValueTopRightBottomLeft(PLace pLace) {
        int col = pLace.x;
        int line = pLace.y;
        int value = 1;
        boolean opponentRight = false;
        boolean opponentLeft = false;
        for(int i = 1; i < 4; i++){
            if(col+i < 7 && line+i < 6){
                if(board.get(col+i).get(line+i) == pLace.value){
                    value++;
                } else if (board.get(col+i).get(line+i) != 0){
                    opponentRight = true;
                    break;
                } else {
                    break;
                }
            }
        }
        for(int i = 1; i < 4; i++){
            if(col-i >= 0 && line-i >= 0){
                if(board.get(col-i).get(line-i) == pLace.value){
                    value++;
                } else if (board.get(col-i).get(line-i) != 0){
                    opponentLeft = true;
                    break;
                } else {
                    break;
                }
            }
        }
        if (opponentLeft && opponentRight){
            return 0;
        }
        return value;
    }


    /**
     * Return the value of the disk about the diagonal top left bottom right
     * @param pLace
     * @return
     */
    private int getValueTopLeftBottomRight(PLace pLace) {
        int col = pLace.x;
        int line = pLace.y;
        int value = 0;
        boolean opponentRight = false;
        boolean opponentLeft = false;
        for (int i = 1; i < 4; i++) {
            if(col+i < 7 && line-i >= 0){
                if(board.get(col+i).get(line-i) == pLace.value){
                    value++;
                } else if (board.get(col+i).get(line-i) != 0){
                    opponentRight = true;
                    break;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 4; i++) {
            if(col-i >= 0 && line+i < 6){
                if(board.get(col-i).get(line+i) == pLace.value){
                    value++;
                } else if (board.get(col-i).get(line+i) != 0){
                    opponentLeft = true;
                    break;
                } else {
                    break;
                }
            }
        }
        if (opponentLeft && opponentRight){
            return 0;
        }
        return value;
    }

    /**
     * **************************************** Vertical ****************************************
     */

    /**
     * Return the value of the disk about the vertical
     * @param pLace
     * @return
     */
    private int getValueVertical(PLace pLace){
        int value = 0;
        if(possibleVertical(pLace)) {
            value = calculValueVertical(pLace);
        }
        return value >= 4 ? Integer.MAX_VALUE : value;
    }

    /**
     * check if the vertical is possible
     * @param pLace
     * @return
     */
    boolean possibleVertical(PLace pLace){
        int cumul = 1;
        //check top
        for (int i = 1; i < 4; i++) {
            if(pLace.y+i < 6){
                if(board.get(pLace.x).get(pLace.y+i) == pLace.value || board.get(pLace.x).get(pLace.y+i) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        //check bottom
        for (int i = 1; i < 4; i++) {
            if(pLace.y-i >= 0){
                if(board.get(pLace.x).get(pLace.y-i) == pLace.value || board.get(pLace.x).get(pLace.y-i) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        return cumul >= 4;
    }

    /**
     * Calculate the value of the disk about the vertical
     * @param pLace
     * @return
     */
    private int calculValueVertical(PLace pLace) {
        int col = pLace.x;
        int line = pLace.y;
        int value = 1;
        boolean opponentRight = false;
        boolean opponentLeft = false;
        //right
        for(int i = line+1; i < 6; i++){
            if(board.get(col).get(i) == pLace.value){
                value++;
            } else if (board.get(col).get(i) != 0){
                opponentRight = true;
                break;
            } else {
                break;
            }
        }
        //left
        for(int i = line-1; i >=0; i--){
            if(board.get(col).get(i) == pLace.value){
                value++;
            } else if (board.get(col).get(i) != 0){
                opponentLeft = true;
                break;
            } else {
                break;
            }
        }
        if(opponentLeft && opponentRight){
            return 0;
        }
        return value;
    }

    /**
     * **************************************** Horizontal ****************************************
     */

    /**
     * return the value of the disk about the horizontal
     * @param pLace
     * @return
     */
    private int getValueHorizontal(PLace pLace){
        int value = 0;
        if(possibleHorizontal(pLace)){
            value = calculValueHorizontal(pLace);
        }
        return value >= 4 ? Integer.MAX_VALUE: value;
    }

    /**
     * check if the horizontal is possible
     * @param pLace
     * @return
     */
    boolean possibleHorizontal(PLace pLace) {
        int cumul = 1;
        //check right
        for (int i = 1; i < 4; i++) {
            if(pLace.x+i < 7){
                if(board.get(pLace.x+i).get(pLace.y) == pLace.value || board.get(pLace.x+i).get(pLace.y) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        //check left
        for (int i = 1; i < 4; i++) {
            if(pLace.x-i >= 0){
                if(board.get(pLace.x-i).get(pLace.y) == pLace.value || board.get(pLace.x-i).get(pLace.y) == 0){
                    cumul++;
                } else {
                    break;
                }
            }
        }
        return cumul >= 4;
    }

    /**
     * Calculate the value of the disk about the horizontal
     * @param pLace
     * @return
     */
    private int calculValueHorizontal(PLace pLace) {
        int col = pLace.x;
        int line = pLace.y;
        int value = 1;
        boolean opponentRight = false;
        boolean opponentLeft = false;
        //right
        for(int i = col+1; i < 7; i++){
            if(board.get(i).get(line) == pLace.value){
                value++;
            } else if (board.get(i).get(line) != 0){
                opponentRight = true;
                break;
            } else {
                break;
            }
        }
        //left
        for(int i = col-1; i >=0; i--){
            if(board.get(i).get(line) == pLace.value){
                value++;
            } else if (board.get(i).get(line) != 0){
                opponentLeft = true;
                break;
            } else {
                break;
            }
        }
        if(opponentLeft && opponentRight ){
            return 0;
        }
        return value;
    }


    /**
     * return all places of a color
     * @param color
     * @return
     */
    private List<PLace> getPlaces(int color) {
        List<PLace> places = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(board.get(i).get(j) == 0){
                    break;
                }
                if(board.get(i).get(j) == color) {
                    places.add(new PLace(i, j, board.get(i).get(j)));
                }
            }
        }
        return places;
    }


    /**
     * Display the board
     */
    void showBoard(){
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board.get(j).get(i) + " ");
            }
            System.out.println();
        }
    }

    /**
     * **************************************** Place ****************************************
     */
    private class PLace {
        int x;
        int y;
        int value;

        PLace(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "PLace : " +
                    "(col= " + x +
                    ", line= " + y +
                    ", value= " + value +
                    ')';
        }
    }
}
