package com.sparcs10.demo.repository;

import com.sparcs10.demo.entity.FollowRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRelationRepository extends JpaRepository<FollowRelation, String> {
}
