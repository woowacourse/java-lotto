package lotto.domain.ticketresult;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoNumbersTest {
    private final LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(
                    LottoNumber.of(1),
                    LottoNumber.of(2),
                    LottoNumber.of(3),
                    LottoNumber.of(4),
                    LottoNumber.of(5),
                    LottoNumber.of(6)
            )
    );

    @DisplayName("우승 로또 번호 정상 생성")
    @Test
    void Should_Not_ThrowException_When_ValidLottoNumbers() {
        LottoNumber bonusNumber = LottoNumber.of(7);
        assertThatCode(
                () -> new WinningLottoNumbers(lottoTicket, bonusNumber)
        ).doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호에 보너스 번호 포함시 에러")
    @Test
    void Should_ThrowException_When_LottoNumbersContainBonusNumber() {
        LottoNumber bonusNumber = LottoNumber.of(5);
        assertThatThrownBy(
                () -> new WinningLottoNumbers(lottoTicket, bonusNumber)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
