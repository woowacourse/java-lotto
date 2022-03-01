package lotto.strategy;

import java.util.List;
import lotto.domain.ChoiceNumber;

public interface LottoBuyStrategy {
    List<ChoiceNumber> buyLotto(int lottoCount);
}
