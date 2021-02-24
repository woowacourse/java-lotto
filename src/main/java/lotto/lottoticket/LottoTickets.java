package lotto.lottoticket;

import lotto.lottogame.LottoCount;
import lotto.lottoticket.ticketnumber.NumbersGenerator;
import lotto.ranking.Ranking;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(LottoCount lottoCount, NumbersGenerator numbersGenerator) {
        this.lottoTickets = new ArrayList<>();
        while (lottoCount.isBiggerThanZeroWithDecreasing()) {
            lottoTickets.add(new LottoTicket(numbersGenerator));
        }
    }

    public List<Ranking> makeResult(WinnerTicket winnerTicket, BonusBall bonusBall) {
        List<Ranking> result = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchCount = winnerTicket.findMatchCount(lottoTicket);
            boolean bonus = lottoTicket.containsBonusBall(bonusBall);
            result.add(Ranking.makePrice(matchCount, bonus));
        }
        return result;
    }

    public List<LottoTicket> getTickets() {
        return this.lottoTickets;
    }
}
