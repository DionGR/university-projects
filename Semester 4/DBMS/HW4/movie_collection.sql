-- Movie Collection has a pair primary key as these records aren't unique on their own
alter table movie_collection add primary key (movie_id, collection_id);