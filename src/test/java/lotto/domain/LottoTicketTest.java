package lotto.domain;

import static lotto.domain.LottoTestDataGenerator.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @DisplayName("번호 리스트를 활용하여 로또 티켓을 생성한다.")
    @Test
    void 로또_티켓_정상_생성() {
        // given & when & then
        assertDoesNotThrow(() -> new LottoTicket(generateNumbers()));
    }

    @DisplayName("로또 번호의 개수가 7개 이상이면 예외를 던진다.")
    @Test
    void 로또_티켓_번호_개수_초과() {
        // given & when & then
        assertThatThrownBy(() -> new LottoTicket(List.of(LottoNumber.of(1), LottoNumber.of(2))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("번호의 갯수가 적절하지 않습니다. 또한 중복될 수 없습니다.");
    }

    @DisplayName("로또 번호가 중복이면 예외를 던진다.")
    @Test
    void 로또_티켓_중복() {
        // given & when & then
        assertThatThrownBy(() -> new LottoTicket(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                        LottoNumber.of(5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("번호의 갯수가 적절하지 않습니다. 또한 중복될 수 없습니다.");
    }

    @DisplayName("getter 로 꺼내오는 리스트를 변경할 경우 예외를 던진다.")
    @Test
    void 로또_티켓_불변_검증() {
        // given
        LottoTicket lottoTicket = new LottoTicket(generateNumbers());

        // when
        Set<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();

        // then
        assertThatThrownBy(() -> lottoNumbers.add(LottoNumber.of(1)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("로또 티켓에 당첨 번호가 존재하는지 유무를 반환한다.")
    @Test
    void 당첨_번호_인지_확인() {
        // given
        LottoTicket lottoTicket = new LottoTicket(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                        LottoNumber.of(6)));
        LottoNumber lottoNumber = LottoNumber.of(1);

        // when
        boolean result = lottoTicket.contains(lottoNumber);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("로또 티켓을 비교하여 서로 맞는 번호의 개수를 반환한다.")
    @Test
    void 맞는_번호_갯수_확인() {
        // given
        LottoTicket lottoTicket = new LottoTicket(generateNumbers());
        LottoTicket targetLottoTicket = new LottoTicket(generateNumbers());

        // when
        int sameNumberCount = lottoTicket.getSameNumberCount(targetLottoTicket);

        // then
        assertThat(sameNumberCount).isEqualTo(6);
    }
}