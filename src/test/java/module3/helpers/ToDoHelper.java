package module3.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import module3.lesson2.Task;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ToDoHelper {

    private final static String URL = "https://todo-app-sky.herokuapp.com/";

    private final HttpClient httpClient;

    public ToDoHelper() {
        this.httpClient = HttpClientBuilder.create().build();
    }

    public int createTask() throws IOException {
        HttpPost request = new HttpPost(URL);
        String myContent = """
                {
                    "title": "DELETEJAVA",
                    "completed": false
                }""";
        StringEntity stringEntity = new StringEntity(myContent, ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(request);

        ObjectMapper objectMapper = new ObjectMapper();
        String stringBody = EntityUtils.toString(response.getEntity());
        Task task = objectMapper.readValue(stringBody, Task.class);
        return task.getId();
    }
}
