package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoNumbersFactoryTest {
    @Test
    void 팩토리_생성_테스트() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(6))).isTrue();
    }

    @Test
    void 팩토리_생성_테스트2() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(Arrays.asList(1,2,3,4,5,6));
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(6))).isTrue();
    }
}
