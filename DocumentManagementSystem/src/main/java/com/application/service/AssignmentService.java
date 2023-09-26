package com.application.service;

import com.application.entity.Assignment;
import com.application.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment findAssignmentById(int id) {
        return assignmentRepository.getById(id);
    }

    public List<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> getAllDescriptionsForHead() {
        return assignmentRepository.getAllDescriptionsForHead();
    }

    public List<Assignment> getAllDescriptionsForDirector() {
        return assignmentRepository.getAllDescriptionsForDirector();
    }

    public List<Assignment> getAllDescriptionsForSpec() {
        return assignmentRepository.getAllDescriptionsForSpec();
    }

    public List<String> getAllDescriptions() {
        return assignmentRepository.getAllDescriptions();
    }

    public Assignment save(Assignment assignment) {   //переименовать
        return assignmentRepository.save(assignment);
    }

    public boolean delete(int id) {
        if (assignmentRepository.findById(id).isPresent()) {
            assignmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void setDocumentId(int documentId, int assignmentId) {
        assignmentRepository.setDocumentId(documentId, assignmentId);
    }

    public void setRevision(int id) {
        assignmentRepository.setRevision(id);
    }

    public void setConfirm(int id) {
        assignmentRepository.setConfirm(id);
    }

    public void confirmNext(int id) {
        assignmentRepository.confirmNext(id);
    }

    public int checkCreator(int id) {
        return assignmentRepository.checkSender(id);
    }
}
