package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.WinningLotto;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LotteriesTest {

    @Test
    @DisplayName("티켓 개수만큼 로또 생성")
    void addLottoByTicket() {
        final Lotteries lotteries = new Lotteries();
        lotteries.addLottoByTicket(new RandomLottoMachine(), 5);
        assertThat(lotteries.toList().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("로또 생성 정보 비교")
    void compareLottoByTicket() {
        final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        final Lotteries lotteries = new Lotteries();
        lotteries.addLottoByTicket(() -> expected, 5);
        assertThat(lotteries.toList().get(0).getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 수동 생성 정보 비교")
    void compareLottoByManual() {
        final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        final Lotteries lotteries = new Lotteries();
        lotteries.addLottoByManual(
            Collections.singletonList(Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6))));
        assertThat(lotteries.toList().get(0).getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 긁은 내역 확인")
    void scratchLottoCheck() {
        final Lotteries lotteries = new Lotteries();
        final Ticket ticket = new Ticket(new Money(2000));

        lotteries
            .addLottoByTicket(() -> Arrays.asList(1, 2, 3, 4, 5, 6), ticket.getTotalCount());

        final WinningLotto winningLotto = new WinningLotto(
            Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
            LottoNumber.from(7));
        final RatingInfo ratingInfo = lotteries.scratchLotto(winningLotto);

        assertThat(ratingInfo.get(Rating.FIRST)).isEqualTo(2);
    }

}
