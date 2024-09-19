DELETE FROM movie_cast
WHERE ctid IN(
SELECT t1.ctid
FROM movie_cast t1
NATURAL JOIN movie_cast t2
WHERE t1.ctid>t2.ctid);

DELETE FROM movie_collection
WHERE ctid IN(
SELECT t1.ctid
FROM movie_collection t1
NATURAL JOIN movie_collection t2
WHERE t1.ctid>t2.ctid);

DELETE FROM movie_crew
WHERE ctid IN(
SELECT t1.ctid
FROM movie_crew t1
NATURAL JOIN movie_crew t2
WHERE t1.ctid>t2.ctid);

DELETE FROM movie_genres
WHERE ctid IN(
SELECT t1.ctid
FROM movie_genres t1
NATURAL JOIN movie_genres t2
WHERE t1.ctid>t2.ctid);

DELETE FROM movie_keywords
WHERE ctid IN(
SELECT t1.ctid
FROM movie_keywords t1
NATURAL JOIN movie_keywords t2
WHERE t1.ctid>t2.ctid);

DELETE FROM movie_productioncompanies
WHERE ctid IN(
SELECT t1.ctid
FROM movie_productioncompanies t1
NATURAL JOIN movie_productioncompanies t2
WHERE t1.ctid>t2.ctid);

update movie_cast
set character='UNKNOWN'
where character is NULL;