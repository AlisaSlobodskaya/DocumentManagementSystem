package com.application.constant;

public final class UserSql {
    public static final String USER_UPDATE = "update users u set u.username = ?1," +
            " u.email = ?2, u.password = ?3, u.roleId = ?4 where u.id = ?5";

    private UserSql() {
    }
}
