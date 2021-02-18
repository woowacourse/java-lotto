package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.RandomLottoMachine;
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

}
