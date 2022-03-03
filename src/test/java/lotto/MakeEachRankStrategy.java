package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.ChoiceNumber;
import lotto.strategy.LottoBuyStrategy;

public class MakeEachRankStrategy implements LottoBuyStrategy {
    /**
     * 당첨번호가 1,2,3,4,5,6이고, 보너스번호가 7일 때 각 랭크별로 하나씩 추가해주는 전략
     */
    @Override
    public List<ChoiceNumber> buyLotto(int Count) {
        List<ChoiceNumber> choiceNumbers = new ArrayList<>();
        choiceNumbers.add(new ChoiceNumber(Arrays.asList(1, 2, 3, 4, 5, 6)));
        choiceNumbers.add(new ChoiceNumber(Arrays.asList(1, 2, 3, 4, 5, 7)));
        choiceNumbers.add(new ChoiceNumber(Arrays.asList(1, 2, 3, 4, 5, 8)));
        choiceNumbers.add(new ChoiceNumber(Arrays.asList(1, 2, 3, 4, 7, 8)));
        choiceNumbers.add(new ChoiceNumber(Arrays.asList(1, 2, 3, 7, 8, 9)));
        choiceNumbers.add(new ChoiceNumber(Arrays.asList(7, 8, 9, 10, 11, 12)));
        return choiceNumbers;
    }
}
