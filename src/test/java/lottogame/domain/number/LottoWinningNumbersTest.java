package lottogame.domain.number;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import lottogame.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWinningNumbersTest {

    @Test
    @DisplayName("당첨 번호 일치 테스트")
    void testCreateWinningNumbers() {
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)), 7
        );

        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7)
            .stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet())
        );

        assertThat(lottoWinningNumbers.countMatchedWinningNumber(lottoTicket)).isEqualTo(5);
        assertThat(lottoWinningNumbers.hasBonusNumber(lottoTicket)).isTrue();
    }

    @Test
    @DisplayName("당첨 번호 개수가 올바르지 않을시 예외처리")
    void testWinningNumbersCountException() {
        assertThatThrownBy(() -> new LottoWinningNumbers(
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)), 45
        )).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new LottoWinningNumbers(
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)), 45
        )).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new LottoWinningNumbers(
            new HashSet<>(Arrays.asList(5, 5, 5, 5, 5, 5)), 45
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 존재할시 예외처리")
    void testBonusNumberDuplicateException() {
        assertThatThrownBy(() -> new LottoWinningNumbers(
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)), 6
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
