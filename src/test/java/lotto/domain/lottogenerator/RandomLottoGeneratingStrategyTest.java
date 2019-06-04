package lotto.domain.lottogenerator;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomLottoGeneratingStrategyTest {
    @Test
    void 자동으로_로또숫자_생성되는지_확인() {
        RandomLottoGeneratingStrategy strategy = new RandomLottoGeneratingStrategy();
        List<LottoNumber> lottoNumbers = strategy.generate();

        LottoNumber minLottoNumber = LottoNumber.getNumber(1);
        LottoNumber maxLottoNumber = LottoNumber.getNumber(45);

        for (LottoNumber lottoNumber : lottoNumbers) {
            assertThat(lottoNumber.compareTo(minLottoNumber)).isEqualTo(1);
            assertThat(lottoNumber.compareTo(maxLottoNumber)).isEqualTo(-1);
        }
    }
}
