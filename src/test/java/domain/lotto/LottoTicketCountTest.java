package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketCountTest {

    @Test
    void 정적팩토리_생성_테스트_0이하의_수동티켓_음수() {
        assertThatThrownBy(() -> LottoTicketCount.of(1, -1))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 정적팩토리_생성_테스트_총티켓_수_이상의_수동티켓() {
        assertThatThrownBy(() -> LottoTicketCount.of(10, 11))
                .isInstanceOf(Exception.class);
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