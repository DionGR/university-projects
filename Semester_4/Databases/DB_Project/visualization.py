import psycopg2
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import axes3d

# Database Connection Info
HOST = "auebserver22.postgres.database.azure.com"
NAME = "MovieLens"
USER = "examiner@auebserver22"
PASSWORD = "Trifonas1!"
SSLMODE = "require"

# Construct connection string
conn_string = "host={0} user={1} dbname={2} password={3} sslmode={4}".format(HOST, USER, NAME, PASSWORD, SSLMODE)
conn = psycopg2.connect(conn_string)
print("Connection established")
cursor = conn.cursor()

# 1 - Number of movies released per year
cursor.execute("select count(title) as movies_released, extract(year from release_date) as year from movie where release_date is not null group by year order by year asc;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

x = [rows[i][0] for i in range(0, len(rows))]
y = [int(rows[i][1]) for i in range(0, len(rows))]

plt.suptitle('Number of movies released per year')
plt.xticks(np.arange(min(y) - 2, max(y) + 1, 5.0))
plt.yticks(np.arange(min(x) - 1, max(x) + 25, 25.0))
plt.xlabel(column_names[1])
plt.ylabel(column_names[0])
plt.bar(y, x)
plt.show()

# 2 - Number of movies belonging to each genre
cursor.execute("select count(title) as movies_released, g.name from movie m, movie_genres as mg, genre g where m.id = mg.movie_id and mg.genre_id = g.id group by g.name order by movies_released asc;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

x = [rows[i][0] for i in range(0, len(rows))]
y = [rows[i][1] for i in range(0, len(rows))]

plt.suptitle('Number of movies belonging to each genre')
plt.yticks(np.arange(0, max(x) + 250, 250.0))
plt.xlabel(column_names[1])
plt.ylabel(column_names[0])
plt.bar(y, x)
plt.show()

# 3 - Number of movies released per year per genre
cursor.execute("select extract(year from release_date) as year,  g.name, count(title) as movies_released from movie m, movie_genres as mg, genre g where m.id = mg.movie_id and mg.genre_id = g.id and m.release_date is not null group by g.name, year order by year asc, g.name asc;")
rows = cursor.fetchall()

x = [rows[i][1] for i in range(0, len(rows))]
y = [int(rows[i][0]) for i in range(0, len(rows))]
z = [rows[i][2] for i in range(0, len(rows))]

fig = plt.figure()
ax1 = fig.add_subplot(111, projection='3d')
ax1.set_facecolor((1.0, 1.0, 1.0))
xCategories = x
i = 0
xDict = {}
xl = []
for category in xCategories:
  if category not in xDict:
    xDict[category] = i
    xl.append(i)
    i += 1
  else:
    xl.append(xDict[category])

zl = np.zeros(len(xl))
dx = np.ones(len(xl))*0.1
dy = np.ones(len(xl))
dz = z
ax1.bar3d(xl, y, zl, dx, dy, dz)
ax1.set_zlim3d(0, max(z) + 50, True)
ax1.set_ylim3d(min(y) - 5, max(y) + 5)
ax1.set_xlabel('Genre')
ax1.set_ylabel('Year')
ax1.set_zlabel('Number of movies')
plt.xticks(range(len(xDict.values())), xDict.keys())
plt.show()

# 4 - Max budget per year
cursor.execute("select extract(year from m.release_date) as year, max(m.budget) as max_budget from movie m where m.release_date is not null and m.budget is not null group by year order by year;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

x = [int(rows[i][0]) for i in range(0, len(rows))]
y = [rows[i][1]/1000000 for i in range(0, len(rows))]

plt.suptitle('Max budget per year (in mil USD)')
plt.xticks(np.arange(min(x) - 2, max(x) + 1, 5.0))
plt.yticks(np.arange(min(y), max(y) + 10, 10.0))
plt.xlabel(column_names[0])
plt.ylabel(column_names[1].replace("_", " "))
plt.bar(x, y)
plt.show()



# 5 - Total revenue per year for Harrison Ford
cursor.execute("select sum(revenue) as revenue, extract(year from release_date) as year from Movie_cast c join Person p on c.person_id = p.person_id join Movie m on c.movie_id = m.id where p.name = 'Johnny Depp' group by year;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

y = [rows[i][0]/1000000 for i in range(0, len(rows))]
x = [rows[i][1] for i in range(0, len(rows))]

plt.suptitle('Total revenue per year for Johnny Depp (in mil USD)')
plt.yticks(np.arange(0, max(y) + 50.0, 50.0))
plt.xticks(np.arange(min(y), max(x) + 3, 5.0))
plt.xlabel(column_names[1])
plt.ylabel(column_names[0])
plt.bar(x, y)
plt.show()

# 6 - Average rating per user
cursor.execute("select avg(rating),user_id from ratings group by user_id order by user_id asc;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

y = np.array([rows[i][0] for i in range(0, len(rows))])
x = np.array([rows[i][1] for i in range(0, len(rows))])

plt.suptitle('Average Rating Per User')
plt.xlabel(column_names[1])
plt.ylabel(column_names[0])
plt.scatter(x, y, s = 4, c = 'b', marker='o')
plt.show()

# 7 - Ratings count per user
cursor.execute("select count(rating), user_id from ratings group by user_id order by user_id asc;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

x = np.array([rows[i][1] for i in range(0, len(rows))])
z = np.array([rows[i][0] for i in range(0, len(rows))])

plt.suptitle('Ratings Per User')
plt.xlabel(column_names[1])
plt.ylabel(column_names[0])
plt.scatter(x, z, s = 4, c = 'b', marker = 'o')
plt.show()

# # 8 - X Axis = Number of ratings per user, Y Axis = Average rating per user
plt.suptitle('X Axis = Number of ratings per user, Y Axis = Average rating per user')
plt.xlabel("Number of ratings per user")
plt.ylabel("Average rating per user")
plt.scatter(z, y, s = 4, c = 'b', marker = 'o')
plt.show()

# 9 - Average rating per movie genre
cursor.execute("select avg(rating), name from genre g join movie_genres m on g.id = m.genre_id join ratings r on r.movie_id = m.movie_id group by name;")
rows = cursor.fetchall()
column_names = [desc[0] for desc in cursor.description]

x = [rows[i][0] for i in range(0, len(rows))]
y = [rows[i][1] for i in range(0, len(rows))]

plt.suptitle('Average Rating per Movie Genre')
plt.yticks(np.arange(0, 5.0, 0.5))
plt.xlabel(column_names[1])
plt.ylabel(column_names[0])
plt.bar(y, x)
plt.show()
