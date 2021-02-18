package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.MAXIMUM_NUMBER;
import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.MINIMUM_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RandomNumbersGeneratorTest {
    List<Integer> randomNumbers;

    @BeforeEach
    void setUp() {
        RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();
        randomNumbers = randomNumbersGenerator.generate();
    }

    @Test
    @DisplayName("자동 로또 생성 숫자 6개 확인")
    void randomNumbersCreate() {
        assertThat(randomNumbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 로또 중복 확인")
    void checkDuplicated() {
        boolean duplicated = randomNumbers.stream()
                .distinct()
                .count() == randomNumbers.size();
        assertTrue(duplicated);
    }

    @Test
    @DisplayName("자동 로또 생성 숫자 1부터 45 사이 확인")
    void checkNumbersInRange() {
        for (Integer randomNumber : randomNumbers) {
            assertThat(randomNumber).isBetween(MINIMUM_NUMBER, MAXIMUM_NUMBER);
        }
    }
}
