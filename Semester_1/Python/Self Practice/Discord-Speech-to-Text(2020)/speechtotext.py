import speech_recognition as sr 
import discord
from discord import Webhook, RequestsWebhookAdapter
from os import path, remove


client = discord.Client()

""" File Creation Function"""
def file_Creation(filename, filevar, objecttype): # file_Creation(NAME OF FILE, FILE VARIABLE, OBJECT TYPE (USERNAME OR WEBHOOK))
    if path.exists(filename): # If the file exists, read from it.
        filevar = open(filename, "r")
        print(objecttype.capitalize() + " found!\n\n")
        object = str(filevar.read())
    else: # If it doesn't exist, create the file and write the input into it.
        filevar = open(filename, "w+")
        object = input(f"What is your {objecttype}?\t")
        filevar.write(object)
    filevar.close()
    return object # Return the string contained in the file.

""" Main Function """
def main():
    username = file_Creation("userid.txt", "userfile", "username") # Create/look for the file with the username.
    while True:
        webhookid = file_Creation("webhook.txt", "webhookfile", "webhook") # Create/look for the file with the webhook.
        try: # Filter out errors with the webhook function. 
            webhook = Webhook.from_url(webhookid, adapter = RequestsWebhookAdapter()) # Connects the webhook given with the discord server.
            break
        except: 
            print("There is an issue with the webhook. Please try retyping it.")
            remove("webhook.txt") # Deletes the webhook file.
            continue
    while True:
        choice = input("Press S to speak or anything else to exit!\n")
        if choice.capitalize() == "S":
            r = sr.Recognizer() 
            with sr.Microphone() as source:# Takes input from the mic.
                print("Say something...")
                audio = r.listen(source)
            try: # Look for errors with voice recognition.
                webhook.send(f"**{username} said:**\n" + r.recognize_google(audio)) # Print the speech-to-text in the text channel.
            except Exception as e:
                print("Error:" + str(e))
        else:
            break

if __name__ == '__main__':
    main()
