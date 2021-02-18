package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoBoughtTicket extends LottoTicket {

    private static final int TOTAL_NUMBER_COUNT = 12;

    public LottoBoughtTicket(final List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public LottoRank getRank(final LottoWinner lottoWinner) {
        int matchCount = calculateMatchCount(lottoWinner.getLottoWinnerTicket());
        boolean matchBonusNumber = this.isContain(lottoWinner.getLottoWinnerBonusNumber());
        return LottoRank.matchLottoRank(matchCount, matchBonusNumber);
    }

    private int calculateMatchCount(final LottoWinnerTicket lottoWinnerTicket) {
        Set<LottoNumber> matchingCheckContainer = new HashSet<>(this.getLottoNumbers());
        matchingCheckContainer.addAll(lottoWinnerTicket.getLottoNumbers());
        return TOTAL_NUMBER_COUNT - matchingCheckContainer.size();
    }
}
