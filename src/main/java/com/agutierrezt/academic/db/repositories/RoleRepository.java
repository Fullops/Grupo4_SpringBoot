package com.agutierrezt.academic.db.repositories;

import com.agutierrezt.academic.db.entities.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Roles, String> {
    Optional<Roles> findOneByName(String name);
}
