package pt.pauloortolan.directors.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ImageConverterServiceTest {

    private final ImageConverterService imageConverterService = new ImageConverterService();

    @Test
    @DisplayName("Should get mimetype PNG")
    void shouldGetMimetypePNG() {
        // Given
        String expected = "image/png";
        String fileName = "image.png";

        // When
        String actual = imageConverterService.getMimeType(fileName);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should get mimetype GIF")
    void shouldGetMimetypeGIF() {
        // Given
        String expected = "image/gif";
        String fileName = "image.gif";

        // When
        String actual = imageConverterService.getMimeType(fileName);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should get mimetype WEBP")
    void shouldGetMimetypeWEBP() {
        // Given
        String expected = "image/webp";
        String fileName = "image.webp";

        // When
        String actual = imageConverterService.getMimeType(fileName);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should get mimetype SVG")
    void shouldGetMimetypeSVG() {
        // Given
        String expected = "image/svg+xml";
        String fileName = "image.svg";

        // When
        String actual = imageConverterService.getMimeType(fileName);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should get mimetype JPEG")
    void shouldGetMimetypeJPEG() {
        // Given
        String expected = "image/jpeg";
        String fileName = "image.jpg";

        // When
        String actual = imageConverterService.getMimeType(fileName);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

}
