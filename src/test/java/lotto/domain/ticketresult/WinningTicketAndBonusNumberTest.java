package lotto.domain.ticketresult;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningTicketAndBonusNumberTest {
    private LottoTicket winningTicket;

    @BeforeEach
    void setUp() {
        winningTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(4),
                LottoNumbers.get(5),
                LottoNumbers.get(6)
            )
        );
    }

    @DisplayName("우승 로또 번호 정상 생성")
    @Test
    void Should_Not_ThrowException_When_ValidLottoNumbers() {
        LottoNumber bonusNumber = LottoNumbers.get(7);
        assertThatCode(
            () -> new WinningTicketAndBonusNumber(winningTicket, bonusNumber)
        ).doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호에 보너스 번호 포함시 에러")
    @Test
    void Should_ThrowException_When_WinningLottoNumbersContainBonusNumber() {
        LottoNumber bonusNumber = LottoNumbers.get(5);
        assertThatThrownBy(
            () -> new WinningTicketAndBonusNumber(winningTicket, bonusNumber)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
