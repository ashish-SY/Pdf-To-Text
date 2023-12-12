package com.example.pdfToText.service;
import com.example.pdfToText.Repositories.FileRepository;
import com.example.pdfToText.model.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
//@RequiredArgsConstructor
public class HandleConversion {
@Autowired
    private FileRepository fileRepository;
    Template template = new Template();
public String processPDF(MultipartFile file){
    String text;

    try (final PDDocument document = PDDocument.load(file.getInputStream())) {
        final PDFTextStripper pdfStripper = new PDFTextStripper();
        text = pdfStripper.getText(document);
        template.setContent(text);
        template.setFileName(file.getOriginalFilename());
        template.setFileType(file.getContentType());
        template.setData(file.getBytes());
        fileRepository.save(template);

    } catch (final Exception ex) {
        log.error("Error parsing PDF", ex);
        text = "Error parsing PDF";
    }
    return text;
}



    public ResponseEntity<?> getFile(String fileName) {
    try{
       Template template1  = new Template();
              template1 = fileRepository.findByfileName(fileName);
        byte[] pdfData = template1.getData();
        String filePath = "D:\\download demo\\"+ template1.getFileName();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(pdfData); // Write the byte array data to the file
            System.out.println("PDF file saved successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving the PDF file: " + e.getMessage());
        }
    }
    catch(Exception e){
        return ResponseEntity.ok("MOYE MOYE -> Something went wrong  "+e.getMessage());
    }
    return ResponseEntity.ok("Download Successfully");
    }

    public List<String> searchFiles(String keyword){
        List<Template> templates = fileRepository.findByContentRegex(".*" + keyword + ".*");
        return templates.stream()
                .map(Template::getFileName)
                .collect(Collectors.toList());
    }

}


