package com.example.jumptospringboot;

import static org.junit.jupiter.api.Assertions.*;

import com.example.jumptospringboot.entity.Answer;
import com.example.jumptospringboot.entity.Question;
import com.example.jumptospringboot.repository.AnswerRepository;
import com.example.jumptospringboot.repository.QuestionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsStatic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JumptospringbootApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("질문 저장")
    void saveQuestionTest() {
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
    @DisplayName("저장된 질문의 개수 맞는 지 확인하는 테스트")
    void questionFindAll() {
        List<Question> all = questionRepository.findAll();
        assertEquals(4, all.size());
    }

    @Test
    @DisplayName("아이디로 질문 찾기 테스트")
    void getQuestionById(){
        Optional<Question> oq = questionRepository.findById(1);
        if (oq.isPresent()){
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요", q.getSubject());
        }
    }

    @Test
    @DisplayName("주제로 질문 찾기 테스트")
    void getQuestionBySubject(){
        Question q = questionRepository.findBySubject("sbb가 무엇인가요");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("주제와 내용으로 질문 찾기")
    void findQuestionBySubjectAndContent(){
        Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가요", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("입력한 값과 비슷한 주제로 질문 찾기")
    void findQuestionBySubjectLike(){
        List<Question> qList = questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요", q.getSubject());
    }

    @Test
    @DisplayName("질문 아이디로 조회한 후 있을 시 가져와서 값 수정 후 저장하는 테스트")
    void getByIdAndEdit(){
        Optional<Question> oq = questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        questionRepository.save(q);
    }

    @Test
    @DisplayName("질문 삭제 테스트")
    void deleteQuestion(){
        assertEquals(4, questionRepository.count());
        Optional<Question> oq = questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        questionRepository.delete(q);
        assertEquals(3, this.questionRepository.count());
    }

    @Test
    void testJpa(){
        Optional<Question> oq = questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        answerRepository.save(a);
    }

    @Test
    void contextLoads() {
    }

}
