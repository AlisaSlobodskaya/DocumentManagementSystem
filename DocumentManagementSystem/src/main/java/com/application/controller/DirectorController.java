package com.application.controller;

import com.application.entity.Assignment;
import com.application.entity.enumeration.AssignmentStatus;
import com.application.service.AssignmentService;
import com.application.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/director")
public class DirectorController {
    private final AssignmentService assignmentService;
    private final RoleService roleService;

    @GetMapping("/send_assignmentToHead")
    public String sendAssignmentToSpecPage(){
        return "director/send_assignmentToHead";
    }

    @GetMapping("/send_assignmentToSpec")
    public String sendAssignmentToHeadPage(){
        return "director/send_assignmentToSpec";
    }

    @PostMapping("/send_assignmentToHead")
    public String sendToHead(@RequestParam String description) {
        if (description != null) {
            Assignment assignment = new Assignment();
            assignment.setDescription(description);
            assignment.setSenderRole(roleService.getRoleById(1));
            assignment.setRecipientRole(roleService.getRoleById(3));
            assignment.setStatus(AssignmentStatus.NOT_CONFIRMED);
            assignmentService.save(assignment);
        }
        return "director/director_panel";
    }

    @PostMapping("/send_assignmentToSpec")
    public String sendToSpec(@RequestParam String description) {
        if (description != null) {
            Assignment assignment = new Assignment();
            assignment.setDescription(description);
            assignment.setSenderRole(roleService.getRoleById(1));
            assignment.setRecipientRole(roleService.getRoleById(4));
            assignment.setStatus(AssignmentStatus.NOT_CONFIRMED);
            assignmentService.save(assignment);
        }
        return "director/director_panel";
    }

    @GetMapping("/assignmentCheck")
    public String checkAssignment(Model model) {
        if (assignmentService.getAllDescriptionsForDirector() != null) {
            model.addAttribute("assignments", assignmentService.getAllDescriptionsForDirector());
        } else {
            model.addAttribute("assignments", "No assignments");
        }

        return "director/assignmentCheck";
    }

    @PostMapping("/assignmentCheck")
    public String checkAssignment(@RequestParam int id,
                                  @RequestParam String move,
                                  Model model) {
        moveOnAssignment(move, id);
        model.addAttribute("assignments", assignmentService.getAllDescriptionsForDirector());
        return "director/assignmentCheck";
    }

    @GetMapping("/assignmentCheck/{id}")
    public String assignmentById(@PathVariable int id, Model model) {
        if (assignmentService.findAssignmentById(id).getDocument() == null) {
            model.addAttribute("assignments", assignmentService.findAssignmentById(id));
            return "director/assignmentById";
        } else {
            model.addAttribute("doc", assignmentService.findAssignmentById(id).getDocument().getTitle());
            model.addAttribute("assignments", assignmentService.findAssignmentById(id));
            return "director/assignmentByIdDoc";
        }
    }

    private void moveOnAssignment(String move, int id) {
        switch (move) {
            case "confirm":
                assignmentService.setConfirm(id);
                break;
            case "delete":
                assignmentService.delete(id);
                break;
            case "revision":
                assignmentService.setRevision(id);
                break;
        }
    }
}
