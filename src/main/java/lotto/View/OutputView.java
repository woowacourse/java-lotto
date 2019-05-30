package lotto.View;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;

import java.util.List;

public class OutputView {

    public static void printCountOfLotto(Money money) {
        System.out.println(money.countOfLotto() + "개를 구매 했습니다.");
    }

    public static void printLotto(List<LottoNumber> lottoNumbers) {
        System.out.println(lottoNumbers.toString());
    }

    public static void printUserLottos(List<Lotto> userLottos) {
        userLottos.forEach(lotto -> printLotto(lotto.getLottoNumbers()));
    }

}
