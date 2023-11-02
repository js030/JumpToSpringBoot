package com.example.jumptospringboot.controller;

import com.example.jumptospringboot.entity.Question;
import com.example.jumptospringboot.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    public String list(Model model){
        List<Question> questionList = questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
