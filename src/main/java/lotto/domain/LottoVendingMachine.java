package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {
    private static final int LOTTO_PRICE = 1000;

    public static List<Lotto> getRandomLottos(int money) {
        List<Lotto> lottos = new ArrayList<>();
        Money lottoBuyingMoney = new Money(money);
        if (!lottoBuyingMoney.isMultipleOf(new Money(LOTTO_PRICE))) {
            throw new InvalidLottoBuyingMoney("로또 금액(" + LOTTO_PRICE + ") 의 배수에 해당하는 돈을 입력하셔야 합니다.");
        }
        for (int i=0; i<lottoBuyingMoney.divideBy(new Money(LOTTO_PRICE)); i++) {
            lottos.add(new Lotto());
        }
        return lottos;
    }

    public static List<Lotto> getCustomLottos(List<List<Integer>> lottoNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> numbers : lottoNumbers) {
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }
}


