package com.example.testapistackexchange.api;

import com.example.testapistackexchange.model.Question;
import com.example.testapistackexchange.model.Questions;
import com.google.gson.*;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Component
public class ApiService implements IApiService{

    public List<Question> getQuestions(String intitle) throws IOException {
        String urlStr = "http://api.stackexchange.com/2.2/questions?key=lBJbbaF4E1MMmug3IUiZPw((&order=desc&sort=activity&site=stackoverflow&filter=withbody&intitle=" + intitle;
        {
                String json = Jsoup.connect(urlStr).ignoreContentType(true).execute().body();
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Date.class, ser)
                        .registerTypeAdapter(Date.class, deser).create();
                Questions questions = gson.fromJson(json, Questions.class);
                return questions.getQuestions();
        }
    }

    JsonSerializer<Date> ser = (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(src.getTime());
    JsonDeserializer<Date> deser = (json, typeOfT, context) -> json == null ? null : new Date(json.getAsLong());
}
