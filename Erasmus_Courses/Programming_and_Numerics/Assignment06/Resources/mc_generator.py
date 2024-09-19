import ipywidgets as widgets
import sys
import random
from IPython.display import display
from IPython.display import clear_output


def generate_mc(answers,question,answer,custom_feedback=[],shuffle=True):
    out = widgets.Output()
    answer_string = answers[answer-1] #svaret på spørsmålet
    if shuffle:
        if(len(custom_feedback)==len(answers)): #dersom vi har feedback må denne sorteres shuffles sammen med svarene
            mapIndexPosition = list(zip(answers, custom_feedback))
            random.shuffle(mapIndexPosition)
            answers, custom_feedback = zip(*mapIndexPosition)
        else: #ellers shufler vi bare svarene
            random.shuffle(answers)
    alternativ = widgets.RadioButtons(
    options=answers,
    description='',
    layout=widgets.Layout(width='1500px'),
    disabled=False)
    answer=answers.index(answer_string)
    print('\033[1m',question,'\033[0m')
    button = widgets.Button(description="Sjekk svaret")
    display(alternativ)
    display(button)
    def check_answer(b):
        a = alternativ.value
        if(a==answer_string):
            color = '\x1b[6;30;42m' + "Riktig." + '\x1b[0m' +"\n" #fargen blir da grønn
            if(len(custom_feedback)==len(answers)):
                color += custom_feedback[answer]
        else:
            color = '\x1b[5;30;41m' + "Feil. " + '\x1b[0m' +"\n" #ellers blir fargen rød
            if(len(custom_feedback)==len(answers)):
                color += custom_feedback[answer]
        with out:
            clear_output()
            print(color)
            
            
      
    display(out)
    button.on_click(check_answer)
    
def generate_mc_no_answer(answers,question):
    out = widgets.Output()
    alternativ = widgets.RadioButtons(
    options=answers,
    description='',
    layout=widgets.Layout(width='1500px'),
    disabled=False)
    
    print('\033[1m',question,'\033[0m')
    display(alternativ) 
    display(out)
    return alternativ

def generate_multiple_no_answer(filename): #Tar inn tekst med linjer på formatet spørsmål£alternativ1£alternativ2osv
    f = open(filename,"r",encoding="UTF-8")
    lines = f.readlines()
    f.close()
    #buttons = []
    for line in lines:
        line = line.split("£")
        question = line[0]
        answers = line[1:]
        generate_mc_no_answer(answers,question)
        #buttons.append(generate_mc_no_answer(answers,question))
   # button = widgets.Button(description="Lagre svar")
   # display(button)
    """def save_answers(b):
        saved_answers = []
        for radiobutton in buttons:
            saved_answers.append(radiobutton.value)
    button.on_click(save_answers)"""
    
def generate_multiple(filename): #Tar inn tekst på formatet spørsmål, antall alternativer, [alternativer], rett svar (tall)
    f = open(filename,"r",encoding="UTF-8")
    lines = f.readlines()
    f.close()
    for line in lines:
        line = line.split(",")
        question = line[0]
        answers = []
        for answer in line[2:2+int(line[1])]:
            answers.append(answer)
        generate_mc(answers,question,int(line[len(line)-1]))
        
        
def generate_multiple1(filename): #Tar inn tekst på formatet spørsmål, rett svar (streng), [andre alternativer]
    f = open(filename,"r",encoding="UTF-8")
    lines = f.readlines()
    f.close()
    for line in lines:
        line = line.split(",")
        question = line[0]
        answer = line[1]
        answers = line[2:]+[answer]
        generate_mc(answers,question,len(answers))
        
        
def generate_multiple3(filename): #her er førstelinje alle svarene på form f.eks abbcdd...
                                  #neste linjer: spørsmål£alternativ1£alternativ2osv
    f = open(filename,"r",encoding="UTF-8")
    lines = f.readlines()
    f.close()
    correct_answers=[]
    for letter in lines[0]:
        correct_answers.append(ord(letter) - 96)
    for i in range(1,len(lines)-1):
        line = lines[i].split("£")
        question = line[0]
        answers = line[1:]
        generate_mc(answers,question,correct_answers[i-1])