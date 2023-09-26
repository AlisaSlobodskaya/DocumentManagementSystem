package com.application.repository;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import static com.application.constant.UserSql.USER_UPDATE;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from users where username = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = USER_UPDATE, nativeQuery = true)
    boolean updateUser(String username,
                       String email,
                       String password,
                       int roleId,
                       int id);

//    @Transactional
//    @Modifying
//    @Query(USER_DELETE)
//    boolean deleteUser(int id);
}
