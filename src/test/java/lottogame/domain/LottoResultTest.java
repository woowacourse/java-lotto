package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 당첨_결과를_알려주는_클래스_구현() {
        LottoTickets lottoTickets = LottoTickets.generate();
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"),"45");
        assertThat(LottoResult.generate(lottoTickets, winningLotto)).isExactlyInstanceOf(LottoResult.class);
    }
}
