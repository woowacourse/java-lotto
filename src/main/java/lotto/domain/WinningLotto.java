package lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNo bonusNo;

    public WinningLotto(Lotto winningLotto, LottoNo bonusNo) {
        if (winningLotto.contains(bonusNo)) {
            throw new IllegalArgumentException("보너스 번호가 로또 번호에 포함됩니다.");
        }
        this.winningLotto = winningLotto;
        this.bonusNo = bonusNo;
    }

    public int getWinningLottoNo(int index) {
        return winningLotto.getLottoNo(index);
    }

    public int getBonusNo() {
        return bonusNo.getNo();
    }

    public WinningLottoState match(LottoTickets lottoTickets) {
        Map<Rank, Integer> winningLottoState = initWinningLottoState();
        for (int index = 0; index < lottoTickets.size(); index++) {
            Rank rank = calculateRank(lottoTickets.get(index));
            winningLottoState.put(rank, winningLottoState.get(rank) + 1);
        }
        return new WinningLottoState(winningLottoState);
    }

    private Rank calculateRank(LottoTicket lotto) {
        return Rank.valueOf(lotto.includedNumber(winningLotto), lotto.contains(bonusNo));
    }

    private Map<Rank, Integer> initWinningLottoState() {
        Map<Rank, Integer> winningLottoState = new TreeMap<>(Collections.reverseOrder());
        for (Rank rank : Rank.values()) {
            winningLottoState.put(rank, 0);
        }
        return winningLottoState;
    }
}