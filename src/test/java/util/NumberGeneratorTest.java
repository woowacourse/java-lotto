package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberGeneratorTest {
    private final NumberPickStrategy defaultNumberPickStrategy = new RandomNumberPickStrategy();

    @DisplayName("주어진 개수만큼 숫자를 생성해 리스트에 저장한다.")
    @Test
    void createListWithNumbers() {
        int numberCount = 10;
        List<Integer> numbers = NumberGenerator.pickUniqueNumbersInRange(1, 10, numberCount, defaultNumberPickStrategy);
        assertThat(numbers.size()).isEqualTo(numberCount);
    }

    @DisplayName("""
            중복되지 않는 숫자를 생성한다.
            중복된 숫자를 생성할 경우, 저장하지 않고 중복되지 않는 수가 나올 때까지 다시 생성한다.
            """)
    @Test
    void createListWithUnduplicatedNumbers() {
        List<Integer> numbers = NumberGenerator.pickUniqueNumbersInRange(-1, -6, 3, new NumberPickStrategy() {
            private int methodCallCounter = 0;
            private List<Integer> pickingNumbers = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 4));

            @Override
            public int pickInRange(int min, int max) {
                return pickingNumbers.get(methodCallCounter++);
            }
        });

        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(2);
        assertThat(numbers.get(2)).isEqualTo(3);
    }


}
