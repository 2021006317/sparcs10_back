package com.sparcs10.demo.repository;

import com.sparcs10.demo.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForestRepository extends JpaRepository<Tree, String> {

}
