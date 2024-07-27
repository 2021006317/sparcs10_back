package com.sparcs10.demo.repository;

import com.sparcs10.demo.entity.Trashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrashcanRepository extends JpaRepository<Trashcan, String> {
    Trashcan findByAddress(String address);

    List<Trashcan> findByAddressContaining(String city);
    List<Trashcan> findByAddressContaining(String city, String district);
}
