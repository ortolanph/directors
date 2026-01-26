package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageConverterService {

    public String convertImageToBase64(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);

        // Open connection and get input stream
        InputStream inputStream = url.openStream();

        // Read image data into byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Close streams
        inputStream.close();
        outputStream.close();

        // Convert to byte array
        byte[] imageBytes = outputStream.toByteArray();

        // Encode to Base64
        String base64String = Base64.getEncoder().encodeToString(imageBytes);

        // Determine MIME type from URL (basic detection)
        String mimeType = getMimeType(imageUrl);

        // Return as data URI
        return "data:" + mimeType + ";base64," + base64String;
    }

    public String convertLocalImageToBase64(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] imageBytes = Files.readAllBytes(path);

        // Encode to Base64
        String base64String = Base64.getEncoder().encodeToString(imageBytes);

        // Determine MIME type from file extension
        String mimeType = getMimeType(filePath);

        // Return as data URI
        return "data:" + mimeType + ";base64," + base64String;
    }

    private static String getMimeType(String url) {
        String lowerUrl = url.toLowerCase();
        if (lowerUrl.endsWith(".png")) return "image/png";
        if (lowerUrl.endsWith(".jpg") || lowerUrl.endsWith(".jpeg")) return "image/jpeg";
        if (lowerUrl.endsWith(".gif")) return "image/gif";
        if (lowerUrl.endsWith(".webp")) return "image/webp";
        if (lowerUrl.endsWith(".svg")) return "image/svg+xml";
        return "image/jpeg"; // default
    }

}
