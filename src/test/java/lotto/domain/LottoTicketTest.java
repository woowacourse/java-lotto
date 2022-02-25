package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import lotto.domain.generator.AutoLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @DisplayName("로또 티켓 생성 시점에 로또 번호 생성을 위한 전략을 활용한다.")
    @Test
    void 로또_티켓_정상_생성() {
        // given & when & then
        assertThatCode(() -> new LottoTicket(new AutoLottoNumberGenerator()))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호가 중복이면 예외를 던진다.")
    @Test
    void 로또_티켓_중복() {
        // given & when & then
        assertThatThrownBy(() -> new LottoTicket(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("getter 로 꺼내오는 리스트를 변경할 경우 예외를 던진다.")
    @Test
    void 로또_티켓_불변_검증() {
        // given
        LottoTicket lottoTicket = new LottoTicket(new AutoLottoNumberGenerator());

        // when
        Set<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();

        // then
        assertThatThrownBy(() -> lottoNumbers.add(new LottoNumber(1)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("로또 티켓에 당첨 번호가 존재하는지 유무를 반환한다.")
    @Test
    void 당첨_번호_인지_확인() {
        // given
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(1);

        // when
        boolean result = lottoTicket.isSame(lottoNumber);

        // then
        assertThat(result).isTrue();
    }
}