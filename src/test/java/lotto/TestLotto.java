package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;

import java.util.Arrays;
import java.util.List;

public class TestLotto {
    public static List<Numbers> lottos = Arrays.asList(new Numbers("1,2,3,4,5,6"), new Numbers("2,3,4,5,6,7"));

    public static Winning winning = Winning.of(Lotto.of(new Numbers("1,2,3,4,5,6")), 7);

    private static PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(2000), 2);

    private static Lottos lottos1 = Lottos.of(purchaseCount, Arrays.asList(new Numbers("1,2,3,4,5,6"), new Numbers("2,3,4,5,6,7")));

    public static LottoResult lottoResult = LottoResult.of(winning, lottos1);
}
