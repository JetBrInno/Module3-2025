package iteratorExample;

import org.instancio.Instancio;

import java.nio.file.Path;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        MyIterator myIterator = new MyIterator(getDataFromDbMock());

        while (myIterator.hasNext()) {
            System.out.println(myIterator.next());
        }
    }

    private static List<Integer> getDataFromDbMock() {
        return Instancio.createList(Integer.class);
    }
}
