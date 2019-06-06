package lotto.domain;

import lotto.domain.lottomanager.LottoNumber;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.LottoCreator;
import lotto.utils.NullCheckUtil;

import java.util.List;

public class WinningLotto {
    private LottoTicket winningLotto;

    private WinningLotto(LottoTicket winningLotto) {
        NullCheckUtil.checkNullLottoTicket(winningLotto);
        this.winningLotto = winningLotto;
    }

    public static WinningLotto createWinningLotto(List<Integer> winningNumbers) {
        NullCheckUtil.checkNullIntegerNumbers(winningNumbers);
        return new WinningLotto(LottoCreator.createManualTickets(winningNumbers));
    }

    public Integer getMatchedWinningNumbersCount(LottoTicket lottoTicket) {
        NullCheckUtil.checkNullLottoTicket(lottoTicket);
        return winningLotto.getMatchedNumbersCount(lottoTicket);
    }

    public boolean isContainedWinningNumbers(LottoNumber bonusBall) {
        NullCheckUtil.checkNullLottoNumber(bonusBall);
        return winningLotto.isContainedNumbers(bonusBall);
    }
}
