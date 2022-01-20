package com.iedaas.checklistrequest.dao;

import com.iedaas.checklistrequest.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    @Query(value = "select * from owner o where o.owner_uid= ?1", nativeQuery = true)
    Owner findbyUUID(String uid);
}
