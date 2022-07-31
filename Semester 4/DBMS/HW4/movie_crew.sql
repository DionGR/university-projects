-- Movie Crew has a three-way primary key as the same person can be in the same movie multiple times under a different job
alter table movie_crew add primary key (movie_id, person_id, job);