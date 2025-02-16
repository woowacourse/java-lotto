package util.random;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRandomUtilTest {

    private RandomUtil randomUtil;

    @BeforeEach
    void beforeEach() {
        randomUtil = new LottoRandomUtil();
    }

    @Test
    @DisplayName("generateRandomNumbers 호출 시 지정된 범위 내 고유 숫자를 생성하는지 확인")
    void generateRandomNumbers() {
        // given
        int minNumber = 1;
        int maxNUmber = 45;
        int count = 6;

        // when
        List<Integer> randomNumbers = randomUtil.generateRandomNumbers(minNumber, maxNUmber, count);

        // then
        assertThat(randomNumbers).hasSize(count);
        assertThat(new HashSet<>(randomNumbers)).hasSize(count);
        for (Integer randomNumber : randomNumbers) {
            assertThat(randomNumber).isBetween(minNumber, maxNUmber);
        }
    }
}