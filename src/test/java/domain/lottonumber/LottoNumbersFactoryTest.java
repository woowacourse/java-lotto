package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoNumbersFactoryTest {
    @Test
    void 팩토리_생성_테스트() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(6))).isTrue();
    }

    @Test
    void 팩토리_생성_테스트2() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(Arrays.asList(1,2,3,4,5,6));
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(6))).isTrue();
    }
}
