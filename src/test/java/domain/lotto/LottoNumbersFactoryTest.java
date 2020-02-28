package domain.lotto;

import generator.TestNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LottoNumbersFactoryTest {
    @Test
    @DisplayName("팩토리 생성 테스트 - 인터페이스 사용")
    void test1() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(6))).isTrue();
    }

    @Test
    @DisplayName("팩토리 생성 테스트 - 리스트 사용")
    void test2() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(6))).isTrue();
    }
}
