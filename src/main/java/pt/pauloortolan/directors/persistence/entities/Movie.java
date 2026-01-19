package pt.pauloortolan.directors.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie", schema = "director_schema")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int tmdbId;
    private String title;
    private LocalDate releaseDate;
    private String posterPath;

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<DirectorMovie> directorMovies;

}
