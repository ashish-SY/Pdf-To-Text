package com.example.pdfToText.model;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//REPLACE IT @Entity
@Document(collection = "templates")
public class Template {
//    @Id
//    private String id;
//    @Indexed(unique=true)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private String fileName;
    private String fileType;

//    @Lob
//    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
//@Column(columnDefinition="LONGTEXT")
    private String content;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
