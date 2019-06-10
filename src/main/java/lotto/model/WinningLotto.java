package lotto.model;


import lotto.model.exceptions.IllegalNumberCombinationException;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;


    public WinningLotto(Lotto winningLottoTicket, LottoNumber bonusNumber) {
        if (winningLottoTicket.containsNumber(bonusNumber)) {
            throw new IllegalNumberCombinationException();
        }
        this.winningLotto = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public Prize prizeOf(Lotto lotto) {
        Prize prize = lotto.getRank(winningLotto, bonusNumber);
        return prize;
    }
}
