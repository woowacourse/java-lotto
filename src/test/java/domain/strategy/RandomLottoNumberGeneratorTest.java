package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoNumber;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RandomLottoNumberGenerator 테스트")
public class RandomLottoNumberGeneratorTest {
    @Test
    @DisplayName("랜덤 생성한 로또 번호는 1 ~ 45사이여야 한다")
    void randomLottoNumberGenerateTest() {
        LottoNumberGenerateStrategy randomLottoNumberGenerator = new RandomLottoNumberGenerator();

        IntStream.range(0, 100000)
                .forEach(i -> {
                    List<Integer> generatedWinningNumbers = randomLottoNumberGenerator.generateLottoNumbers();
                    assertThat(LottoNumber.LOTTO_NUMBERS.containsAll(generatedWinningNumbers)).isTrue();
                });
    }
}
