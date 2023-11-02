package com.example.jumptospringboot;

import com.example.jumptospringboot.entity.Question;
import com.example.jumptospringboot.repository.QuestionRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JumptospringbootApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("질문 저장")
    void saveQuestionTest(){
        Question q1 = new Question();
        q1.setSubject("DI가 무엇인가요?");
        q1.setContent("DI에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("IOC는 무엇인가요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }

    @Test
    void contextLoads() {
    }

}
