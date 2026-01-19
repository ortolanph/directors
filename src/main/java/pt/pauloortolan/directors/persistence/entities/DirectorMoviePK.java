package pt.pauloortolan.directors.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DirectorMoviePK implements Serializable {

    @Column
    private UUID directorId;

    @Column
    private UUID movieId;

}
