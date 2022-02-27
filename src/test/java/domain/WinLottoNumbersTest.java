package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class WinLottoNumbersTest {

    private WinLottoNumbers winLottoNumbers;

    @BeforeEach
    void 당첨번호_생성() {
        winLottoNumbers = WinLottoNumbers.of("1, 2, 3, 4, 5, 6", 10);
    }

    @Test
    void 로또_번호_중복_확인() {
        assertThatThrownBy(() -> WinLottoNumbers.of("1, 2, 3, 4, 5, 5", 10))
            .isInstanceOf(Exception.class);
    }

    @Test
    void 로또_번호와_보너스_중복_확인() {
        assertThatThrownBy(() -> WinLottoNumbers.of("1, 2, 3, 4, 5, 6", 6))
            .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6:6", "1, 2, 3, 4, 5, 7:5",
        "1, 2, 3, 4, 8, 7:4"}, delimiter = ':')
    void 로또_번호_일치_검사(String lottoNumbersText, int expected) {
        WinLottoNumbers winLottoNumbers = WinLottoNumbers.of(lottoNumbersText, 10);
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

        int sameNumber = winLottoNumbers.countSameNumber(lottoTicket);
        assertThat(sameNumber).isEqualTo(expected);
    }

    @Test
    void 로또_보너스_포함_될때_검사() {
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(10)));

        assertThat(winLottoNumbers.isContainsBonus(lottoTicket))
            .isTrue();
    }

    @Test
    void 로또_보너스_포함_안될때_검사() {
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

        assertThat(winLottoNumbers.isContainsBonus(lottoTicket)).isFalse();
    }
}