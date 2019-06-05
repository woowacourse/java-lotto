package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomLottoGeneratingStrategyTest {
    @Test
    void 자동으로_로또숫자_생성되는지_확인() {
        RandomLottoGeneratingStrategy strategy = new RandomLottoGeneratingStrategy();
        List<Integer> lottoNumbers = strategy.generate();

        for (int lottoNumber : lottoNumbers) {
            assertThat(lottoNumber >= LottoNumber.MIN_BOUNDARY).isTrue();
            assertThat(lottoNumber <= LottoNumber.MAX_BOUNDARY).isTrue();
        }

        assertThat(lottoNumbers.size()).isEqualTo(Lotto.LOTTO_NUMBER_SIZE);
    }
}
