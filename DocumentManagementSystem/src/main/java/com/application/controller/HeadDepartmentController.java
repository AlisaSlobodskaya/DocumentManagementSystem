package com.application.controller;

import com.application.entity.Assignment;
import com.application.entity.Document;
import com.application.entity.enumeration.AssignmentStatus;
import com.application.service.AssignmentService;
import com.application.service.DocumentService;
import com.application.service.RoleService;
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
@RequestMapping("/head")
public class HeadDepartmentController {
    private final AssignmentService assignmentService;
    private final RoleService roleService;
    private final DocumentService documentService;

    @GetMapping("/assignmentCheck")
    public String checkAssignment(Model model) {
        if (assignmentService.getAllDescriptionsForHead() != null) {
            model.addAttribute("assignments", assignmentService.getAllDescriptionsForHead());
        } else {
            model.addAttribute("assignments", "No assignments");
        }
        return "head/assignmentCheck";
    }

    @PostMapping("/send_assignment")
    public String sendToSpec(@RequestParam String description) {
        if (description != null) {
            Assignment assignment = new Assignment();
            assignment.setDescription(description);
            assignment.setSenderRole(roleService.getRoleById(3));
            assignment.setRecipientRole(roleService.getRoleById(4));
            assignment.setStatus(AssignmentStatus.NOT_CONFIRMED);
            assignmentService.save(assignment);
        }
        return "head/head_panel";
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

    @PostMapping("/assignmentCheck")
    public String checkAssignment(@RequestParam int id,
                                  @RequestParam String move,
                                  Model model) {
        moveOnAssignment(move, id);
        model.addAttribute("assignments", assignmentService.getAllDescriptionsForHead());
        return "head/assignmentCheck";
    }

    @GetMapping("/assignmentCheck/{id}")
    public String assignmentById(@PathVariable int id, Model model) {
        if (assignmentService.findAssignmentById(id).getDocument() == null) {
            model.addAttribute("assignments", assignmentService.findAssignmentById(id));
            return "head/assignmentById";
        } else {
            model.addAttribute("doc", assignmentService.findAssignmentById(id).getDocument().getTitle());
            model.addAttribute("assignments", assignmentService.findAssignmentById(id));
            return "head/assignmentByIdDoc";
        }
    }

    private void moveOnAssignment(String move, int id) {
        switch (move) {
            case "confirm":
                assignmentService.confirmNext(id);
                break;
            case "delete":
                if (assignmentService.checkCreator(id) != 2) {
                    assignmentService.delete(id);
                }
                break;
            case "revision":
                assignmentService.setRevision(id);
                break;
        }
    }
}
