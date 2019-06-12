package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    private LottoRepository lottoRepository;

    @BeforeEach
    void setUp() {
        lottoRepository = new LottoRepository();
    }

    @Test
    void 수동_로또_확인() {
        lottoRepository.addManualLotto(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));
        assertThat(new LottoTickets(lottoRepository).getManualLottoTickets()).isEqualTo(lottoRepository.getManualLottos());
    }

    @Test
    void 자동_로또_확인() {
        lottoRepository.addAutoLottos(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        lottoRepository.addAutoLottos(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        assertThat(new LottoTickets(lottoRepository).getAutoLottoTickets()).isEqualTo(lottoRepository.getAutoLottos());
    }

    @Test
    void 자동과_수동이_모두_조회() {
        lottoRepository.addAutoLottos(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        lottoRepository.addAutoLottos(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        lottoRepository.addManualLotto(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));

        assertThat(new LottoTickets(lottoRepository).getAllLottoTickets().size())
                .isEqualTo(lottoRepository.getAutoLottos().size() + lottoRepository.getManualLottos().size());
    }
}
