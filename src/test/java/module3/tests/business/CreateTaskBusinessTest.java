package module3.tests.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import module3.helpers.ToDoHelper;
import module3.enitites.Task;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTaskBusinessTest {
    private final static String URL = "https://todo-app-sky.herokuapp.com/";

    private HttpClient httpClient;

    private ToDoHelper toDoHelper;

    private int createdTaskId;

    @BeforeEach
    public void setUp(){
        httpClient = HttpClientBuilder.create().build();
        toDoHelper = new ToDoHelper();
    }

    @AfterEach
    public void tearDown() throws IOException {
        // 4. ПОСТУСЛОВИЕ - Удаление объекта
        toDoHelper.deleteTask(createdTaskId);
    }

    @Test
    public void createTaskCheckExists() throws IOException {
        // 1. Создаем объект BUSJAVA
        String title = "myJavaTask";
        Task myJavaTask = new Task(title, false);
        createdTaskId = toDoHelper.createTask(myJavaTask);

        // 2. Получаем список объектов
        HttpGet httpGet = new HttpGet(URL);
        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();
        String stringBody = EntityUtils.toString(response.getEntity());
        Task[] tasks = objectMapper.readValue(stringBody, Task[].class);

        // 3. Проверяем что BUSJAVA есть в списке
        assertThat(tasks).anyMatch((task) -> task.getTitle().equals(title));
    }

}
