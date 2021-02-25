package lotto.domain;

import lotto.exception.IllegalWinningLottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복되는 지 검증")
    void checkDuplicateBonusNumber() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = LottoTicketFactory.createWinningLotto(Arrays.asList("1", "2", "3", "4", "5", "7"), "3");
        }).isInstanceOf(IllegalWinningLottoException.class)
                .hasMessage("3 : 티켓 내 숫자와 보너스 번호가 중복됩니다.");
    }
}