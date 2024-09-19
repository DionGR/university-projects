import csv, sys, string, math, ast

   
json_file = "keywords.csv" 
csv_file = "Movie_keyword.csv"
        
# JSON CODE FOR MOVIE_KEYWORDS
# # Open the CSV to write to
with open(csv_file, 'w+', encoding='utf-8') as csv_file:                  
# Open the CSV file with the nested JSON we are trying to convert 
    with open(json_file, 'r', encoding='utf-8') as json_file:
        csvreader = csv.reader(json_file)   # Create a CSV reader object
        csvwriter = csv.writer(csv_file)    # Create a CSV writer object
        csvwriter.writerow(["movie_id","keyword_id"]) # Write Header for CSV
        header = next(csvreader)            # Read the header row first
        for row in csvreader:               # Every row of this csv file has nested JSON in its second cell 
            movie_id = row[0]               # First collumn cell contains the movie id. 
            data = ast.literal_eval(row[1]) # Data is a list of dictionaries, each element of the list is a dictionary that we want to extract data from
            for dictionary in data:
                keyword_id = dictionary['id']
                csvwriter.writerow([movie_id, keyword_id])
                
json_file = "keywords.csv" 
csv_file = "keyword.csv"
        
        
# JSON CODE FOR KEYWORD
# # Open the CSV to write to
with open(csv_file, 'w+', encoding='utf-8') as csv_file:                  
# Open the CSV file with the nested JSON we are trying to convert 
    with open(json_file, 'r', encoding='utf-8') as json_file:
        csvreader = csv.reader(json_file)   # Create a CSV reader object
        csvwriter = csv.writer(csv_file)    # Create a CSV writer object
        csvwriter.writerow(["id","name"]) # Write Header for CSV
        header = next(csvreader)            # Read the header row first
        for row in csvreader:               # Every row of this csv file has nested JSON in its second cell 
            data = ast.literal_eval(row[1]) # Data is a list of dictionaries, each element of the list is a dictionary that we want to extract data from
            for dictionary in data:
                keyword_id = dictionary['id']
                name = dictionary['name']
                csvwriter.writerow([keyword_id, name])

