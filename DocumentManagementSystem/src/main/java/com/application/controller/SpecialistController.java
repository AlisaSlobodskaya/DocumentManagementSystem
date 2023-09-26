package com.application.controller;

import com.application.entity.Document;
import com.application.service.AssignmentService;
import com.application.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequiredArgsConstructor
@RequestMapping("/specialist")
public class SpecialistController {
    private final AssignmentService assignmentService;
    private final DocumentService documentService;

    @GetMapping("/assignmentCheck")
    public String checkAssignment(Model model) {
        if (assignmentService.getAllDescriptionsForHead() != null) {
            model.addAttribute("assignments", assignmentService.getAllDescriptionsForSpec());
        } else {
            model.addAttribute("assignments", "No assignments");
        }
        return "specialist/assignmentCheck";
    }


    @PostMapping("/assignmentCheck")
    public String checkAssignment(@RequestParam int id,
                                  @RequestParam String move,
                                  Model model) {
        switch (move) {
            case "confirm":
                assignmentService.confirmNext(id);
                break;
        }
        model.addAttribute("assignments", assignmentService.getAllDescriptionsForSpec());
        return "specialist/assignmentCheck";
    }

    @GetMapping("/assignmentCheck/{id}")
    public String assignmentById(@PathVariable int id, Model model) {
        if (assignmentService.findAssignmentById(id).getDocument() == null) {
            model.addAttribute("assignments", assignmentService.findAssignmentById(id));
            return "specialist/assignmentById";
        } else {
            model.addAttribute("doc", assignmentService.findAssignmentById(id).getDocument().getTitle());
            model.addAttribute("assignments", assignmentService.findAssignmentById(id));
            return "specialist/assignmentByIdDoc";
        }
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("id") int id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path path = Paths.get("C:\\docs\\" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            documentService.saveDocument(new Document(fileName, path.toString()));
            assignmentService.setDocumentId(documentService.getDocumentByTitle(fileName).getId(), id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/specialist/assignmentCheck";
    }
}
