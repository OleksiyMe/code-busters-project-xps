package com.cydeo.repository;

import com.cydeo.entity.ClientVendor;
import com.cydeo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {

    Optional<ClientVendor> findById(Long id);

    @Query("SELECT c from ClientVendor c order by c.clientVendorType, c.clientVendorName")
    List<ClientVendor> listSortedClientVendor();

}
