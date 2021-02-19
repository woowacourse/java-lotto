package lotto.domain.ticketresult;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningTicketAndBonusNumberTest {
    private final LottoTicket lottoTicket = new LottoTicket(
        Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        )
    );

    @DisplayName("우승 로또 번호 정상 생성")
    @Test
    void Should_Not_ThrowException_When_ValidLottoNumbers() {
        LottoNumber bonusNumber = new LottoNumber(7);
        assertThatCode(
            () -> new WinningTicketAndBonusNumber(lottoTicket, bonusNumber)
        ).doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호에 보너스 번호 포함시 에러")
    @Test
    void Should_ThrowException_When_WinningLottoNumbersContainBonusNumber() {
        LottoNumber bonusNumber = new LottoNumber(5);
        assertThatThrownBy(
            () -> new WinningTicketAndBonusNumber(lottoTicket, bonusNumber)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
