/* find the titles for the movies made in 2014
   and where the critics score is greater than the audience score */
SELECT TITLE FROM MOVIE WHERE YEAR = '2014' 
AND CRITICS_SCORE > AUDIENCE_SCORE;

/* for each mpaa rating, find how many movies there are
   order the return table by the count of movies */
SELECT COUNT(ID), MPAA_RATING FROM MOVIE GROUP BY MPAA_RATING ORDER BY COUNT(ID) DESC;

/* find the Harry Potter movie with the highest critics score */
SELECT TITLE, CRITICS_SCORE FROM MOVIE 
WHERE CRITICS_SCORE = (SELECT MAX(CRITICS_SCORE) FROM MOVIE WHERE TITLE LIKE '%Harry Potter%') 
AND TITLE LIKE '%Harry Potter%';

/* find each movie and its critics score if Tom Hanks was in the movie */
SELECT DISTINCT TITLE, CRITICS_SCORE FROM MOVIE INNER JOIN CHARACTER ON MOVIE.ID = CHARACTER.MOVIE_ID 
INNER JOIN ACTOR ON CHARACTER.ACTOR_ID = ACTOR.ID WHERE NAME IN ('Tom Hanks');

/* find all the actors who have played in the same movie with Tom Hanks */
SELECT DISTINCT NAME, TITLE FROM MOVIE INNER JOIN CHARACTER ON MOVIE.ID = CHARACTER.MOVIE_ID 
INNER JOIN ACTOR ON CHARACTER.ACTOR_ID = ACTOR.ID 
WHERE MOVIE.ID IN (SELECT MOVIE_ID FROM CHARACTER 
WHERE ACTOR_ID = (SELECT ID FROM ACTOR WHERE NAME = 'Tom Hanks')) 
AND NAME <> 'Tom Hanks' ORDER BY TITLE ASC;