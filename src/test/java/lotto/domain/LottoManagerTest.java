package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Arrays;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.primitive.Money;
import lotto.domain.primitive.Ticket;
import org.junit.jupiter.api.BeforeEach;
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
}
