-- 4. Create tables Person,Actor,Crew_member and add constraints
CREATE TABLE Person (
   person_id int,
   name varchar(60),
   gender int,
   primary key(person_id)
);

CREATE TABLE Actor (
   person_id int,
   primary key (person_id),
   foreign key (person_id) references Person(person_id)
);

CREATE TABLE Crew_Member (
   person_id int,
   primary key (person_id),
   foreign key (person_id) references Person(person_id)
);

-- 5. Create query that checks records that violate functional dependencies person_id -> name and person_id->gender
-- We can see from this query that Peter Maiota has two different genders on the two tables despite having the same person_id
SELECT *
FROM movie_cast m 
JOIN movie_crew c
ON m.person_id = c.person_id
WHERE m.name <> c.name OR m.gender <> c.gender;

UPDATE movie_crew
SET gender = 2
WHERE person_id = 1785844 and gender <> 2;

-- 6. Create query to insert data into Actor, Crew_Member and Person

INSERT INTO Person 
 (SELECT DISTINCT person_id, name, gender
  FROM movie_crew 
  UNION
  SELECT DISTINCT person_id, name, gender
  FROM movie_cast);
  
  
INSERT INTO Actor
 (SELECT DISTINCT person_id
  FROM movie_cast);

INSERT INTO Crew_Member
 (SELECT DISTINCT person_id
  FROM movie_crew);

-- 7. Add foreign keys to Movie_crew and Movie_cast and Drop Collumns

ALTER TABLE Movie_Crew
  ADD foreign key (person_id) REFERENCES Crew_Member(person_id),
  DROP COLUMN name,
  DROP COLUMN gender;

ALTER TABLE Movie_Cast
  ADD foreign key (person_id) REFERENCES Actor(person_id),
  DROP COLUMN name, 
  DROP COLUMN gender;
