package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketTest {
    @Test
    void 로또_정상생성() {
        assertThat(LottoTicket.create(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(LottoTicket.create(Arrays.asList(3, 4, 5, 6, 1, 2)));
    }

    @Test
    void 중복_로또번호() {
        assertThrows(InvalidLottoTicketException.class, () -> {
            LottoTicket.create(Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }

    @Test
    void 로또번호_5개() {
        assertThrows(InvalidLottoTicketException.class, () -> {
            LottoTicket.create(Arrays.asList(1, 2, 3, 4, 5));
        });
    }

    @Test
    void 로또번호_7개() {
        assertThrows(InvalidLottoTicketException.class, () -> {
            LottoTicket.create(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        });
    }

    @Test
    void 번호범위_벗어남() {
        assertThrows(InvalidLottoTicketException.class, () -> {
            LottoTicket.create("46, 1, 2, 3, 4, 5");
        });
    }

    @Test
    void 번호_일치_개수_확인() {
        LottoTicket winning = LottoTicket.create(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket user = LottoTicket.create(Arrays.asList(1, 2, 3, 8, 9, 10));
        assertThat(winning.countOfMatch(user)).isEqualTo(3);
    }
}
