package lotto.model;


public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;


    public WinningLotto(Lotto winningLottoTicket, LottoNumber bonusNumber) {
        if (winningLottoTicket.contains(bonusNumber)) {
            throw new IllegalNumberCombinationException();
        }
        this.winningLotto = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public Prize prizeOf(Lotto lotto) {
        int matchCount = lotto.countMatchLottoNumber(this.winningLotto);
        Prize prize = Prize.getPrizeRank(matchCount, lotto.containsNumber(bonusNumber));
        return prize;
    }
}
