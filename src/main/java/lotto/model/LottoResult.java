package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final int WINNING_COUNT = 1;
    private final int INITIAL_NUMBER = 0;
    private Map<LottoRank, Integer> lottoResult;

    public LottoResult() {
        Map<LottoRank, Integer> lottoResult = new HashMap<>();
        lottoResult.put(LottoRank.FIFTH, INITIAL_NUMBER);
        lottoResult.put(LottoRank.FOURTH, INITIAL_NUMBER);
        lottoResult.put(LottoRank.THIRD, INITIAL_NUMBER);
        lottoResult.put(LottoRank.SECOND, INITIAL_NUMBER);
        lottoResult.put(LottoRank.FIRST, INITIAL_NUMBER);
        this.lottoResult = lottoResult;
    }

    // TODO : 매치된 갯수와 LottoRank 이름을 연결 , Test 코드 구현
    public void checkCount(LottoTicket lottoTicket, WinNumber winNumber, BonusBall bonusBall) {
        int matchNumber = lottoTicket.matchNumber(winNumber);
        if (LottoRank.checkNoPrize(matchNumber)) {
            return ;
        }
        if (LottoRank.checkThirdWinner(matchNumber)) {
            checkSecondWinner(lottoTicket, bonusBall);
            return;
        }
        lottoResult.put(LottoRank.getNameByRank(matchNumber), lottoResult.get(LottoRank.getNameByRank(matchNumber)) + WINNING_COUNT);
    }

    private void checkSecondWinner(LottoTicket lottoTicket, BonusBall bonusBall) {
        if (lottoTicket.matchesWithBonusBall(bonusBall)) {
            lottoResult.put(LottoRank.SECOND, lottoResult.get(LottoRank.SECOND) + WINNING_COUNT);
            return;
        }
        lottoResult.put(LottoRank.THIRD, lottoResult.get(LottoRank.THIRD) + WINNING_COUNT);
    }

    public int rankResult(String rank) {
        return lottoResult.get(rank);
    }
}
