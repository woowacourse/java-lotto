package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @Test
    void 생성자_확인() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.add(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));

        assertThat(lottoRepository.getLottos()).isEqualTo(new LottoTickets(lottoRepository).getLottoTickets());
    }
}
