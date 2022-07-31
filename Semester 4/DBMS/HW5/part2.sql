-- Names of all the actors that have played in a set of genres (assumed we picked that because we want to film such a movie)
-- and their movies have all made profits as well as the ratings were over 4.0
select name
from person p
join movie_cast mc 
on mc.person_id = p.person_id 
where mc.movie_id in (select b.id
                      from (select movie_id 
                            from movie_genres 
                            join genre 
                            on genre_id = id 
                            where name = 'Fantasy' or name = 'Action' 
                            or name = 'Science Fiction' or name = 'Adventure'
                               or name = 'Thriller' or name = 'Horror') as a
                      join (select m.id
                             from movie m
                             join ratings r
                             on m.id = r.movie_id
                             where m.revenue > m.budget
                             group by m.id
                             having avg(r.rating) > 4.0) as b
                      on a.movie_id = b.id)
group by p.person_id
having count(p.person_id) > 1;


-- Names of all the women that play in a movie rated above 4.5
select distinct p.name
from person p 
join movie_cast mc
on p.person_id = mc.person_id
where p.gender = 1 and mc.movie_id in (select m.id
                                          from movie m join ratings r
                                         on r.movie_id = m.id
                                         group by m.id
                                         having avg(r.rating) > 4.5);
                              
                              
---Count people by gender who worked as a crew_member in one of the 50 most popular movies
select count(name), gender
from Person p
where name in(select distinct name
			  from movie m
			  join movie_crew c
			  on m.id = c.movie_id
			  join person p
			  on c.person_id = p.person_id
			  where title in (select title
							  from movie
							  order by popularity desc limit 50))
group by gender;