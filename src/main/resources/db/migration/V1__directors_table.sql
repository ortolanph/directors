create table director_schema.director (
    id uuid primary key,
    name varchar(255) not null,
    tmdb_id integer not null
);

create unique index idx_director_tmdb_id on director_schema.director (tmdb_id);

comment on table director_schema.director is 'Table storing director information from TMDB';
comment on column director_schema.director.tmdb_id is 'The TMDB ID of the director';
comment on column director_schema.director.name is 'The name of the director';
