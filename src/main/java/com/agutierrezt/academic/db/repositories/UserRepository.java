package com.agutierrezt.academic.db.repositories;

import com.agutierrezt.academic.db.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    List<Users> findAllByRoleId(@Param("role_id") String roleId);
    Optional<Users> findOneByEmail(@Param("email") String email);
}
