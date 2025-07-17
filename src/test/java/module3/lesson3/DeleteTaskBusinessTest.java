package module3.lesson3;

import com.fasterxml.jackson.databind.ObjectMapper;
import module3.helpers.HttpCode;
import module3.helpers.ToDoHelper;
import module3.lesson2.Task;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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

public class DeleteTaskBusinessTest {
    private final static String URL = "https://todo-app-sky.herokuapp.com/";

    private HttpClient httpClient;

    private ToDoHelper toDoHelper;

    @BeforeEach
    public void setUp(){
        httpClient = HttpClientBuilder.create().build();
        toDoHelper = new ToDoHelper();
    }

    @Test
    public void deleteTaskNotExists() throws IOException {
        // 1. Предусловие Создаем объект BUSJAVA
        int createdTaskId = toDoHelper.createTask();

        HttpDelete httpDelete = new HttpDelete(URL + "/" + createdTaskId);
        httpClient.execute(httpDelete);


        HttpGet httpGet = new HttpGet(URL);
        HttpResponse todoListResponse = httpClient.execute(httpGet);

        String stringBody = EntityUtils.toString(todoListResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        Task[] tasks = objectMapper.readValue(stringBody, Task[].class);
        Arrays.stream(tasks).forEach(System.out::println);

        assertThat(tasks).filteredOn(task1 -> task1.getId() == createdTaskId).isEmpty();
    }
}
