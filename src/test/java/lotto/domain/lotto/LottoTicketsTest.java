package lotto.domain.lotto;

import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketsTest {
    @Test
    void 생성자_확인() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThat(lottoRepository.getLottos()).isEqualTo(new LottoTickets(lottoRepository).getLottoTickets());
    }

    @Test
    void 생성자_확인_null이_들어왔을_경우() {
        assertThatThrownBy(() -> new LottoTickets(null)).isInstanceOf(NullPointerException.class);
    }
}
