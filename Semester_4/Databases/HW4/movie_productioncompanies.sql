-- Movie Productioncompanies has a pair key as these records aren't unique on their own
alter table movie_productioncompanies add primary key (movie_id, pc_id);