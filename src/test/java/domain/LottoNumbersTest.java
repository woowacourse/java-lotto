package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumbersTest {

    private LottoNumbers lottoNumbers;

    @BeforeEach
    void 로또_번호_생성() {
        lottoNumbers = new LottoNumbers(
            Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6:6", "1, 2, 3, 4, 5, 7:5", "1, 2, 3, 4, 8, 7:4"}, delimiter = ':')
    void 로또_번호_일치_검사(String lottoNumbersText, int expected) {
        WinLottoNumbers winLottoNumbers = WinLottoNumbers.of(lottoNumbersText, 10);

        int sameNumber = lottoNumbers.countSameNumber(winLottoNumbers);
        assertThat(sameNumber).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 로또_보너스_포함_될때_검사(int value) {
        assertThat(lottoNumbers.isContainsBonus(LottoNumber.valueOf(value)))
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {9,10,11})
    void 로또_보너스_포함_안될때_검사(int value) {
        assertThat(lottoNumbers.isContainsBonus(LottoNumber.valueOf(value))).isFalse();
    }
}