package pt.pauloortolan.directors.persistence.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "director_movie", schema = "director_schema")
public class DirectorMovie {

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
    @EmbeddedId
    private DirectorMoviePK id;
}
