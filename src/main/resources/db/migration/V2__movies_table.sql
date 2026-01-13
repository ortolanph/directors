create table director_schema.movie (
    id uuid primary key,
    director_id uuid not null references director_schema.director(id),
    tmdb_id integer not null,
    title varchar(255) not null,
    release_date date,
    poster_path varchar(255),
);

create unique index idx_movie_tmdb_id on director_schema.movie (tmdb_id);

comment on table director_schema.movie is 'Table to save movies directed by directors';
comment on column director_schema.movie.tmdb_id is 'The Unique Movie Database (TMDb) identifier for the movie';
comment on column director_schema.movie.title is 'The movie title translated to english';
comment on column director_schema.movie.release_date is 'The release date of the movie';
comment on column director_schema.movie.poster_path is 'The poster path';
