package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageConverterService {

    @Value("${directors.surrogate.posters}")
    private List<String> defaultPosters;

    private static final Random RANDOM = new Random();

    public String convertImageToBase64(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);

        InputStream inputStream = url.openStream();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();

        byte[] imageBytes = outputStream.toByteArray();
        String base64String = Base64.getEncoder().encodeToString(imageBytes);
        String mimeType = getMimeType(imageUrl);

        return "data:" + mimeType + ";base64," + base64String;
    }

    public String convertLocalImageToBase64(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] imageBytes = Files.readAllBytes(path);

        String base64String = Base64.getEncoder().encodeToString(imageBytes);

        String mimeType = getMimeType(filePath);

        return "data:" + mimeType + ";base64," + base64String;
    }

    public String getRandomSurrogatePoster() {
        return defaultPosters.get(RANDOM.nextInt(defaultPosters.size()));
    }

    protected String getMimeType(String url) {
        String extension = url.toLowerCase().substring(url.lastIndexOf(".") + 1);

        return switch (extension) {
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            case "webp" -> "image/webp";
            case "svg" -> "image/svg+xml";
            default -> "image/jpeg";
        };
    }

}
