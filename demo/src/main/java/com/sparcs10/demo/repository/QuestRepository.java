package com.sparcs10.demo.repository;

import com.sparcs10.demo.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {
    List<Quest> findAllByType(String type);
    Quest findFirstByType(String type);
    Quest findByContent(String content);
}
