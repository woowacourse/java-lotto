package util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomNumberMockTest {

    @DisplayName("중복된 숫자는 피해서 생성된다.")
    @Test
    void duplicateMockTest() {
        // when
        List<Integer> randomNumbers = RandomNumberGenerator.getRandomNumbers(new TestLottoGenerator());
        // then
        Assertions.assertThat(randomNumbers).contains(1, 2, 3, 4, 5, 6);
    }

    class TestLottoGenerator implements RandomGenerator {
        private static final List<Integer> numberList = List.of(1, 1, 2, 3, 5, 5, 6, 4);
        int index = 0;

        @Override
        public int generate() {
            return numberList.get(index++);
        }
    }
}
