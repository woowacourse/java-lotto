package lotto.domain.manager;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Arrays;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;
import lotto.domain.statistics.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoManagerTest {

    LottoManager lottoManager;
    LottoRepository lottoRepository;

    @BeforeEach
    void setup() {
        lottoManager = new LottoManager(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        lottoRepository = new LottoRepository();
    }

    @Test
    @DisplayName("로또 구매 결과 확인")
    void buyLottoCheck() {
        int count = 14;
        lottoRepository.generateLottoByTicket(() -> Arrays.asList(1, 2, 3, 4, 5, 6), count);

        Ticket ticket = new Ticket(new Money(14000));
        LottoRepository lottoRepositoryByLottoManager = lottoManager.buyLotto(ticket);

        for (int i = 0; i < count; i++) {
            List<Integer> expected = lottoRepository.toList().get(i).getNumbers();
            List<Integer> actual = lottoRepositoryByLottoManager.toList().get(i).getNumbers();
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    @DisplayName("로또 긁은 내역 확인")
    void scratchLottoCheck() {
        Ticket ticket = new Ticket(new Money(2000));

        lottoManager.buyLotto(ticket);
        WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
            new LottoNumber(7));
        RatingInfo ratingInfo = lottoManager.scratchLotto(winningLotto);

        assertThat(ratingInfo.get(Rating.FIRST)).isEqualTo(2);
    }
}
