def convert_to_2d_array(input_string):
    rows, columns = 6, 7
    array_2d = [['' for _ in range(columns)] for _ in range(rows)]
    index = 0
    
    for col in range(columns):
        for row in range(rows):
            array_2d[row][col] = input_string[index]
            index += 1
            
    return array_2d

input_string = "m00000h00000mm0000hmh000h00000h00000000000"
array_2d = convert_to_2d_array(input_string)

for row in array_2d:
    print(row)


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




 #VALID GLOBAL:
 # appelle count
 # puis appelle valid char, valid size, correct nb pion par rapport a autre, puis full,
 #Puis convertir en tableau de tableau et ensuite check win et check correct disposition


def global_validation(string):
    count = count_characters(string)

    print("valid char" if valid_characters(count) else "invalid char")
    print("valid size" if valid_size(count) else "invalid size")
    print("valid nb pion" if valid_nb_pions(count) else "invalid nb pion")
    print("board full" if board_full(count) else "board not full")

    board = convert_to_2d_array(string)

    print("correct disposition" if check_correct_disposition(board) else "incorrect disposition")
    print("win" if check_win(board) else "no win")


global_validation("m00000h00000mm0000hmh000h00000h00000000000")


