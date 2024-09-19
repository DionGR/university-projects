#==================
# Εργασία Python 
# Επιστήμη Υπολογιστών - ΑUEB 2020
# Μέλη - ΑΜ: Διονύσιος Ρηγάτος (ΜΕΤΕΓΓΡΑΦΗ), Ελεονώρα Σδραβοπούλου (3200172), Θεόδωρος Κοτίδης(3170192)
# Κυρια Εργασία - Ομάδα (Χ) Bonus I - Διονύσης & Ελεονώρα (Χ) Bonus II(-)
#==================

from operator import *
import random 
import string

deck = [' A ♦', ' A ♣', ' A ♥', ' A ♠', ' 2 ♦', ' 2 ♣', ' 2 ♥', ' 2 ♠', ' 3 ♦', ' 3 ♣', ' 3 ♥', ' 3 ♠', ' 4 ♦', ' 4 ♣', ' 4 ♥', ' 4 ♠', ' 5 ♦', ' 5 ♣', ' 5 ♥', ' 5 ♠', ' 6 ♦', ' 6 ♣', ' 6 ♥', ' 6 ♠', ' 7 ♦', ' 7 ♣', ' 7 ♥', ' 7 ♠', ' 8 ♦', ' 8 ♣', ' 8 ♥', ' 8 ♠', ' 9 ♦', ' 9 ♣', ' 9 ♥', ' 9 ♠',' 10♦',' 10♣',' 10♥',' 10♠', ' J ♦', ' J ♣', ' J ♥', ' J ♠', ' Q ♦', ' Q ♣', ' Q ♥', ' Q ♠', ' K ♦', ' K ♣', ' K ♥', ' K ♠']

#==================
# Η κύρία συνάρτηση του προγράμματος.
#==================
def main():
    players, difficulty, gamemode = introduction() # Καλεί την συνάρτηση και θέτει τις μεταβλητές των παικτών και δυσκολίας σύμφωνα με το input.
    if difficulty == 1: # Έλεγχος για το ποια δυσκολία να κληθεί, ανάλογα με το input.
        points = easy(players, difficulty, gamemode) # Καλεί την συνάρτηση της Χ δυσκολίας και επιστρεέφει πίνακα με πόντους.
    elif difficulty == 2: 
        points = medium(players, difficulty, gamemode)
    else: 
        points = hard(players, difficulty, gamemode)
    winner(points) # Καλεί την συνάρτηση winner οπου θα βρεθεί ο νικητηής βάσει πόντων.
    repeat = input("\nΘα θέλατε να παίξετε ξανά? [ΝΑΙ/ΟΧΙ]") # Δίνει στον παίκτη την επιλογή να ξαναπαίξει.
    if repeat.lower() == 'ναι' or repeat.lower() == 'nai': # Εαν απαντήσει ναι σε οποιαδήποτε μορφή, ξανακαλεί την main.
        print("Επανάληψη παιχνιδιού...\n")
        main()
    else: # Εαν απαντήσει οτιδήποτε άλλο εκτος απο ναι, τερματίζει.
        print("Tερματισμός παιχνιδιού...\n")
