package lotto.cotroller;

import lotto.domain.lottoTicket.Lottos;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.rank.RankResult;
import lotto.view.InputView;

public class RankController {
    public static RankResult request(Lottos lottos) {
        try {
            WinningLotto winningLotto = new WinningLotto(InputView.winningLotto(), InputView.bonusNumber());
            return lottos.matchLottoRank(winningLotto);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return request(lottos);
        }
    }
}
