import csv, sys, string, math

path = None
slashes = None

# usage: python gen_ddl.py csv_file_to_be_converted
if len(sys.argv) != 2:
    print('Invalid number of parameters')
    print('Usage: python gen_ddl.py <path_to_input_csv_file>')
    print('Example: python gen_ddl.py airbnb/listings.csv')
    sys.exit()
    
csv_file = sys.argv[1]
print('csv file: ' + csv_file)

filename, extension = csv_file.rsplit('.', 1)

if filename.find('/') > -1:
    path, name = filename.rsplit('/', 1)
    slashes = '/'
elif filename.find('\\') > -1:
    path, name = filename.rsplit('\\', 1)
    slashes = '\\'
else:
    name = filename
    slashes = ''
    path = ''

sql_file = path + slashes + 'create_' + name + '.sql'
print('sql file: ' + sql_file)	
    
table_name = name.title()
print('table name: ' + table_name)

def round_up(x):
    return int(math.ceil(x / 10.0)) * 10	

def is_date(str):	
    # expected date format: yyyy-mm-dd
    is_hyphen1 = str[4:5] 
    is_hyphen2 = str[7:8]
    if is_hyphen1 == "-" and is_hyphen2 == "-":
        return True;
    else:
        return False;
        
def find_max_varchar_length(data_rows, i):
    # j = row index, i = column index
    max_varchar_length = 1
    for j in range(len(data_rows)):
        length = len(data_rows[j][i])
        if length > max_varchar_length:
            max_varchar_length = length
    
    return round_up(max_varchar_length)
    
    
with open(sql_file, 'w+') as out_file:
    out_file.write('create table ' + table_name + '(\n')

    with open(csv_file, 'rt', encoding='utf8') as in_file:
        reader = csv.reader(in_file, delimiter=',')
        header = next(reader)
        
        data_rows = []
        for row in reader:
            data_rows.append(row)
        
        # get two rows of data, to improve accuracy of datatype assignment
        first_row = data_rows[1] # sample one row of data
        last_row_index = len(data_rows) - 1
        last_row = data_rows[last_row_index] # sample another row of data
    
        # i = column index
        for i in range(len(header)):
            col_name = header[i]
            val1 = first_row[i]
            val2 = last_row[i]
            if val1.isdigit() and val2.isdigit():
                if int(val1) > 2147483647 or int(val2) > 2147483647:
                    out_file.write('   ' + col_name.strip() + ' bigint')
                else:
                    out_file.write('   ' + col_name.strip() + ' int')
            else:	
                # we have a boolean, varchar or date
                # assume that a decimal is represented as a varchar in the csv file due to formatting
                if (val1 == 't' or val1 == 'f') and (val2 == 't' or val2 == 'f'):
                    out_file.write('   ' + col_name.strip() + ' boolean')
                elif (is_date(val1) and is_date(val2)):
                    out_file.write('   ' + col_name.strip() + ' date')
                else:
                    varchar_length = find_max_varchar_length(data_rows, i)
                    if varchar_length > 3000:
                        out_file.write('   ' + col_name.strip() + ' text')
                    else:
                        out_file.write('   ' + col_name.strip() + ' varchar(' + str(varchar_length) + ')')
            
            if i < len(header)-1:
                out_file.write(',\n')
            else:
                out_file.write('\n')
        
        out_file.write(');')

print('Finished generating ' + sql_file + '. Open the file and make the appropriate changes before creating the table.') 
