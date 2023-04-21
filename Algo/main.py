from flask import Flask,jsonify, request, make_response
from benchmark import *
from convertStringToTab import *

app = Flask(__name__)

# Une route simple pour afficher un message de bienvenue
@app.route('/move', methods=['GET'])
def move():
    stringBoard = request.args.get('b')
    board = validation(stringBoard)
    if isinstance(board, str):
        response = make_response(jsonify(board))
        response.status_code = 404
        return response
    column = mainBench(board) + 1 # +1 car l'index commence à 0
    response = make_response(jsonify(column))
    response.status_code = 200
    return response


if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')
