package com.application.service;

import com.application.entity.Document;
import com.application.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document getDocumentByTitle(String title) {
        return documentRepository.getByTitle(title);
    }
}
