package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.WinningLotto;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRepositoryTest {

    @Test
    @DisplayName("티켓 개수만큼 로또 생성")
    void generateLottoByTicket() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.generateLottoByTicket(new RandomLottoMachine(), 5);
        assertThat(lottoRepository.toList().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("로또 생성 정보 비교")
    void compareLottoByTicket() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.generateLottoByTicket(() -> expected, 5);
        assertThat(lottoRepository.toList().get(0).getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 수동 생성 정보 비교")
    void compareLottoByManual() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.generateLottoByManual(Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lottoRepository.toList().get(0).getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 긁은 내역 확인")
    void scratchLottoCheck() {
        LottoRepository lottoRepository = new LottoRepository();
        Ticket ticket = new Ticket(new Money(2000));

        lottoRepository
            .generateLottoByTicket(() -> Arrays.asList(1, 2, 3, 4, 5, 6), ticket.getCount());

        WinningLotto winningLotto = new WinningLotto(
            Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
            LottoNumber.from(7));
        RatingInfo ratingInfo = lottoRepository.scratchLotto(winningLotto);

        assertThat(ratingInfo.get(Rating.FIRST)).isEqualTo(2);
    }

}
