package mock;

import generator.NumberGenerator;
import java.util.List;
import java.util.stream.IntStream;

public class MockNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generateNumbers(final int count) {
        return IntStream.rangeClosed(1, count)
                .boxed()
                .toList();
    }
}
