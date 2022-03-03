package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class WinNumbersTest {
    private WinNumbers winNumbers;

    @BeforeEach
    void 당첨번호_생성() {
        List<LottoNumber> lottoNumbers = NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        winNumbers = LottoFactory.createWinNums(lottoNumbers, LottoNumber.getInstance(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void 로또_숫자_확인(int input) {
        assertThat(winNumbers.contains(LottoNumber.getInstance(input)))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10})
    void 로또_숫자_포함안될때_검사(int input) {
        assertThat(winNumbers.contains(LottoNumber.getInstance(input)))
                .isFalse();
    }

    @Test
    void 로또_번호_중복_확인() {
        List<LottoNumber> lottoNumbers = NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 2));
        assertThatThrownBy(() -> LottoFactory.createWinNums(lottoNumbers, LottoNumber.getInstance(10)))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 로또_번호와_보너스_중복_확인() {
        List<LottoNumber> lottoNumbers = NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 2));
        assertThatThrownBy(() -> LottoFactory.createWinNums(lottoNumbers, LottoNumber.getInstance(5)))
                .isInstanceOf(Exception.class);
    }
}