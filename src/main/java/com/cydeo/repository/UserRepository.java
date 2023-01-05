package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("Select u from User u where u.isDeleted=false and u.company.companyStatus='ACTIVE'" +
            "and u.company.isDeleted=false order by u.company.title, u.role.description ")
    List<User> findAllNotDeletedWithActiveCompanyOrderByCompanyAndRole();

    @Query("Select u from User u where u.isDeleted=false and u.id=?1")
    Optional<User> findNotDeletedById(Long id);

}

