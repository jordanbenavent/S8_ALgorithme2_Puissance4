from flask import Flask,jsonify, request
from benchmark import *
from convertStringToTab import *

app = Flask(__name__)

# Une route simple pour afficher un message de bienvenue
@app.route('/move', methods=['GET'])
def move():
    stringBoard = request.args.get('b')
    board = validation(stringBoard)
    if isinstance(board, str):
        return jsonify(board)
    column = mainBench(board) + 1 # +1 car l'index commence à 0
    return jsonify(column)


if __name__ == '__main__':
    app.run(debug=True)
