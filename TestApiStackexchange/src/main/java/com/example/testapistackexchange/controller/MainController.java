package com.example.testapistackexchange.controller;

import com.example.testapistackexchange.api.ApiService;
import com.example.testapistackexchange.api.IApiService;
import com.example.testapistackexchange.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
@Controller
public class MainController {

    @Autowired
    private IApiService service;

    @GetMapping({"","search"})
    public String index(Model model, @ModelAttribute(value="intitle") String intitle) throws IOException {

        if (intitle.equals("")) {
            model.addAttribute("errors", "please give me your word");
            return "index";
        }

        String errors = null;
      //  IApiService conn = new ApiService();
        List<Question> questions = service.getQuestions(intitle);
        model.addAttribute("questions", questions);
        model.addAttribute("intitle", intitle);
        model.addAttribute("errors", errors);
        return "index";
    }
}

