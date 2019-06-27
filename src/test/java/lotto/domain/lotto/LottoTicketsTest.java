package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    private LottoRepository lottoRepository;

    @BeforeEach
    void setUp() {
        lottoRepository = new LottoRepository();
    }

    @Test
    void 수동_로또_확인() {
        lottoRepository.add(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));
        assertThat(new LottoTickets(lottoRepository).getManualLottoTickets()).isEqualTo(lottoRepository.getLottos().stream().filter(lotto -> !lotto.getIsAuto()).collect(Collectors.toList()));
    }

    @Test
    void 자동_로또_확인() {
        lottoRepository.add(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        lottoRepository.add(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        assertThat(new LottoTickets(lottoRepository).getAutoLottoTickets()).isEqualTo(lottoRepository.getLottos().stream().filter(Lotto::getIsAuto).collect(Collectors.toList()));
    }

    @Test
    void 자동과_수동이_모두_조회() {
        lottoRepository.add(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        lottoRepository.add(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        lottoRepository.add(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));

        LottoTickets lottoTickets = new LottoTickets(lottoRepository);
        assertThat(lottoTickets.getAllLottoTickets().size())
                .isEqualTo(lottoTickets.getCountOfAutoLotto() + lottoTickets.getCountOfManualLotto());
    }
}
