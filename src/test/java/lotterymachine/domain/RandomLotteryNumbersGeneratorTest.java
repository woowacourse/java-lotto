package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLotteryNumbersGeneratorTest {

    @Test
    @DisplayName("6개의 랜덤 LotteryNumber 리스트를 생성한다.")
    void generate() {
        RandomLotteryNumbersGenerator randomLotteryNumbersGenerator = new RandomLotteryNumbersGenerator();
        assertThat(randomLotteryNumbersGenerator.generate().size()).isEqualTo(6);
    }
}