#==================
# Εισαγωγικά μηνύματα, είσοδος και έλεγχοι εγκυρότητας. 
#==================
def introduction(): # Δέχεται τον αριθμό παικτών και της δυσκολίας και τα επιστρέφει στην main.
    print("Καλωσήλθατε στο Matching Game!\n")
    while True: # Επανάληψη μέχρι να τηην "σπάσουμε" οταν δοθεί ορθή τιμη.
        try:  # Ελεγχος εγκυρότητας.
            players = int(input("Δώστε αριθμό παικτών: "))
        except (TypeError, ValueError): # Σε περιπτωση που δοθει κάτι δεκαδικο η οποιαδηποτε λαθος τιμη, θέτει την τιμή ως 0.
            players = 0
        if players < 1: # Σε περιπτωση που δοθει κάτι <1 η οποιαδηποτε λαθος τιμη, επαναλαμβάνει.
            print("\nΛάθος τιμή. Παρακαλώ ξαναδώστε αριθμό παικτών.\n")
        else: #  Δόθηκε ορθή τιμη - τερματισμός επανάληψης.
            break
    while True:
        try: # Ελεγχος εγκυρότητας. Σε περιπτωση που δοθει κάτι <1 & >2, δεκαδικο η οποιαδηποτε λαθος τιμη, επαναλαμβάνει.
            gamemode = int(input("ΚΛΑΣΣΙΚΟ[1] η ΕΝΑΛΛΑΚΤΙΚΟ[2] παιχνιδι: "))
        except (TypeError, ValueError):
            gamemode = 0
        if gamemode not in range(1,3): # Τιμές 1 εως 3 (χωρίς το 3).
            print("\nΛάθος τιμή. Παρακαλώ ξαναδώστε τύπο παιχνιδιού.\n")
        else:
            break
    while True:
        try: # Ελεγχος εγκυρότητας. Σε περιπτωση που δοθει κάτι <1 & >3, δεκαδικο η οποιαδηποτε λαθος τιμη, επαναλαμβάνει.
            difficulty = int(input("Δώστε επίπεδο δυσκολίας Εύκολο (1), Μέτριο (2), Δύσκολο (3): "))
        except (TypeError, ValueError):
            difficulty = 0
        if difficulty not in range(1,4): # Τιμές 1 εως 4 (χωρίς το 4).
            print("\nΛάθος τιμή. Παρακαλώ ξαναδώστε επίπεδο δυσκολίας.\n")
        else:
            break
    return players, difficulty, gamemode

#==================
# Δυσκολίες
#==================
def easy(players, difficulty, gamemode): # Εύκολο παιχνίδι.
    print(f"\nΕκκίνηση παιχνιδιού έυκολου επιπέδου με {players} παίκτες...\n\n") # Εμφανίζει μήνυμα εκκίνησης με την δυσκολια και τον αριθμό των παικτών.
    easydeck = deck[36:] # Αρχικοποιεί τις κάρτες που θα χρησιμοποιήσουμε στο έυκολο επίπεδο, απο το 10 εως και το Κ.
    easydeck = shuffler(easydeck)
    easydeck, deckwithx = deckto2d(easydeck, 4) # Μετατροπή deck σε 2D list.
    if gamemode == 1: # Συνθήκη για να βρούμε τον τύπο παιχνιδιού.
        points = playgame(players, difficulty, easydeck, deckwithx, 4) # Ξεκινάει την επανάληψη του παιχνιδιού και επιστρέφει το scoreboard.
    else:
        points = playgamealt(players, difficulty, easydeck, deckwithx, 4)
    return points

def medium(players, difficulty, gamemode): # Μέτριο παιχνίδι.
    print(f"\nΕκκίνηση παιχνιδιού μέτριου επιπέδου με {players} παίκτες...\n\n") 
    mediumdeck = deck[:40] # Αρχικοποιεί τις κάρτες που θα χρησιμοποιήσουμε στο μέτριο επίπεδο, απο το Α εως και το 10.
    mediumdeck = shuffler(mediumdeck)
    mediumdeck, deckwithx = deckto2d(mediumdeck, 10) # Μετατροπή deck σε 2D list.
    if gamemode == 1: # Συνθήκη για να βρούμε τον τύπο παιχνιδιού.
        points = playgame(players, difficulty, mediumdeck, deckwithx, 10) # Ξεκινάει την επανάληψη του παιχνιδιού και επιστρέφει το scoreboard.
    else:
        points = playgamealt(players, difficulty, mediumdeck, deckwithx, 10)
    return points

