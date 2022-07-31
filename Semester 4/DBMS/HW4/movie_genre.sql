-- Movie Genre has a pair key as these records aren't unique on their own
alter table movie_genres add primary key (genre_id, movie_id);