package domain;

import java.util.List;

public class LottoReferee {
    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoReferee(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoResult getLottoResult(LottoTicket lottoTicket) {
        int matchCount = getMatchCount(lottoTicket);
        boolean hasBonus = containsBonus(lottoTicket);

        return LottoResult.of(matchCount, hasBonus);
    }

    private int getMatchCount(LottoTicket lottoTicket) {
        return (int) lottoTicket.getNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean containsBonus(LottoTicket lottoTicket) {
        return lottoTicket.getNumbers().contains(bonusNumber);
    }
}
