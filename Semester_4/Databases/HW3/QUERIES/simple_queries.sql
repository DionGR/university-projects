-- Query #1
-- Finds all the movies that contain the word STAR and their average rating, sorted by title.
-- Count: 17
select m.title, avg(r.rating)
from movie m 
join ratings r
on r.movie_id = m.id
where m.title like '%Star%' or m.title like '%star%'
group by m.title
order by m.title;

-- Query #2
-- Select all the lowest-rated movies
-- Count: 185
select distinct m.title, avg(r.rating)
from movie m 
join ratings r
on m.id = r.movie_id
group by m.title
having avg(r.rating) <= all(select avg(r2.rating) from ratings r2 join movie m2 on m2.id = r2.movie_id group by m2.id)
order by m.title;


-- Query #3
-- Selects all the movies in alphabetical order and their average rating (CONTAINS NULL VALUES IF NO RATING)
-- Count: 38642
select m.title, avg(r.rating)
from movie m full outer join ratings r 
on m.id = r.movie_id 
group by m.title
order by m.title;

-- Query #4
-- The maximum revenue generated for each genre that has a movie for it
-- Count: 9
select gnr.name, max(gnr.revenue)
from (select m.title, g.name, m.revenue
      from (movie_genres mg join movie m on m.id = mg.movie_id) join genre g on g.id = mg.genre_id) as gnr
group by gnr.name
order by gnr.name;

-- Query #5
-- Select all the movies that had a loss, ordered by the loss amount
-- Count: 1621
select m.title, (m.budget - m.revenue) as loss 
from movie m
where m.revenue < m.budget
order by loss desc;

-- Query #6
-- Selects the top 50 keywords that appear in the most popular movies
-- Count: 10
select distinct kwrd.name, avg(kwrd.popularity) as popularity_avg
from (select k.name, m.popularity
      from (movie_keywords mk join movie m on m.id = mk.movie_id) join keyword k on k.id = mk.keyword_id) as kwrd
group by kwrd.name
order by popularity_avg desc
limit 50;

-- Query #7
-- Finds movies , the company that produced them and their revenue that
-- had an above average revenue with a below average budget
-- Count: 921
select distinct movie.title as Movie , productioncompany.name as Producer, movie.revenue as Earnings
from (movie join movie_productioncompanies on movie.id = movie_productioncompanies.movie_id)
join productioncompany on productioncompany.id = movie_productioncompanies.pc_id
where revenue > (
	select avg(revenue)
	from movie)
	and budget < (
	select avg(budget)
	from movie
	);

-- Query #8
-- Finds the number of Movies released each year , between 1995 and 2000
-- Count: 6
select count(movie.title),extract(year from movie.release_date) as Year
from movie
where release_date between '1995-1-1'and '2000-1-1'
group by extract(year from release_date)
order by year;

-- Query #9
-- Find all languages which had a non-zero total revenue
-- Count: 25
select original_language 
from (
	select original_language,sum(revenue) as Total_Revenue
	from movie
	group by original_language
	order by total_revenue) as r1
where total_revenue <> 0;

-- Query #10
-- Find all the movie titles, their runtime, their tagline(if any) and their homepage(if any)
-- that contain the keyword pregnancy or stepmother
-- Count : 38
select distinct m.title, m.runtime, m.tagline, m.homepage
from movie_cast mc full outer join movie m 
on  mc.movie_id = m.id 
where mc.gender = 1
and m.title in(
	select m2.title
	from movie m2, keyword k2, movie_keywords mk2
	where m2.id = mk2.movie_id and mk2.keyword_id = k2.id and (k2.name = 'pregnancy' or k2.name = 'stepmother'))
order by m.runtime desc;

-- Query #11
-- Find movies and their release date/overview that are not in english 
-- and have a higher popularity than the average english movie
-- Count : 411
select movie.title ,movie.overview, movie.release_date
from movie
where movie.original_language <> 'en' and popularity > (
select avg(popularity)
from movie
where original_language='en')
order by popularity;

-- Query #12
-- Find all cast and the minimum rating a movie they played in ever received.
-- Count: 32983
select movie_cast.cast_id, movie_cast.name, min(rating)
from movie_cast join ratings
on movie_cast.movie_id = ratings.movie_id 
group by movie_cast.name,movie_cast.cast_id
order by min desc;