def hard(players, difficulty, gamemode): # Δύσκολο παιχνίδι.
    print(f"\nΕκκίνηση παιχνιδιού υψηλού επιπέδου με {players} παίκτες...\n\n") 
    harddeck = deck.copy() # Αρχικοποιεί τις κάρτες που θα χρησιμοποιήσουμε στο υψηλό επίπεδο, δηλαδή όλες, χωρίς να παραμορφώνει το αρχικό deck, αντιγράφοντάς το σε νέα μεταβλητή.
    harddeck = shuffler(harddeck)  
    harddeck, deckwithx = deckto2d(harddeck, 13) # Μετατροπή deck σε 2D list.
    if gamemode == 1: # Συνθήκη για να βρούμε τον τύπο παιχνιδιού.
        points = playgame(players, difficulty, harddeck, deckwithx, 13) # Ξεκινάει την επανάληψη του παιχνιδιού και επιστρέφει το scoreboard.
    else:
        points = playgamealt(players, difficulty, harddeck, deckwithx, 13)
    return points

#==================
# Δυσκολίες - Boηθητικές Συναρτήσεις
#================== 
def shuffler(shuffledeck): # Μπερδέυει τις κάρτες και επιστρέφει ένα μπερδεμένο deck.
    random.shuffle(shuffledeck) # Μπερδέυει τα φύλλα του x-deck με την χρήση της random.
    return shuffledeck

def deckto2d(deck, rng): # Μετατρέπει το x-deck σε μια 2D λιστα και δημιουργεί μια παράλληλη λίστα γεμάτη με 'Χ'.
    newdeck = []
    deckwithx = []
    pos = 0
    for i in range(4):
        deckwithx.append([])
        newdeck.append([])
        for _ in range(rng):
            deckwithx[i].append(" X  ")
            newdeck[i].append(deck[pos])
            pos += 1
    #print(f"{newdeck}\n\n") DEBUGGING - Ελεγχος για να δουμε πως ειναι η 2D λιστα.
    return newdeck,deckwithx

