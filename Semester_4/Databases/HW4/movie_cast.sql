-- Movie Cast has a 4-way key
alter table movie_cast add primary key (movie_id, cast_id, person_id, character);