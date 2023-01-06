package com.cydeo.repository;

import com.cydeo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> getCategoryById (Long id);

    Boolean existsByDescriptionAndCompany_Title(String description, String companyTitle);

    @Query("select c from Category c where c.isDeleted=false order by c.description")
    List<Category> findAllNotDeleted();



}
