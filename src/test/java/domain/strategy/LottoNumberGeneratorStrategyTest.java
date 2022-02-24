package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberGeneratorStrategyTest {
    @Test
    @DisplayName("랜덤 생성한 로또 번호는 1 ~ 45사이여야 한다")
    void randomLottoNumberGenerateTest() {
        LottoNumberGenerateStrategy lottoNumberGenerateStrategy = new RandomLottoNumberGenerateStrategy();
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }

        IntStream.range(0, 100000)
                .forEach(i -> {
                    List<Integer> generatedWinningNumbers = lottoNumberGenerateStrategy.generateLottoNumbers();
                    assertThat(lottoNumbers.containsAll(generatedWinningNumbers)).isTrue();
                });
    }
}
