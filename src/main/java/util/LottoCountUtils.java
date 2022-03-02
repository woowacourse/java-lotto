package util;

import static validator.NumberValidators.validateManualLottosCount;
import static validator.NumberValidators.validateTotalLottoPriceUnit;

import domain.Lotto;

public class LottoCountUtils {

    public static int getValidTotalCount(int totalLottoPrice) {
        validateTotalLottoPriceUnit(totalLottoPrice);
        return totalLottoPrice / Lotto.PRICE;
    }

    public static int getValidAutosCount(int manualsCount, int totalCount) {
        validateManualLottosCount(manualsCount, totalCount);
        return totalCount - manualsCount;
    }
}
