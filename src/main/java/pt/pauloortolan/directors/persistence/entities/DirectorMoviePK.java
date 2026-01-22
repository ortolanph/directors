package pt.pauloortolan.directors.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

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
