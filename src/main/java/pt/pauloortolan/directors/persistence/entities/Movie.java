package pt.pauloortolan.directors.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie", schema = "director_schema")
public class Movie {

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<DirectorMovie> directorMovies;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int tmdbId;
    private String title;
    private LocalDate releaseDate;
    private String posterPath;

}
