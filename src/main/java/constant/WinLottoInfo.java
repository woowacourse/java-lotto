package constant;

import java.util.Arrays;
import java.util.List;
import model.LottoNumbers;
import model.WinLotto;

public enum WinLottoInfo {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(2, false, 0);

    private final Integer matchNumberCount;
    private final Boolean matchBonusNumber;
    private final Integer price;

    WinLottoInfo(Integer matchNumberCount, Boolean matchBonusNumber, Integer price) {
        this.matchNumberCount = matchNumberCount;
        this.matchBonusNumber = matchBonusNumber;
        this.price = price;
    }

    public static WinLottoInfo result(LottoNumbers purchasedLotto, WinLotto winLotto) {
        Integer matchNumberCount = winLotto.countMatchNumber(purchasedLotto);
        Boolean bonusMatch = winLotto.bonusMatch(purchasedLotto);
        if (matchNumberCount <= 2) {
            return NONE;
        }
        if (matchNumberCount == 5 && !bonusMatch) {
            return THIRD;
        }
        List<WinLottoInfo> filteredWinLottoInfo = Arrays.stream(WinLottoInfo.values())
                .filter((winLottoInfo) -> winLottoInfo.matchNumberCount == matchNumberCount)
                .toList();
        return filteredWinLottoInfo.getFirst();
    }

    public Integer getMatchNumberCount() {
        return matchNumberCount;
    }

    public Integer getPrice() {
        return price;
    }
}
