package iteratorExample;

import org.instancio.Instancio;

import java.util.List;

public class NoIteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = getDataFromDbMock();
        for (int i = 0; i < list.size(); i++) {
            if (i == 20) break;
            System.out.println(list.get(i));
        }
    }

    private static List<Integer> getDataFromDbMock() {
        return Instancio.createList(Integer.class);
    }
}