#==================
# Bασικό Παιχνίδι
# ΝΟΤΕ: xdeck = Χαρτιά στην σωστή πλευρά, όπου x = easy,medium,hard. /// deckwithx = Χαρτια γυρισμένα, δηλαδή deck γεμάτο "X" που σταδιακά γυρίζουν.
#==================
def playgame(players, difficulty, xdeck, deckwithx, rng): # Συνάρτηση όπου οι παίκτες παίζουν.
    printdeck(xdeck, rng) # Eμφανίζει το deck γυρισμενο για λόγους ευκολίας και debugging. 
    print("\n\n\n\n")
    i = 1 # Aρχικοποιεί το ποιός παίκτης παίζει πρώτα.
    points = [0] * players # Λίστα με πόντους παικτών μηδενισμένη.
    printdeck(deckwithx, rng) # Εμφανίζει το deck με ολες τις κάρτες κρυμμένες.
    flatdeckwithx = [j for k in deckwithx for j in k] # Δημιουργεί ένα flattened deckwithx για την ορθότητα της συνθήκης της επανάληψης.
    while ' X  ' in flatdeckwithx: # Επανάληψη μέχρι να γυρίσουν όλες οι κάρτες, δηλαδη να μην υπαρχει Χ στο deckwithx.
        print(f"\nΣειρά του παίκτη {i}.\n")
        print("Δώστε την θέση της πρώτης κάρτας.")
        card1, card1r, card1c = cardpicker(difficulty, xdeck, deckwithx) # Card1 = 1η καρτα, Card1r = Card 1 Row, Card1c = Card 2 Column
        deckwithx = cardchanger(card1, card1r, card1c, deckwithx, False) # Αλλάζει τα χαρτιά που επιλέγονται και τα τυπώνει. False επειδή δεν απέτυχε ο παίκτης.
        printdeck(deckwithx, rng) # Τύπωμα τράπουλας παιχνιδιού με την πρώτη κάρτα γυρισμένη.
        print("Δώστε την θέση της δεύτερης κάρτας.") 
        while True: # Έλεγχος ετσι ωστε η 2η κάρτα να είναι διαφορετική απο την 1η.
            card2, card2r, card2c = cardpicker(difficulty, xdeck, deckwithx)
            if card2 != card1: 
                break
            else:
                print("Δεν μπορείτε να ανοίξετε την ίδια κάρτα 2 φορές. Δοκιμάστε ξανά.")
        deckwithx = cardchanger(card2, card2r, card2c, deckwithx, False) 
        printdeck(deckwithx, rng)  # Τύπωμα τράπουλας παιχνιδιού με την δεύτερη κάρτα γυρισμένη.
        if card1[1:2] == card2[1:2]: # Ελέγχεται εαν οι κάρτες ταιριάζουν και πόσους πόντους παίρνει ο παίκτης
            points[i-1] += cardpoints(card1) # Προσθέτει πόντους στον παίκτη.
            if card1[1:2] == "J": # Ειδική κάρτα ΒΑΛΕΣ.
                print("Συγχαρητήρια ανοίξατε δύο Βαλέδες, ξαναπαίζετε!\n")
                i -= 1
            elif card1[1:2] == "K": # Ειδική κάρτα ΡΗΓΑΣ.
                print("Συγχαρητήρια ανοίξατε δύο Ρηγάδες, ο επόμενος παικτης χάνει τη σειρά του!\n")
                if i+2 > players: # Εαν ο μεθεπόμενος παίκτης δεν υπαρχει, τότε παίζει ο πρωτος.
                    i = 1
                else:
                    i += 1
        elif (card1[1:2] == "K" and card2[1:2]== "Q" ) or (card2[1:2] == "K" and card1[1:2]== "Q" ): # Ειδική κάρτα ΝΤΑΜΑ-ΡΗΓΑΣ.
            print("Συγχαρητήρια ανοίξατε μία ντάμα και έναν ρήγα, παρακαλώ επιλέξτε και μία τρίτη διαφορετική καρτα!\n")
            while True: # Έλεγχος της τρίτης κάρτας.
                card3, card3r, card3c = cardpicker(difficulty, xdeck, deckwithx)
                if card3 == card2 or card3 == card1:
                    print("Παρακαλώ διαλέξτε διαφορετική κάρτα από τις δύο που έχετε ήδη ανοίξει.")
                else:
                    break
            deckwithx = cardchanger(card3, card3r, card3c, deckwithx, False)
            printdeck(deckwithx,rng)
            if card3[1:2] == "K" or card3[1:2]== "Q": # Εαν η τρίτη κάρτα είναι K η Q, δηλαδή σωστή.
                points[i-1] += cardpoints(card3) # Προσθέτει πόντους στον παίκτη.
                if card3[1:2] == card1[1:2]: # Εαν card3 = card1, τοτε ξαναγυρνάει την card2. Στην περίπτωση card3 = card2, ξαναγυρνάει την card1.
                    cardchanger(card2, card2r, card2c, deckwithx, True)
                else:
                    cardchanger(card1, card1r, card1c, deckwithx, True)
            else:
                print("Οι κάρτες που επιλέξατε δεν ταιριάζουν.\n")
                cardchanger(card1, card1r, card1c, deckwithx, True) # Επαναφέρει τα "X" στις θέσεις των 3 καρτών.
                cardchanger(card2, card2r, card2c, deckwithx, True) # Τrue επειδή ο παίκτης απέτυχε.
                cardchanger(card3, card3r, card3c, deckwithx, True)
        else:
            print("Οι κάρτες που επιλέξατε δεν ταιριάζουν.\n")
            cardchanger(card1, card1r, card1c, deckwithx, True) # Επαναφέρει τα "X" στις θέσεις των 2 καρτών.
            cardchanger(card2, card2r, card2c, deckwithx, True) # Τrue επειδή ο παίκτης απέτυχε.
        printdeck(deckwithx, rng)  # Τύπωμα τράπουλας παιχνιδιού πριν αλλάξει η γύρα.        
        if i < players: # Ποιος παικτης θα παιξει μετα;
            i += 1
        else:
            i = 1
        flatdeckwithx = [j for k in deckwithx for j in k] # Ενημέρωση της flatdeckwithx με το deck αφου τελειώσει η γύρα.
    return points # Επιστρέφει τους πόντους στην αρχική συνάρτηση X δυσκολίας σε μορφή λίστας.

