-- 1) Number of movies released per year
select count(title) as movies_released, extract(year from release_date) as year
from movie
where release_date is not null
group by year
order by year asc;

-- 2) Number of movies belonging to each genre
select count(title) as movies_released, g.name
from movie m, movie_genres as mg, genre g
where m.id = mg.movie_id and mg.genre_id = g.id
group by g.name
order by movies_released asc;

-- 3) Number of movies released per year per genre
select extract(year from release_date) as year,  g.name, count(title) as movies_released
from movie m, movie_genres as mg, genre g
where m.id = mg.movie_id and mg.genre_id = g.id and m.release_date is not null
group by g.name, year
order by year asc, g.name asc;

-- 4) Max budget per year
select extract(year from m.release_date) as year, max(m.budget) as max_budget
from movie m
where m.release_date is not null and m.budget is not null
group by year
order by year;

-- 5) Total revenue per year for Harrison Ford
select sum(revenue),extract(year from release_date) as year
from Movie_cast c
join Person p
on c.person_id = p.person_id
join Movie m
on c.movie_id = m.id
where p.name = 'Johnny Depp'
group by year;

-- 6) Avg rating per user
select avg(rating), user_id
from ratings
group by user_id
order by user_id asc;

-- 7) Ratings count per user
select count(rating), user_id
from ratings
group by user_id
order by user_id asc;

-- 9) Avg rating per movie genre
select avg(rating), name
from genre g join
movie_genres m on g.id = m.genre_id
join ratings r on r.movie_id = m.movie_id
group by name;