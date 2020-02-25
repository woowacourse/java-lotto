package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private static List<Lotto> lottoTickets = new ArrayList<>();

    public void addLotto(Lotto oneLotto) {
        lottoTickets.add(oneLotto);
    }

    public LottoResult countWinningLotto(WinningNumber winningNumber) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottoTickets){
            LottoRank rank = LottoRank.findRank(winningNumber.countWinningMatch(lotto),
                                            winningNumber.isBonusMatch(lotto));
            lottoResult.addWinningRankCount(rank);
        }
        return lottoResult;
    }

    public int getTicketsSize() {
        return lottoTickets.size();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
