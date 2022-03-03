package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    public void 로또번호_범위_성공(int value) {
        List<Integer> lottoNumbers = LottoNumber.getRangeLottoNumbers();

        assertThat(lottoNumbers.contains(value)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    public void 로또번호_범위_실패(int value) {
        List<Integer> lottoNumbers = LottoNumber.getRangeLottoNumbers();

        assertThat(lottoNumbers.contains(value)).isFalse();
    }
}
