import random

class Puissance4:

    def bestCoup(self,color):
        bestMove = -1
        bestValue = float("-inf")
        validMoves = self.getValidMoves()
        for move in validMoves:
            line = self.getColumnHeight(move)
            self.set(move, line, color)
            value = self.minMax(3, color, -1000, 1000, False, 1)
            self.set(move, line, 0)  # Undo the move
            if value > bestValue:
                bestValue = value
                bestMove = move
        return bestMove


    def minMax(self,depth, color, alpha, beta, maximizingPlayer, value):
        if depth == 0 or self.isFull():
            return self.getValueBoard(color)
        validMoves = self.getValidMoves()
        if maximizingPlayer:
            maxEval = float("-inf")
            for move in validMoves:
                line = self.getColumnHeight(move)
                self.set(move, line, color)
                eval = self.minMax(depth - 1, color, alpha, beta, False, value)
                self.set(move, line, 0)  # Undo the move
                maxEval = max(maxEval, eval)
                alpha = max(alpha, eval)
                if beta <= alpha:
                    break
            return maxEval
        else:
            minEval = float("inf")
            for move in validMoves:
                line = self.getColumnHeight(move)
                self.set(move, line, 3 - color)
                if self.getValueBoard(3-color) > 1000:
                    self.set(move, line, 0)
                    return 20 - depth * 1000
                eval = self.minMax(depth - 1, color, alpha, beta, True, value)
                self.set(move, line, 0)  # Undo the move
                minEval = min(minEval, eval)
                beta = min(beta, eval)
                if beta <= alpha:
                    break
            return minEval

    def isFull(self):
        for column in self.board:
            if column[5] == 0:
                return False
        return True


    def getValidMoves(self):
        validMoves = []
        for i in range(7):
            if self.board[i][5] == 0:
                validMoves.append(i)
        return validMoves

    def getColumnHeight(self, col):
        height = 0
        for value in self.board[col]:
            if value != 0:
                height += 1
            else:
                break
        return height

    board = [[0 for j in range(6)] for i in range(7)]

    def set(self, col, line, value):
        self.board[col][line] = value

    def getValueBoard(self, color):
        places = self.get_places(color)
        value = 0
        for place in places:
            value += self.getValue(place)
        return value

    def getValue(self, place):
        value = 0
        value += self.get_value_horizontal(place)
        value += self.get_value_vertical(place)
        value += self.getValueDiagonal(place)
        return value

    def getValueDiagonal(self, pLace):
        valueTopLeftBottomRight = 0
        valueTopRightBottomLeft = 0
        if self.possibleTopLeftBottomRight(pLace) and self.possibleTopRightBottomLeft(pLace):
            valueTopLeftBottomRight = self.get_value_top_left_bottom_right(pLace)
            valueTopRightBottomLeft = self.get_value_top_right_bottom_left(pLace)
        elif self.possibleTopLeftBottomRight(pLace):
            valueTopLeftBottomRight = self.get_value_top_left_bottom_right(pLace)
        elif self.possibleTopRightBottomLeft(pLace):
            valueTopRightBottomLeft = self.get_value_top_right_bottom_left(pLace)
        value = valueTopLeftBottomRight + valueTopRightBottomLeft
        if valueTopLeftBottomRight >= 4 or valueTopRightBottomLeft >= 4:
            value = value * 1000
        return value

    def possibleTopRightBottomLeft(self, pLace):
        cumul = 1
        for i in range(1, 4):
            if pLace.x + i < 7 and pLace.y + i < 6:
                if self.board[pLace.x + i][pLace.y + i] == pLace.value or self.board[pLace.x + i][pLace.y + i] == 0:
                    cumul += 1
                else:
                    break
        for i in range(1, 4):
            if pLace.x - i >= 0 and pLace.y - i >= 0:
                if self.board[pLace.x - i][pLace.y - i] == pLace.value or self.board[pLace.x - i][pLace.y - i] == 0:
                    cumul += 1
                else:
                    break
        return cumul >= 4

    def possibleTopLeftBottomRight(self, pLace):
        cumul = 1
        for i in range(1, 4):
            if pLace.x + i < 7 and pLace.y - i >= 0:
                if self.board[pLace.x + i][pLace.y - i] == pLace.value or self.board[pLace.x + i][pLace.y - i] == 0:
                    cumul += 1
                else:
                    break
        for i in range(1, 4):
            if pLace.x - i >= 0 and pLace.y + i < 6:
                if self.board[pLace.x - i][pLace.y + i] == pLace.value or self.board[pLace.x - i][pLace.y + i] == 0:
                    cumul += 1
                else:
                    break
        return cumul >= 4

    def get_value_top_right_bottom_left(self, pLace):
        col = pLace.x
        line = pLace.y
        value = 1
        opponent_right = False
        opponent_left = False
        for i in range(1, 4):
            if col + i < 7 and line + i < 6:
                if self.board[col + i][line + i] == pLace.value:
                    value += 1
                elif self.board[col + i][line + i] != 0:
                    opponent_right = True
                    break
                else:
                    break
        for i in range(1, 4):
            if col - i >= 0 and line - i >= 0:
                if self.board[col - i][line - i] == pLace.value:
                    value += 1
                elif self.board[col - i][line - i] != 0:
                    opponent_left = True
                    break
                else:
                    break
        if opponent_left and opponent_right:
            return 0
        return value

    def get_value_top_left_bottom_right(self, pLace):
        col = pLace.x
        line = pLace.y
        value = 1
        opponent_right = False
        opponent_left = False
        for i in range(1, 4):
            if col + i < 7 and line - i >= 0:
                if self.board[col + i][line - i] == pLace.value:
                    value += 1
                elif self.board[col + i][line - i] != 0:
                    opponent_right = True
                    break
                else:
                    break
        for i in range(1, 4):
            if col - i >= 0 and line + i < 6:
                if self.board[col - i][line + i] == pLace.value:
                    value += 1
                elif self.board[col - i][line + i] != 0:
                    opponent_left = True
                    break
                else:
                    break
        if opponent_left and opponent_right:
            return 0
        return value

    def get_value_vertical(self, pLace):
        value = 0
        if self.possible_vertical(pLace):
            value = self.calculate_value_vertical(pLace)
        return value * 1000 if value >= 4 else value

    def possible_vertical(self, pLace):
        cumul = 1
        # check top
        for i in range(1, 4):
            if pLace.y + i < 6:
                if self.board[pLace.x][pLace.y + i] == pLace.value or self.board[pLace.x][pLace.y + i] == 0:
                    cumul += 1
                else:
                    break
        # check bottom
        for i in range(1, 4):
            if pLace.y - i >= 0:
                if self.board[pLace.x][pLace.y - i] == pLace.value or self.board[pLace.x][pLace.y - i] == 0:
                    cumul += 1
                else:
                    break
        return cumul >= 4

    def calculate_value_vertical(self, pLace):
        col = pLace.x
        line = pLace.y
        value = 1
        opponent_right = False
        opponent_left = False
        # right
        for i in range(line + 1, 6):
            if self.board[col][i] == pLace.value:
                value += 1
            elif self.board[col][i] != 0:
                opponent_right = True
                break
            else:
                break
        # left
        for i in range(line - 1, -1, -1):
            if self.board[col][i] == pLace.value:
                value += 1
            elif self.board[col][i] != 0:
                opponent_left = True
                break
            else:
                break
        if opponent_left and opponent_right:
            return 0
        return value

    def get_value_horizontal(self, pLace):
        value = 0
        if self.possible_horizontal(pLace):
            value = self.calculate_value_horizontal(pLace)
        return value * 1000 if value >= 4 else value

    def possible_horizontal(self, pLace):
        cumul = 1
        # check right
        for i in range(1, 4):
            if pLace.x + i < 7:
                if self.board[pLace.x + i][pLace.y] == pLace.value or self.board[pLace.x + i][pLace.y] == 0:
                    cumul += 1
                else:
                    break
        # check left
        for i in range(1, 4):
            if pLace.x - i >= 0:
                if self.board[pLace.x - i][pLace.y] == pLace.value or self.board[pLace.x - i][pLace.y] == 0:
                    cumul += 1
                else:
                    break
        return cumul >= 4

    def calculate_value_horizontal(self, pLace):
        col = pLace.x
        line = pLace.y
        value = 1
        opponent_right = False
        opponent_left = False
        # right
        for i in range(col + 1, 7):
            if self.board[i][line] == pLace.value:
                value += 1
            elif self.board[i][line] != 0:
                opponent_right = True
                break
            else:
                break
        # left
        for i in range(col - 1, -1, -1):
            if self.board[i][line] == pLace.value:
                value += 1
            elif self.board[i][line] != 0:
                opponent_left = True
                break
            else:
                break
        if opponent_left and opponent_right:
            return 0
        return value

    def get_places(self, color):
        places = []
        for i in range(7):
            for j in range(6):
                if self.board[i][j] == 0:
                    break
                if self.board[i][j] == color:
                    places.append(PLace(i, j, self.board[i][j]))
        return places

    def show_board(self):
        for i in range(5, -1, -1):
            for j in range(7):
                print(self.board[j][i], end=" ")
            print()

    def __init__(self):
        self.board = []

    def init(self):
        for i in range(7):
            column = []
            for j in range(6):
                column.append(0)
            self.board.append(column)

    def set(self, col, line, value):
        self.board[col][line] = value


    def tryBoardColonne(self,color, colonne):
        gameBoard2 = list(self.board)
        for j in range(6):
            if gameBoard2[colonne][j] == 0:
                gameBoard2[colonne][j] = color
                values = self.getValueBoard(color)
                gameBoard2[colonne][j] = 0
                return values

    def computeMove2(self, color):
        indiceColonne = random.randint(0, 6)
        while self.board[indiceColonne][5] != 0:
            indiceColonne = random.randint(0, 6)
        max_val = self.tryBoardColonne(color, indiceColonne)
        for i in range(7):
            a = self.tryBoardColonne(color, i)
            if a == max_val and self.board[i][5] == 0:
                if self.board[i][0] == 0:
                    max_val = a
                    indiceColonne = i
            elif a > max_val and self.board[i][5] == 0:
                max_val = a
                indiceColonne = i
        if self.board[indiceColonne][5] == 0:
            return [indiceColonne, 0]
        return 0  # Si on trouve rien, on joue au pif


class PLace:
    def __init__(self, x, y, value):
        self.x = x
        self.y = y
        self.value = value

    def __str__(self):
        return f"PLace : (col={self.x}, line={self.y}, value={self.value})"


def main(board):
    puissance4 = Puissance4()
    puissance4.init()
    # puissance4.board = board
    puissance4.set(0, 0, 2)
    puissance4.set(1, 0, 1)
    puissance4.set(2, 0, 2)
    puissance4.set(3, 0, 1)
    puissance4.set(4, 0, 1)
    puissance4.set(5, 0, 1)
    puissance4.set(2, 1, 2)
    puissance4.set(3, 1, 2)
    puissance4.set(3, 2, 1)
    puissance4.show_board()
    best = puissance4.bestCoup(2)
    print(best)
    return best

if __name__ == "__main__":
    main([[0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]])
