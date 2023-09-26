package com.application.constant;

public final class AssignmentSql {
    public static final String GET_ALL_DESCRIPTIONS_FOR_HEAD = "select * from assignment" +
            " where status='NOT_CONFIRMED' AND recipientRoleId=3";
    public static final String GET_ALL_DESCRIPTIONS_FOR_SPECIALIST = "select * from assignment" +
            " where status='NOT_CONFIRMED' AND recipientRoleId=4";
    public static final String GET_ALL_DESCRIPTIONS = "select description from assignment";
    public static final String SET_CONFIRM = "UPDATE assignment SET status = 'CONFIRMED' WHERE id = ?1";
    public static final String SET_REVISION = "UPDATE assignment SET recipientRoleId " +
            "= (recipientRoleId+1) WHERE id = ?1";
    public static final String CONFIRM_NEXT = "UPDATE assignment SET recipientRoleId " +
            "= (recipientRoleId-1) WHERE id = ?1";
    public static final String GET_ALL_DESCRIPTIONS_FOR_DIRECTOR = "select * from assignment" +
            " where status='NOT_CONFIRMED' and recipientRoleId = 2";
    public static final String CHECK_SENDER = "select senderRoleId from assignment WHERE id = ?1";
    public static final String SET_DOCUMENT_ID = "UPDATE assignment SET documentId = ?1 WHERE id = ?2";

    private AssignmentSql() {
    }
}
