package com.example.testapistackexchange.api;

import com.example.testapistackexchange.model.Question;

import java.io.IOException;
import java.util.List;

public interface IApiService {
    List<Question> getQuestions(String intitle) throws IOException;
}
