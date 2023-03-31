import time
from pythonPuissance import *

start_time = time.perf_counter()

main()

end_time = time.perf_counter()

print(f"Temps d'exécution : {end_time - start_time : .4f} secondes")
