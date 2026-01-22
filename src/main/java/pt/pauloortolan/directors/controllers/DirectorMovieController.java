package pt.pauloortolan.directors.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.pauloortolan.directors.pojo.DirectorResume;
import pt.pauloortolan.directors.services.DirectorMovieService;
import pt.pauloortolan.directors.services.ReportService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/composer")
@RequiredArgsConstructor
public class DirectorMovieController {

    private final DirectorMovieService directorMovieService;

    private final ReportService reportService;

    @Value("${directors.template.file}")
    private String template;

    @PostMapping(value = "/{id}", produces = "application/pdf")
    public ResponseEntity<byte[]> compose(@PathVariable("id") int directorId, @RequestParam("movies[]") List<Integer> movies) {
        DirectorResume directorResume = directorMovieService.generateReport(directorId, movies);
        byte[] documentData = reportService.generateReport(directorResume);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportService.getFileName(directorResume.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(documentData);
    }
}
