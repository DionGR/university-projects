create table Ratings(
   user_id int,
   movie_id int,
   rating varchar(10),
   PRIMARY KEY (user_id, movie_id)
);
