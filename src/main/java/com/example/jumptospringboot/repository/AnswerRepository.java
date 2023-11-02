package com.example.jumptospringboot.repository;

import com.example.jumptospringboot.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
