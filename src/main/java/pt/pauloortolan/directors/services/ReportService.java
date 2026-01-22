package pt.pauloortolan.directors.services;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import pt.pauloortolan.directors.pojo.DirectorResume;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.*;
import java.time.format.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;

    @Value("${directors.template.file}")
    private String templateFile;

    public byte[] generateReport(DirectorResume directorResume) {
        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("directorName", directorResume.getName());
        dataModel.put("movies", directorResume.getMovies());
        dataModel.put("generatedOn", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        try {
            Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();

            Template template = configuration.getTemplate(templateFile);

            StringWriter stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);

            String reportText = stringWriter.toString();

            ByteArrayOutputStream target = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();

            HtmlConverter.convertToPdf(reportText, target, converterProperties);

            return target.toByteArray();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileName(String directorName) {
        String finalDirectorName = directorName
                .toLowerCase()
                .replace(".", "")
                .replace(" ", "_");

        return finalDirectorName + "_binge_watch.pdf";
    }

}
