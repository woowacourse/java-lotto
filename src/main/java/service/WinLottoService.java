package service;

import static constant.WinLottoInfo.NONE;
import static constant.WinLottoInfo.THIRD;

import constant.WinLottoInfo;
import model.LottoNumbers;
import model.WinLotto;

import java.util.Arrays;
import java.util.List;

public class WinLottoService {

    public static WinLottoInfo result(LottoNumbers purchasedLotto, WinLotto winLotto) {
        int matchNumberCount = winLotto.countMatchNumber(purchasedLotto);
        boolean bonusMatch = winLotto.bonusMatch(purchasedLotto);
        if (matchNumberCount <= 2) {
            return NONE;
        }
        if (matchNumberCount == 5 && !bonusMatch) {
            return THIRD;
        }
        List<WinLottoInfo> filteredWinLottoInfo = Arrays.stream(WinLottoInfo.values())
                .filter((winLottoInfo) -> winLottoInfo.getMatchNumberCount() == matchNumberCount)
                .toList();
        return filteredWinLottoInfo.getFirst();
    }


}
