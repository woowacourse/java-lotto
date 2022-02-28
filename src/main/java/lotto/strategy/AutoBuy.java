package lotto.strategy;

import static lotto.domain.Money.UNIT_AMOUNT;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.ChoiceNumber;
import lotto.domain.Money;

public class AutoBuy implements LottoBuyStrategy {

    @Override
    public List<ChoiceNumber> buyLotto(Money money) {
        ArrayList<ChoiceNumber> lotto = new ArrayList<>();
        int bound = money.getAmount() / UNIT_AMOUNT;
        for (int i = 0; i < bound; i++) {
            lotto.add((new ChoiceNumber()));
        }
        return lotto;
    }
}
