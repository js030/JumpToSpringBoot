package com.example.jumptospringboot.repository;

import com.example.jumptospringboot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
