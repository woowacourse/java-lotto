package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("해당 당첨 번호가 보너스볼인지 여부를 확인한다.")
    @Test
    void 담청_번호가_보너스_볼인지_확인() {
        // given
        WinningNumber winningNumber = new WinningNumber(1, BallType.BONUS);

        // when
        boolean result = winningNumber.isBonus();
        
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("LottoTicket을 기반으로 당첨 번호가 존재하는지 유무를 반환한다.")
    @Test
    void 당첨_번호_인지_확인() {
        // given
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 5, 6));
        WinningNumber winningNumber = new WinningNumber(1, BallType.BONUS);

        // when
        boolean result = winningNumber.isSame(lottoTicket);

        // then
        assertThat(result).isTrue();
    }
}