#==================
# Βασικό Παιχνίδι - Boηθητικές Συναρτήσεις
#================== 

def printdeck(gamedeck, lim): # Τυπώνει τα ανακατεμένα χαρτιά σε στήλες και γραμμές ανάλογα με την δυσκολία.
    print("\n")
    numbersy = 1 # Αρχική τιμή για τους αριθμούς αριστερά απο κάθε γραμμή χαρτιών στον άξονα y.
    for i in range(1, lim+1): 
        if i < 11: # Τυπώνει τους αριθμούς πάνω απο τα χαρτια.
            print("  ", i, end =" ")  
        else: # 1 λιγοτερο κενο εαν ειναι >11 για ομοιομορφια.
            print(" ", i, end =" ")
    print("\n")
    print(numbersy, end =" ")
    card = 0
    for x in gamedeck: # Επανάληψη για κάθε λίστα που υπάρχει στην λίστα deck.
        for y in x: # Επανάληψη για κάθε υπολίστα που υπάρχει στην λίστα deck.
            if card % lim == 0 and card!=0: # Έλεγχος τιμής καρτών για αλλαγή γραμμής.
                print(" \n ")
                if numbersy < 4: # Έλεγχος τιμής για τους αριθμούς κάθετα αριστερά απο τα χαρτιά και τύπωμα.
                    numbersy += 1
                    print(numbersy, end =" ")
            print(y, end=" ")
            card += 1
    print("\n")

def cardpicker(difficulty, xdeck, deckwithx): # Δέχεται την στήλη και την γραμμή της κάρτας που θέλει να γυρίσει ο παίκτης.
    while True:
        while True: # Επανάληψη μέχρι να δωθεί ορθή τιμή
            try:  # Ελεγχος εγκυρότητας. Σε περιπτωση που δοθει κάτι <0, δεκαδικο η οποιαδηποτε λαθος τιμη, επαναλαμβάνει.
                col = int(input("Στήλη: "))
            except (TypeError, ValueError):
                col = 0
            if (col < 1) or (difficulty == 1 and col > 4) or (difficulty == 2 and col > 10) or (difficulty == 3 and col > 13): # Ελέγχεται εαν η τιμή που έχει δοθεί για στήλη είναι σωστή με βάση τη δυσκολία που έχει επιλεχτεί και >1.
                print("\nΛάθος τιμή. Παρακαλώ ξαναδώστε αριθμό στήλης.\n")
            else: 
                break
        while True: # Επανάληψη μέχρι να δωθεί ορθή τιμή
            try: # Ελεγχος εγκυρότητας. Σε περιπτωση που δοθει κάτι <0, δεκαδικο η οποιαδηποτε λαθος τιμη, επαναλαμβάνει.
                row = int(input("Γραμμή: "))     
            except (TypeError, ValueError):
                row = 0
            if row in range(1, 5): # Ελεγχος εγκυρότητας. Σε περιπτωση που δοθει κάτι <1 η >4, δεκαδικο η οποιαδηποτε λαθος τιμη, επαναλαμβάνει.
                break
            else:
                print("\nΛάθος τιμή. Παρακαλώ ξαναδώστε αριθμό γραμμής 1-4.\n")
        if deckwithx[row-1][col-1] != ' X  ': # Έλεγχος έτσι ωστε η κάρτα να μην έχει γυρίσει ήδη.
            print("Η κάρτα αυτή έχει ήδη γυρίσει. Δοκιμάστε ξανά.\n")
            continue
        else:
            break
    return xdeck[row-1][col-1], row-1, col-1 # Επιστρέφει την κάρτα και την θέση της.

