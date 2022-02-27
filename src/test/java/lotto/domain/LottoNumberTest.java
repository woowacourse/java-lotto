package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @Test
    public void 로또번호_생성_검증() {
        LottoNumber lottoNumber = new LottoNumber();
        List<Integer> testValues = lottoNumber.createLottoNumbers();
        assertThat(testValues.size()).isEqualTo(Set.copyOf(testValues).size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    public void 로또번호_범위_성공(int value) {
        LottoNumber lottoNumber = new LottoNumber();
        lottoNumber.createLottoNumbers();
        List<Integer> lottoNumbers = lottoNumber.getLottoNumbers();

        assertThat(lottoNumbers.contains(value)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    public void 로또번호_범위_실패(int value) {
        LottoNumber lottoNumber = new LottoNumber();
        lottoNumber.createLottoNumbers();
        List<Integer> lottoNumbers = lottoNumber.getLottoNumbers();

        assertThat(lottoNumbers.contains(value)).isFalse();
    }
}
