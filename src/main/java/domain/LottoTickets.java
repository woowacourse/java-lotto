package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private static List<Lotto> lottoTickets = new ArrayList<>();

    public LottoTickets(int lottoCount) {
        for (int index = 0; index < lottoCount; index++){
            lottoTickets.add(LottoFactory.createOneLotto());
        }
    }

    public void countWinningLotto(WinningNumber winningNumber, LottoResult lottoResult) {
        for (Lotto lotto : lottoTickets){
            LottoRank rank = LottoRank.findRank(winningNumber.countWinningMatch(lotto),
                                            winningNumber.isBonusMatch(lotto));
            lottoResult.addWinningRankCount(rank);
        }
    }

    public int getTicketsSize() {
        return lottoTickets.size();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
