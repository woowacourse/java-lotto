package constant;

import java.util.Arrays;
import java.util.List;
import model.LottoNumbers;
import model.WinLotto;

public enum WinLottoInfo {
    FIRST(6,  2000000000),
    SECOND(5,  30000000),
    THIRD(5,  1500000),
    FOURTH(4,  50000),
    FIFTH(3,  5000),
    NONE(2,  0);

    private final int matchNumberCount;
    private final int price;

    WinLottoInfo(int matchNumberCount,  int price) {
        this.matchNumberCount = matchNumberCount;

        this.price = price;
    }

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
                .filter((winLottoInfo) -> winLottoInfo.matchNumberCount == matchNumberCount)
                .toList();
        return filteredWinLottoInfo.getFirst();
    }

    public int getMatchNumberCount() {
        return matchNumberCount;
    }

    public int getPrice() {
        return price;
    }
}
