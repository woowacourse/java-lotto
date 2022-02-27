package lotto.domain.strategy;

import java.util.List;
import lotto.domain.ChoiceNumber;
import lotto.domain.Money;

public interface LottoBuyStrategy {
    List<ChoiceNumber> buyLotto(Money money);
}
