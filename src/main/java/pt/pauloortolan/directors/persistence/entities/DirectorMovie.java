package pt.pauloortolan.directors.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "director_movie", schema = "director_schema")
public class DirectorMovie {

    @EmbeddedId
    private DirectorMoviePK id;

    @ManyToOne
    @MapsId("directorId")
    @JoinColumn(name = "director_id")
    Director director;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    Movie movie;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
