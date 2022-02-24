package lotto.model;

import java.util.ArrayList;
import java.util.List;

import lotto.model.result.LottoRank;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.WinningTicket;
import lotto.model.ticket.number.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningTicketTest {

    @Test
    @DisplayName("당첨번호를 생성한다.")
    public void createWinningTicket() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoNumber bonusball = new LottoNumber(7);
        // then
        Assertions.assertThatCode(() -> new WinningTicket(lottoTicket, bonusball))
            .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("당첨번호와 로또번호를 비교할 수 있다.")
    public void compareWinningTicketWithLottoTicket() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoNumber bonusball = new LottoNumber(7);
        WinningTicket winningTicket = new WinningTicket(lottoTicket, bonusball);
        // when
        LottoRank result = winningTicket.compare(lottoTicket);
        // then
        Assertions.assertThat(result).isEqualTo(LottoRank.FIRST);
    }

}
