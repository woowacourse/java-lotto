package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTicketTest {

    @Test
    @DisplayName("로또번호가 6개가 아닌 경우 오류를 발생시키는지 확인한다.")
    void checkLottoNumberSize() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3));
        assertThatThrownBy(() -> WinningTicket.of(numbers, 10))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(LottoTicket.LOTTO_TICKET_SIZE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("로또 번호를 비교하여 맞는 갯수를 잘 찾아내는지 확인한다.")
    void compareMatchCountTest() {
        Set<Integer> winningNumberValues = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningTicket winningTicket = WinningTicket.of(winningNumberValues, bonusNumber);
        LottoTicket lottoTicket = LottoTicket.from(winningNumberValues);
        assertThat(winningTicket.compareMatchCount(lottoTicket)).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스 넘버와 비교해서 맞는지 확인한다.")
    void isMatchBonusNumberTest() {
        Set<Integer> winningNumberValues = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        int bonusNumber = 7;
        WinningTicket winningTicket = WinningTicket.of(winningNumberValues, bonusNumber);
        LottoTicket lottoTicket = LottoTicket.from(winningNumberValues);
        assertThat(winningTicket.isMatchBonusNumber(lottoTicket)).isTrue();
    }
}