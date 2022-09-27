package com.example.demoSpringSecurityJwt.Dao;


import com.example.demoSpringSecurityJwt.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {
    @Query(
            value = "select * from user u where u.user_first_name= :variable",
            nativeQuery = true
    )
    User showSpecificData(@Param("variable") String userFirstName);
}
