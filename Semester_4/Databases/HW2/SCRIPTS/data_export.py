import csv, sys, string, math, ast

    
csv_file = sys.argv[1]


#csv_file = open(csv_file, 'r')
#csvreader = csv.reader(csv_file)  # Create a CSV reader object
#header = next(csvreader)          # Read the header row first
#print(header)

#sqlf = sys.argv[1].split('.')[0] 
#table_name = sqlf.split("/")[-1]
#sqlf = "insert_" + table_name + ".sql"


#with open(sqlf, 'w+') as out:
#    for row in csvreader:
#        out.write('insert into ' + table_name + '(id,name)\n') # Table headers here 
#        out.write('values (' + row[0] + ',' + row [1] + ');\n\n') # row[X] depending on # of columns
        
# JSON CODE
# # Open the CSV file with Python
with open(csv_file, 'r') as csv_file:
    csvreader = csv.reader(csv_file)  # Create a CSV reader object
    header = next(csvreader)          # Read the header row first
    