def cardchanger(card, cardr, cardc, deckwithx, failed): # Γυρίζει τα χαρτιά που επιλέγονται.
    if failed == True: # Αν η failed == True, τότε επαναφέρει το "Χ" στην θέση της κάρτας. 
        deckwithx[cardr][cardc] = " X  "
    else: # Αλλιώς αντικαθιστά το "Χ" με την τιμή της κάρτας.
        deckwithx[cardr][cardc] = card
    return deckwithx

def cardpoints(card): # Υπολογίζει και επιστρέφει τους πόντους καρτών
    if card[1:2] == "A": # Αν η κάρτα είναι "Α", ο παίκτης παίρνει έναν πόντο.
        points = 1 
    elif (card[1:2] == "J") or (card[1:2] =="Q") or (card[1:2] == "K"): # Αν η κάρτα είναι "J", "Q" η "K" ο παίκτης παίρνει 10 πόντους.
        points = 10
    else: # Αλλιώς ο παίκτης παίρνει όσα λέει η κάρτα.
        points = card[1:3]
    print(f"\nΣυγχαρητήρια, πετύχατε σωστό ζευγάρι! Κερδίζετε {points} πόντους\n")
    return (int)(points)

def winner(points): # Επιλέγει τον νικητή βάσει πόντων.
    max = -1
    maxplayer = 1
    for i in range(len(points)): # Ψαχνω στην λίστα με τους πόντους.
        if points[i] > max: # Εαν ο παίκτης i έχει περισσότερους πόντους απο το μέγιστο, τοτε:
            max = points[i] # Aποθηκέυω τους πόντους του ως το νεο max.
            maxplayer = i+1 # Κρατάω το νούμερο του παίκτη.
    print(f"Ο νικητής είναι ο παίκτης {maxplayer} με {max} πόντους!") # Αγνόησε το Possibly Unbound - πάντα γεμίζει.

