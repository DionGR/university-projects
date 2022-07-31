ALTER TABLE Movie_Cast
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id); 

ALTER TABLE Movie_Collection
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id),
ADD FOREIGN KEY (collection_id) REFERENCES  Collection(id); 

ALTER TABLE Movie_Crew
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id); 

ALTER TABLE Movie_Genres
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id),
ADD FOREIGN KEY (genre_id) REFERENCES  Genre(id); 

ALTER TABLE Movie_Keywords
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id),
ADD FOREIGN KEY (keyword_id) REFERENCES  Keyword(id); 

ALTER TABLE Movie_Productioncompanies
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id),
ADD FOREIGN KEY (pc_id) REFERENCES  Productioncompany(id); 

ALTER TABLE Ratings
ADD FOREIGN KEY (movie_id) REFERENCES  Movie(id);