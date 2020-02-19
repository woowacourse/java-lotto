package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumbersFactoryTest {
    @Test
    void 팩토리_생성_테스트() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(6))).isTrue();
    }
}
