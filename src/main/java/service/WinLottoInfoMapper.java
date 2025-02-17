package service;

import static model.WinLottoInfo.NONE;
import static model.WinLottoInfo.THIRD;

import java.util.Arrays;
import java.util.List;
import model.LottoNumbers;
import model.WinLotto;
import model.WinLottoInfo;

public class WinLottoInfoMapper {

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
