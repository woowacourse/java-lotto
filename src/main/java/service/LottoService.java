package service;

import domain.*;

import java.util.List;

public class LottoService {
    public List<Lotto> buyLottos(int purchaseAmount){
        return LottoFactory.makeLotto(purchaseAmount);
    }

    public void calcResult(LottoStats lottoStats, List<Lotto> lottos, WinningLotto winningLotto){
        for(Lotto lotto : lottos){
            Rank rank = lotto.getRank(winningLotto.winningNumbers(), winningLotto.bonusBall());
            lottoStats.addLottoRankCount(rank);
        }
    }
}
