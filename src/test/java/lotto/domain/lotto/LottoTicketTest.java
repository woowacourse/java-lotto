package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketTest {
    @Test
    void 로또_정상생성() {
        LottoTicket lotto = LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket lotto2 = LottoTicket.create(() -> Arrays.asList(6, 5, 4, 3, 2, 1));

        assertThat(lotto).isEqualTo(lotto2);
    }

    @Test
    void 중복_로또번호() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }

    @Test
    void 로또번호_5개() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5));
        });
    }

    @Test
    void 로또번호_7개() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        });
    }

    @Test
    void 번호범위_벗어남() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 46));
        });
    }

    @Test
    void 번호_일치_개수_확인() {
        LottoTicket user = LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));

        LottoNumberGroup goal = LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 7, 8, 9));
        assertThat(user.countOfMatch(goal)).isEqualTo(3);
    }

    @Test
    void 포함하는_번호_확인() {
        LottoTicket user = LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(user.contains(LottoNumber.of(6))).isTrue();
    }

    @Test
    void 포함하지않는_번호_확인() {
        LottoTicket user = LottoTicket.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(user.contains(LottoNumber.of(7))).isFalse();
    }
}
