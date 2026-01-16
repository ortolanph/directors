package pt.pauloortolan.directors.pojo;

import pt.pauloortolan.directors.integration.pojo.Crew;

import java.util.List;

public record Credits(DirectorPojo director, List<Crew> movieCredits) {
}
