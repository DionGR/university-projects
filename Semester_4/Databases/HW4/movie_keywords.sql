-- Movie Keywords has a pair key as these records aren't unique on their own
alter table movie_keywords add primary key (keyword_id, movie_id);