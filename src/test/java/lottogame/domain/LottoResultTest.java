package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 당첨_결과를_알려주는_클래스_구현() {
        LottoTickets lottoTickets = LottoTickets.generate();
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"), 45);
        assertThat(LottoResultGenerator.create(lottoTickets, winningLotto)).isExactlyInstanceOf(LottoResult.class);
    }

    @Test
    void 이익_계산_테스트() {
        LottoTickets lottoTickets = LottoTickets.generate();
        lottoTickets.addManualLotto("1,2,3,4,5,6");
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"), 7);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        assertThat(lottoResult.getRateOfLotto(Money.generate(1000))).isEqualTo(200000000);
    }
}