#==================
# Bonus I - Kώδικας: Διονύσης Debugging: Ελεονώρα
# Αρχική σκέψη ήταν να γίνει ενα με το βασικο παιχνιδι, αλλα για λόγους οργάνωσης και την διευκολυνση των μελων που δεν συμμετειχαν, δεν τα μπερδεψαμε.
# Για αυτο τον λογο, αν και ίσως όχι τοσο ιδανικο, αποφασισαμε το Bonus I να γινει σε ξεχωριστή συνάρτηση.
#==================
def playgamealt(players, difficulty, xdeck, deckwithx, rng): # Συνάρτηση όπου οι παίκτες παίζουν το alternative παιχνίδι.
    printdeck(xdeck, rng) # Eμεφανίζει το deck γυρισμενο για λόγους ευκολίας και debugging.
    print("\n\n\n\n")
    i = 1 # Aρχικοποιεί το ποιός παίκτης παίζει πρώτα.
    points = [0] * players # Λίστα με πόντους παικτών μηδενισμένη.
    printdeck(deckwithx, rng) # Εμφανίζει το deck με ολες τις κάρτες κρυμμένες.
    flatdeckwithx = [j for k in deckwithx for j in k] # Δημιουργεί ένα flattened deckwithx για την ορθότητα της συνθήκης της επανάληψης.
    while ' X  ' in flatdeckwithx: # Επανάληψη μέχρι να γυρίσουν όλες οι κάρτες, δηλαδη να μην υπαρχει Χ στο deckwithx.
        print(f"\nΣειρά του παίκτη {i}.\n")
        print("Δώστε την θέση της πρώτης κάρτας.")
        card1, card1r, card1c = cardpicker(difficulty, xdeck, deckwithx) # Card1 = 1η καρτα, Card1r = Card 1 Row, Card1c = Card 2 Column
        deckwithx = cardchanger(card1, card1r, card1c, deckwithx, False) # Αλλάζει τα χαρτιά που επιλέγονται και τα τυπώνει. False επειδή δεν απέτυχε ο παίκτης.
        printdeck(deckwithx, rng) # Τύπωμα τράπουλας παιχνιδιού με την πρώτη κάρτα γυρισμένη.
        print("Δώστε την θέση της δεύτερης κάρτας.") 
        while True: # Έλεγχος ετσι ωστε η 2η κάρτα να είναι διαφορετική απο την 1η.
            card2, card2r, card2c = cardpicker(difficulty, xdeck, deckwithx)
            if card2 != card1: 
                break
            else:
                print("Δεν μπορείτε να ανοίξετε την ίδια κάρτα 2 φορές. Δοκιμάστε ξανά.")
        deckwithx = cardchanger(card2, card2r, card2c, deckwithx, False) 
        printdeck(deckwithx, rng)  # Τύπωμα τράπουλας παιχνιδιού με την δεύτερη κάρτα γυρισμένη.
        if (card1[1:2] == "K" and card2[1:2]== "Q" ) or (card2[1:2] == "K" and card1[1:2]== "Q" ): # Ειδική κάρτα ΝΤΑΜΑ-ΡΗΓΑΣ. Γίνεται ελεγχος πρώτα σε αντιθεση με το βασικο παιχνίδι.
            print("Συγχαρητήρια ανοίξατε μία ντάμα και έναν ρήγα, παρακαλώ επιλέξτε και μία τρίτη διαφορετική καρτα!\n")
            while True: # Έλεγχος της τρίτης κάρτας.
                card3, card3r, card3c = cardpicker(difficulty, xdeck, deckwithx)
                if card3 == card2 or card3 == card1:
                    print("Παρακαλώ διαλέξτε διαφορετική κάρτα από τις δύο που έχετε ήδη ανοίξει.")
                else:
                    break
            deckwithx = cardchanger(card3, card3r, card3c, deckwithx, False)
            printdeck(deckwithx,rng)
            if card3[1:2] == "K" or card3[1:2]== "Q": # Εαν η τρίτη κάρτα είναι K η Q, δηλαδή σωστή.
                points[i-1] += cardpointsalt(card1, card3) # Προσθέτει πόντους στον παίκτη.
                if card3[1:2] == card1[1:2]: # Εαν card3 = card1, τοτε ξαναγυρνάει την card2. Στην περίπτωση card3 = card2, ξαναγυρνάει την card1.
                    cardchanger(card2, card2r, card2c, deckwithx, True)
                else:
                    cardchanger(card1, card1r, card1c, deckwithx, True)
            elif card1[3:4] == card3[3:4]: # Εαν ταιριαζει το συμβολο της 3ης καρτας με την 1η.
                points[i-1] += cardpointsalt(card1, card3) # Προσθέτει πόντους στον παίκτη.
                cardchanger(card2, card2r, card2c, deckwithx, True)
            elif card2[3:4] == card3[3:4]: # Εαν ταιριαζει το συμβολο της 3ης καρτας με την 2η.
                points[i-1] += cardpointsalt(card2, card3) # Προσθέτει πόντους στον παίκτη.
                cardchanger(card1, card1r, card1c, deckwithx, True)                
            else:
                print("Οι κάρτες που επιλέξατε δεν ταιριάζουν.\n")
                cardchanger(card1, card1r, card1c, deckwithx, True) # Επαναφέρει τα "X" στις θέσεις των 3 καρτών.
                cardchanger(card2, card2r, card2c, deckwithx, True) # Τrue επειδή ο παίκτης απέτυχε.
                cardchanger(card3, card3r, card3c, deckwithx, True)
        elif card1[1:2] == card2[1:2] or card1[3:4] == card2[3:4]: # Ελέγχεται εαν οι κάρτες ταιριάζουν και πόσους πόντους παίρνει ο παίκτης.
            points[i-1] += cardpointsalt(card1, card2) # Προσθέτει πόντους στον παίκτη.
            if card1[1:2] == "J" and card2[1:2] == "J": # Ειδική κάρτα ΒΑΛΕΣ.
                print("Συγχαρητήρια ανοίξατε δύο Βαλέδες, ξαναπαίζετε!\n")
                i -= 1
            elif card1[1:2] == "K" and card2[1:2] == "K": # Ειδική κάρτα ΡΗΓΑΣ.
                print("Συγχαρητήρια ανοίξατε δύο Ρηγάδες, ο επόμενος παικτης χάνει τη σειρά του!\n")
                if i+2 > players: # Εαν ο μεθεπόμενος παίκτης δεν υπαρχει, τότε παίζει ο πρωτος.
                    i = 1
                else:
                    i += 1
        else:
            print("Οι κάρτες που επιλέξατε δεν ταιριάζουν.\n")
            cardchanger(card1, card1r, card1c, deckwithx, True) # Επαναφέρει τα "X" στις θέσεις των 2 καρτών.
            cardchanger(card2, card2r, card2c, deckwithx, True) # Τrue επειδή ο παίκτης απέτυχε.
        printdeck(deckwithx, rng)  # Τύπωμα τράπουλας παιχνιδιού πριν αλλάξει η γύρα.        
        if i < players: # Ποιος παικτης θα παιξει μετα;
            i += 1
        else:
            i = 1
        flatdeckwithx = [j for k in deckwithx for j in k] # Ενημέρωση της flatdeckwithx με το deck αφου τελειώσει η γύρα.
        if not deckcheck(flatdeckwithx, xdeck): # Eλέγχει εαν υπάρχουν άλλοι συνδυασμοί.
            print("Δεν υπάρχουν άλλοι δυνατοί συνδυασμοί. Τερματισμός παιχνιδιού.\n") # Εμφανίζει μήνυμα τερματισμού και σπάει την επαναλήψη, τερματίζοντας το παιχνίδι.
            break
    return points # Επιστρέφει τους πόντους στην αρχική συνάρτηση X δυσκολίας σε μορφή λίστας.

