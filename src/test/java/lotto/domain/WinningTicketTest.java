package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import lotto.TestLottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTicketTest {

    @Test
    @DisplayName("당첨번호를 생성한다.")
    void createWinningTicket() {
        // given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1,2,3,4,5,6);
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoNumber bonusball = new LottoNumber(7);
        // then
        assertThatCode(() -> new WinningTicket(lottoTicket, bonusball))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 로또번호를 비교할 수 있다.")
    void compareWinningTicketWithLottoTicket() {
        // given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 6);
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoNumber bonusBall = new LottoNumber(7);
        WinningTicket winningTicket = new WinningTicket(lottoTicket, bonusBall);
        // when
        LottoRank result = winningTicket.compare(lottoTicket);
        // then
        assertThat(result).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("당첨번호와 보너스번호가 중복이면 예외 발생.")
    void validateDistinctByBonusBall() {
        //given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 6);
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoNumber bonusBall = new LottoNumber(6);
        //then
        assertThatThrownBy(() -> new WinningTicket(lottoTicket, bonusBall))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("보너스 숫자가 로또 번호에 포함되어 있습니다.");
    }
}
