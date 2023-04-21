import time
from pythonPuissance import *

def mainBench(board):
    start_time = time.perf_counter()
    column = main(board)
    end_time = time.perf_counter()
    print(f"Temps d'exécution : {end_time - start_time : .4f} secondes")
    return column
