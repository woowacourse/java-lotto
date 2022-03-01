package lotto.strategy;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.ChoiceNumber;

public class AutoBuy implements LottoBuyStrategy {

    @Override
    public List<ChoiceNumber> buyLotto(int count) {
        ArrayList<ChoiceNumber> lotto = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lotto.add((new ChoiceNumber()));
        }
        return lotto;
    }
}
