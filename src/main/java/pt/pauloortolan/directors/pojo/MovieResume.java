package pt.pauloortolan.directors.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResume {

    private String title;
    private String poster;
    private String releaseDate;

}
