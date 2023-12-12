package com.example.pdfToText.controller;
import com.example.pdfToText.service.HandleConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pdf")
//@RequiredArgsConstructor
public class conversion {
//    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    public conversion(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    @Autowired
    private HandleConversion handleConversion;
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a PDF file to upload.", HttpStatus.BAD_REQUEST);
        }
        String text= handleConversion.processPDF(file);
        return  ResponseEntity.ok(text);
    }
@GetMapping("/search")
    public ResponseEntity<List<String>> searchFiles(@RequestParam("keyword") String keyword){
//        String query = "SELECT file_name FROM pdftotext.template WHERE content LIKE ?";
//        String searchParam = "%" + keyword + "%";
//
//        List<String> list = new ArrayList<>();
//        list = jdbcTemplate.queryForList(query, String.class, searchParam);
    List<String> list= handleConversion.searchFiles(keyword);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        return handleConversion.getFile(fileName);
    }
}







