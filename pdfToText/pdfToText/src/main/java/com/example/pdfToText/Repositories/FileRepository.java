package com.example.pdfToText.Repositories;

import com.example.pdfToText.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface FileRepository extends JpaRepository<Template, Long>{
//    Template findByfileName(String fileName);
//}

public interface FileRepository extends MongoRepository<Template, String> {
        @Query("{'content': {$regex: ?0, $options: 'i'}}")
        List<Template> findByContentRegex(String keyword);

        Template findByfileName(String name);

}