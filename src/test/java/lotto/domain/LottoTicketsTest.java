package lotto.domain;

import lotto.domain.lottogenerator.MockLottoGeneratingStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @Test
    void 로또구입금액_만큼_로또가_생성되었는지_확인() {
        assertThat(new LottoTickets(new Payment(13_000), new MockLottoGeneratingStrategy()).size()).isEqualTo(13);
    }
}
