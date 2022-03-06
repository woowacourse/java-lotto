package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class WinLottoNumbersTest {

    private WinLottoNumbers winLottoNumbers;

    @BeforeEach
    void 당첨번호_생성() {
        winLottoNumbers = WinLottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
    }

    @Test
    void 로또_번호_중복_확인() {
        assertThatThrownBy(() -> WinLottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 5), 10))
            .isInstanceOf(Exception.class);
    }

    @Test
    void 로또_번호와_보너스_중복_확인() {
        assertThatThrownBy(() -> WinLottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6))
            .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6:false:6", "1, 2, 3, 4, 5, 7:false:5",
        "1, 2, 3, 4, 8, 7:false:4", "1, 2, 3, 4, 5, 10:true:5"}, delimiter = ':')
    void 로또_번호_일치_검사(String lottoNumbersText, boolean hasBonus, int expected) {
        List<Integer> lottoNumbers = Arrays.stream(lottoNumbersText.split(", "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);

        Rank rank = winLottoNumbers.match(lottoTicket);
        assertThat(rank).isEqualTo(Rank.of(expected, hasBonus));
    }
}