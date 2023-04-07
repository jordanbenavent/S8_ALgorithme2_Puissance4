import time
from pythonPuissance import *

start_time = time.perf_counter()

for i in range(0, 1000):
    #time.sleep(0.001)
    main()    

end_time = time.perf_counter()

print(f"Temps d'exécution : {end_time - start_time : .4f} secondes")

print("tempMoyen = (end_time - start_time) / 1000", (end_time - start_time) / 1000)
