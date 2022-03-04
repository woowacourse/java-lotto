package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @Test
    void 생성_오류() {
        assertThatThrownBy(() -> new LottoTickets(List.of(List.of(1, 2, 3, 4, 5, 46)), 1))
            .isInstanceOf(Exception.class);
    }


    @Test
    void 불변_확인() {
        LottoTickets lottoTickets = new LottoTickets(List.of(List.of(1, 2, 3, 4, 5, 6)), 1);
        List<LottoTicket> value = lottoTickets.get();
        assertThatThrownBy(() -> value.add(LottoTicket.ofAuto()))
            .isInstanceOf(Exception.class);
    }
}