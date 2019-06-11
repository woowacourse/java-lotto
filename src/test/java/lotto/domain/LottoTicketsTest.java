package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @Test
    public void 결과를_제대로_만드는지_확인() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Lotto lotto = new Lotto(lottoNumbers);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            lottos.add(lotto);
        }
        LottoTickets lottoTickets = new LottoTickets(lottos);

        WinningLotto winningLotto = new WinningLotto(lotto, new LottoNumber(7));

        LottoResult lottoResultByMe = new LottoResult();
        lottoResultByMe.add(Rank.FIRST);
        lottoResultByMe.add(Rank.FIRST);

        assertThat(lottoTickets.getLottoResult(winningLotto)).isEqualTo(lottoResultByMe);
    }
}
