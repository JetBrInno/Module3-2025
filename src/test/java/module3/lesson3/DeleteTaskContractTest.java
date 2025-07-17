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

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTaskContractTest {
    private final static String URL = "https://todo-app-sky.herokuapp.com/";

    private HttpClient httpClient;

    private ToDoHelper toDoHelper;

    @BeforeEach
    public void setUp() {
        httpClient = HttpClientBuilder.create().build();
        toDoHelper = new ToDoHelper();
    }

    @Test
    public void deleteTaskCode200() throws IOException {
        // 1. Предусловие Создаем объект BUSJAVA
        int createdTaskId = toDoHelper.createTask();

        HttpDelete httpDelete = new HttpDelete(URL + "/" + createdTaskId);
        HttpResponse httpResponse = httpClient.execute(httpDelete);
        int code = httpResponse.getStatusLine().getStatusCode();
        assertThat(code).isEqualTo(HttpCode.OK);
    }

}
