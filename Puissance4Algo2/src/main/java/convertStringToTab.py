from time import sleep

def convert_to_2d_array(input_string):
    rows, columns = 6, 7
    array_2d = [['' for _ in range(columns)] for _ in range(rows)]
    index = 0
    
    for col in range(columns):
        for row in range(rows):
            array_2d[row][col] = input_string[index]
            index += 1

    for row in reversed(array_2d):
        print(row)        
            
    return array_2d






def count_characters(input_string):
    counts = {'0': 0, 'h': 0, 'm': 0, 'other': 0}

    for char in input_string:
        if char in counts:
            counts[char] += 1
        else:
            counts['other'] += 1

    return counts

def valid_characters(counts):
    if counts['other'] == 0:
        return True
    else:
        return False

def valid_size(counts):
    #print("count vaut "+ str(counts))
    if counts['0'] + counts['h'] + counts['m'] == 42:
        return True
    else:
        return False
    
def valid_nb_pions(counts):
    if counts['h'] == counts['m']+1:
        return True
    else:
        return False
    
def board_full(counts):
    if counts['0'] == 0:
        return True
    else:
        return False



def check_win(array_2d):
    #TODO
    return False

def check_correct_disposition(array_2d):
    for col in range(7):
        for row in range(6):
            if(array_2d[row][col] == '0'):
                #check if tehre is no 0 pion above
                for row2 in range(row+1, 6):
                    if(array_2d[row2][col] != '0'):
                        return False
    return True




def validation(string):
    count = count_characters(string)

    if not valid_characters(count) :
        return "invalid char"
    if not valid_size(count):
        return "invalid size"
    if not valid_nb_pions(count):
        return "invalid nb pion"
    if board_full(count):
        return "board full"


    board = convert_to_2d_array(string)

    if not check_correct_disposition(board):
        return "incorrect disposition"
    if check_win(board):
        return "win"
    

    return "correct"



def global_validation(string,id):
    print(validation(string), id)





#test chaine correcte
global_validation("m00000h00000mm0000hmh000h00000h00000000000",1)

sleep(1)

global_validation("h00000000000000000000000000000000000000000",2)

sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h00000mh0000",3)

sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h00000hm0000",4)

sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h00000000000",5)

sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h00000hm0000",6)



sleep(1)
print("MAUVAISE CHAINE")


sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h0000000000x",7)
sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h00000000001",8)

sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h000000m000h",9)

sleep(1)

global_validation("m00000h00000mm0000hmh000h00000h0000h00000m",10)

global_validation("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm",11)

