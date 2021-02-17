package lotto.domain;

import java.util.*;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public List<PrizeType> checkWinningTickets(WinningLotto winningLotto) {
        List<PrizeType> winningTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : this.lottoTickets) {
            winningTickets.add(PrizeType.getPrizeType(lottoTicket.getMatchingCount(winningLotto.getWinningTicket().lottoTicket()),
                    lottoTicket.isMatchingBonusNumber(winningLotto.getBonusNumber())));
        }
        return winningTickets;
    }

//    private int getTicketPrizeTypeCount(List<LottoNumber> numbers, LottoNumber bonusBall, PrizeType prizeType) {
//        int count = 0;
//        for (LottoTicket lottoTicket : lottoTickets) {
//            count += addPrize(lottoTicket.getMatchingCount(numbers, bonusBall), prizeType);
//        }
//        return count;
//    }
//
//    private int addPrize(double matchingCount, PrizeType prizeType) {
//        if (prizeType.isEqualToMatchCount(matchingCount)) {
//            return 1;
//        }
//        return 0;
//    }
}
