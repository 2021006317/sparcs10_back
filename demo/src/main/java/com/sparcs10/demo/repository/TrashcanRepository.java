package com.sparcs10.demo.repository;

import com.sparcs10.demo.entity.Trashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashcanRepository extends JpaRepository<Trashcan, String> {
    Trashcan findByAddress(String address);
}
