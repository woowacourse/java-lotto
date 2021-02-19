package lotto.domain;

import java.util.List;

public class LottoBoughtTicket extends LottoTicket {

    public LottoBoughtTicket(final List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public LottoRank getRank(final LottoWinner lottoWinner) {
        int matchCount = calculateMatchCount(lottoWinner.getLottoWinnerTicket());
        boolean matchBonusNumber = this.isContain(lottoWinner.getLottoWinnerBonusNumber());
        return LottoRank.matchLottoRank(matchCount, matchBonusNumber);
    }

    private int calculateMatchCount(final LottoWinnerTicket lottoWinnerTicket) {
        return (int) lottoWinnerTicket.getLottoNumbers()
                .stream()
                .filter(this::isContain)
                .count();
    }
}
