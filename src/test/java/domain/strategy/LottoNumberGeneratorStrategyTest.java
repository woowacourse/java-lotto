package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import constants.LottoNumbers;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberGeneratorStrategyTest {
    @Test
    @DisplayName("랜덤 생성한 로또 번호는 1 ~ 45사이여야 한다")
    void randomLottoNumberGenerateTest() {
        LottoNumberGenerateStrategy lottoNumberGenerateStrategy = new RandomLottoNumberGenerateStrategy();

        IntStream.range(0, 100000)
                .forEach(i -> {
                    List<Integer> generatedWinningNumbers = lottoNumberGenerateStrategy.generateWinningNumbers();
                    assertThat(LottoNumbers.LOTTO_NUMBERS.containsAll(generatedWinningNumbers)).isTrue();
                });
    }
}
