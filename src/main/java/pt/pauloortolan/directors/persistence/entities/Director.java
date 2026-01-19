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
@Table(name = "director", schema = "director_schema")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int tmdbId;

    @OneToMany(mappedBy = "director")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<DirectorMovie> directorMovies;

}
