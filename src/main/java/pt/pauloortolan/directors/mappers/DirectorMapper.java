package pt.pauloortolan.directors.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.pojo.DirectorImportPOJO;
import pt.pauloortolan.directors.pojo.DirectorPojo;

@Component
@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorPojo fromEntity(Director director);

    @Mapping(target = "directorMovies", ignore = true)
    Director fromPojo(DirectorPojo directorPojo);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "director", target = "name")
    DirectorPojo fromImport(DirectorImportPOJO director);

}
