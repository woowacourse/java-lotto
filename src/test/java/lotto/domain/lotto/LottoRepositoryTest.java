package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoRepositoryTest {
    private LottoRepository lottoRepository;

    @BeforeEach
    void setUp() {
        lottoRepository = new LottoRepository();
    }

    @Test
    void 로또_추가할_때_오류_전략이_null일_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> lottoRepository.register(null));
    }

    @Test
    void 수동_로또_추가_확인() {
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThat(lottoRepository.createLottoTickets())
                .isEqualTo(new LottoTickets(Arrays.asList(LottoGenerator
                        .create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))))));
    }

    @Test
    void 자동_로또_추가() {
        lottoRepository.register(new RandomLottoGeneratingStrategy());
        LottoTickets lottoTickets = lottoRepository.createLottoTickets();

        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(1);
    }
}