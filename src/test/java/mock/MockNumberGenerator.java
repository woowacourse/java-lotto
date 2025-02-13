package mock;

import generator.NumberGenerator;
import java.util.List;
import java.util.stream.IntStream;
import model.Lotto;

public class MockNumberGenerator extends NumberGenerator {

    public MockNumberGenerator() {
        super(Lotto.LOTTO_NUMBER_START_INCLUSIVE, Lotto.LOTTO_NUMBER_END_INCLUSIVE);
    }

    @Override
    public List<Integer> pickNumbersInRange(final int count) {
        return IntStream.rangeClosed(1, count)
            .boxed()
            .toList();
    }
}
