package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.ticketCount.CountLessZeroException;
import exception.ticketCount.CountMoreMaxException;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketCountTest {

    @Test
    void 정적팩토리_생성_테스트_0이하의_수동티켓_음수() {
        assertThatThrownBy(() -> LottoTicketCount.of(2, -1))
                .isInstanceOf(CountLessZeroException.class)
                .hasMessage("로또티켓 수는 1 이상이어야 합니다.");
    }

    @Test
    void 정적팩토리_생성_테스트_총티켓_수_이상의_수동티켓() {
        assertThatThrownBy(() -> LottoTicketCount.of(10, 11))
                .isInstanceOf(CountMoreMaxException.class)
                .hasMessage("수동로또 수가 구매한 로또 수를 초과할 수 없습니다.");
    }

    @Test
    void 정적팩토리_생성_테스트_오토티켓_수() {
        LottoTicketCount count = LottoTicketCount.of(10, 3);
        assertThat(count.ofAuto()).isEqualTo(7);
    }

    @Test
    void 정적팩토리_생성_테스트_수동티켓_수() {
        LottoTicketCount count = LottoTicketCount.of(10, 3);
        assertThat(count.ofManual()).isEqualTo(3);
    }
}