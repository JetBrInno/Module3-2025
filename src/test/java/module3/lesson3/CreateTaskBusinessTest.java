package module3.lesson3;

import com.fasterxml.jackson.databind.ObjectMapper;
import module3.helpers.HttpCode;
import module3.lesson2.Task;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTaskBusinessTest {
    private final static String URL = "https://todo-app-sky.herokuapp.com/";

    private HttpClient httpClient;

    @BeforeEach
    public void setUp(){
        httpClient = HttpClientBuilder.create().build();}

    @Test
    public void createTaskCheckExists() throws IOException {
        // 1. Создаем объект BUSJAVA
        HttpPost request = new HttpPost(URL);
        String myContent = """
                {
                    "title": "BUSJAVA",
                    "completed": false
                }""";
        StringEntity stringEntity = new StringEntity(myContent, ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);
        httpClient.execute(request);

        // 2. Получаем список объектов
        HttpGet httpGet = new HttpGet(URL);
        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();
        String stringBody = EntityUtils.toString(response.getEntity());
        Task[] tasks = objectMapper.readValue(stringBody, Task[].class);

        // 3. Проверяем что BUSJAVA есть в списке
        assertThat(tasks).anyMatch((task) -> task.getTitle().equals("BUSJAVA"));


        // 4. ПОСТУСЛОВИЕ - Удаление объекта

    }

}
