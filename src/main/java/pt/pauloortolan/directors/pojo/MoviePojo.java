package pt.pauloortolan.directors.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoviePojo {

    private UUID id;
    private int tmdbId;
    private String title;
    private LocalDate releaseDate;
    private String posterPath;

}
