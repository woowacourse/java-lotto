package lotto.viewer;

import java.util.List;
import lotto.domain.Lotto;

public class OutputView {
    public void printPurchasedLottos(List<Lotto> lottoBunch) {
        System.out.printf("%d를 구매하였습니다." + System.lineSeparator(), lottoBunch.size());
        for (Lotto lotto : lottoBunch) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }
}
