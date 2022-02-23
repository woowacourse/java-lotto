package model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningNumbersTest {
    private WinningNumbers winningNumbers;

    @BeforeEach
    void beforeEach() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(new LottoNumber(String.valueOf(i)));
        }
        LottoNumber bonusBall = new LottoNumber("30");
        winningNumbers = WinningNumbers.of(numbers, bonusBall);

    }

    @Test
    @DisplayName("countContaining은 로또 티켓의 당첨 번호 개수를 반환한다")
    void returnWinningNumberCount() {
    	// given
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(new LottoNumber(String.valueOf(i)));
        }
        LottoTicket lottoTicket = new LottoTicket(numbers);

    	// when
        int count = winningNumbers.countContaining(lottoTicket);

    	// then
    	assertThat(count).isEqualTo(6);
    }

    @ParameterizedTest
    @CsvSource(value = {"30, true", "29, false"})
    @DisplayName("보너스 볼을 포함하면 true를 반환한다")
    void bonusBallContaining() {
        // given
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numbers.add(new LottoNumber(String.valueOf(i)));
        }
        numbers.add(new LottoNumber("30"));
        LottoTicket lottoTicket = new LottoTicket(numbers);

        // when
        boolean result = winningNumbers.containBonusBall(lottoTicket);

        // then
        assertThat(result).isTrue();
    }
}
