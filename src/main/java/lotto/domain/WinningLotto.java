package lotto.domain;

import lotto.domain.lottomanager.LottoNumber;
import lotto.domain.lottomanager.LottoTicket;
import lotto.utils.NullCheckUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private LottoTicket winningLotto;

    private WinningLotto(LottoTicket winningLotto) {
        NullCheckUtil.checkNullLottoTicket(winningLotto);
        this.winningLotto = winningLotto;
    }

    public static WinningLotto createWinningLotto(List<Integer> winningNumbers) {
        NullCheckUtil.checkNullWinningNumbers(winningNumbers);
        Collections.sort(winningNumbers);

        List<LottoNumber> convertedLottoNumbers = winningNumbers.stream()
                .map(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());

        return new WinningLotto(LottoTicket.createLottoTicket(convertedLottoNumbers));
    }

    public Integer getMatchedWinningNumbersCount(LottoTicket lottoTicket) {
        return winningLotto.getMatchedNumbersCount(lottoTicket);
    }

    public boolean isContainedWinningNumbers(LottoNumber bonusBall) {
        return winningLotto.isContainedNumbers(bonusBall);
    }
}
