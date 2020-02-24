package lotto.model;

import lotto.utils.LottoRules;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final int INITIAL_NUMBER = 0;
    private Map<String, Integer> lottoResult;

    public LottoResult() {
        Map<String, Integer> lottoResult = new HashMap<>();
        lottoResult.put(LottoRank.FIFTH.name(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FOURTH.name(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.THIRD.name(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.SECOND.name(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FIRST.name(), INITIAL_NUMBER);
        this.lottoResult = lottoResult;
    }

    // TODO : 매치된 갯수와 LottoRank 이름을 연결 , Test 코드 구현
    public void checkCount(LottoTicket lottoTicket, WinNumber winNumber, BonusBall bonusBall) {
        int matchNumber = lottoTicket.matchNumber(winNumber);
        if (LottoRank.checkNoPrize(matchNumber)) {
            return ;
        }
        if (LottoRank.checkThirdWinner(matchNumber)) {
            checkSecondWinner(lottoTicket, matchNumber, bonusBall);
            return;
        }
        lottoResult.put(LottoRank.getNameByRank(matchNumber), lottoResult.get(LottoRank.getNameByRank(matchNumber)) + LottoRules.WINNING_COUNT.getNumber());
    }

    private void checkSecondWinner(LottoTicket lottoTicket, int matchNumber, BonusBall bonusBall) {
        if (lottoTicket.matchesWithBonusBall(bonusBall.getBonusNumber())) {
            lottoResult.put(LottoRank.SECOND.name(), lottoResult.get(LottoRank.SECOND.name()) + LottoRules.WINNING_COUNT.getNumber());
            return;
        }
        lottoResult.put(LottoRank.THIRD.name(), lottoResult.get(LottoRank.THIRD.name()) + LottoRules.WINNING_COUNT.getNumber());
    }

    public int rankResult(String rank) {
        return lottoResult.get(rank);
    }
}
