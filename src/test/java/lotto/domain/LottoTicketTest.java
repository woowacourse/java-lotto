package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.generator.AutoLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketTest {

    @DisplayName("로또 티켓 생성 시점에 로또 번호 생성을 위한 전략을 활용한다.")
    @Test
    void 로또_티켓_정상_생성() {
        // given & when & then
        assertThatCode(() -> new LottoTicket(new AutoLottoNumberGenerator()))
                .doesNotThrowAnyException();
    }

    @DisplayName("Getter로 꺼내오는 리스트를 변경할 경우 예외를 던진다.")
    @Test
    void 로또_티켓_불변_검증() {
        // given
        LottoTicket lottoTicket = new LottoTicket(new AutoLottoNumberGenerator());

        // when
        List<Integer> lottoNumbers = lottoTicket.getLottoNumbers();

        // then
        assertThatThrownBy(() -> lottoNumbers.add(0))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("LottoTicket을 기반으로 당첨 번호가 존재하는지 유무를 반환한다.")
    @Test
    void 당첨_번호_인지_확인() {
        // given
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 5, 6));
        WinningNumber winningNumber = new WinningNumber(1, BallType.BONUS);

        // when
        boolean result = lottoTicket.isSame(winningNumber);

        // then
        assertThat(result).isTrue();
    }
}