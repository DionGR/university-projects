-- FIND Violations in movie_cast by checking if there's more than 1 distinct pair of name, gender per person id
-- Returns empty set if no violations.
select person_id
from movie_cast as mcast
group by mcast.person_id
having count(distinct (mcast.name, mcast.gender)) > 1;

-- FIND Violations in movie_crew by checking if there's more than 1 distinct pair of name, gender per person id
-- Returns empty set if no violations
select person_id
from movie_crew
group by person_id
having count(distinct (name, gender)) > 1;