def cardpointsalt(card1, card2): # Υπολογίζει και επιστρέφει το αθροισμα των ποντων των καρτων.
    points = 0
    if card1[1:2] == "A":  # Eλεγχος για την 1η καρτα.
        points += 1 
    elif (card1[1:2] == "J") or (card1[1:2] =="Q") or (card1[1:2] == "K"): # Αν η κάρτα είναι "J", "Q" η "K" ο παίκτης παίρνει 10 πόντους.
        points += 10
    else: # Αν δεν ειναι Α η "KQJ", τοτε προσθέτει την αξία της καρτας.
        points += (int)(card1[1:3])
    if card2[1:2] == "A": 
        points += 1 
    elif (card2[1:2] == "J") or (card2[1:2] =="Q") or (card2[1:2] == "K"): # Αν η κάρτα είναι "J", "Q" η "K" ο παίκτης παίρνει 10 πόντους.
        points += 10
    else: # Αν δεν ειναι Α η "KQJ", τοτε προσθέτει την αξία της καρτας.
        points += (int)(card2[1:3])
    print(f"\nΣυγχαρητήρια, πετύχατε σωστό ζευγάρι! Κερδίζετε {points} πόντους\n") # Εμφανιζει το συνολικό άθροισμα των πόντων.
    return (int)(points)

def deckcheck(flatdeckwithx, xdeck): # Ελέγχει εαν υπάρχουν δυνατοί συνδυασμοί στο deckwithx. Εαν όχι, επιστρέφει False.
    xdeck = [j for k in xdeck for j in k] # Kάνει flatten το xdeck έτσι ώστε να γίνει ευκολότερα ο έλεγχος.
    checkdeck = [xdeck[i] for i in range(len(flatdeckwithx)) if flatdeckwithx[i] == " X  "] # Δημιουργεί ένα νέο checkdeck το οποίο περιέχει τις κάρτες που δεν εχουν γυρίσει.
    for j in range(len(checkdeck)): # Eλέγχει εαν υπαρχουν συνδυασμοι βασει του αριθμου και του συμβολου.
        for k in range(j, len(checkdeck)):
            if checkdeck[j] != checkdeck[k]:
                if checkdeck[j][1:2] == checkdeck[k][1:2] or checkdeck[j][3:4] == checkdeck[k][3:4]:
                    return True
    return False

main() # Κλήση αρχικής συνάρτησης