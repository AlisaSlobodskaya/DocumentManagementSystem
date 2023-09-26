package com.application.repository;

import com.application.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.application.constant.AssignmentSql.*;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    @Query(value = GET_ALL_DESCRIPTIONS_FOR_HEAD, nativeQuery = true)
    List<Assignment> getAllDescriptionsForHead();

    @Query(value = GET_ALL_DESCRIPTIONS_FOR_SPECIALIST, nativeQuery = true)
    List<Assignment> getAllDescriptionsForSpec();

    @Query(value = GET_ALL_DESCRIPTIONS, nativeQuery = true)
    List<String> getAllDescriptions();

    @Modifying
    @Transactional
    @Query(value = SET_DOCUMENT_ID, nativeQuery = true)
    void setDocumentId(int documentId, int assignmentId);

    @Modifying
    @Transactional
    @Query(value = SET_CONFIRM, nativeQuery = true)
    void setConfirm(int id);

    @Modifying
    @Transactional
    @Query(value = SET_REVISION, nativeQuery = true)
    void setRevision(int id);

    @Modifying
    @Transactional
    @Query(value = CONFIRM_NEXT, nativeQuery = true)
    void confirmNext(int id);

    @Query(value = GET_ALL_DESCRIPTIONS_FOR_DIRECTOR, nativeQuery = true)
    List<Assignment> getAllDescriptionsForDirector();

    @Query(value = CHECK_SENDER, nativeQuery = true)
    int checkSender(int id);
}
