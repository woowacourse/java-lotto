package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLottoMaker extends LottoMaker {
    private Random RANDOM = new Random();

    public RandomLottoMaker(LottoRule rule) {
        super(rule);
    }

    private int makeInt() {
        int i;
        do {
            i = RANDOM.nextInt(rule.getNumberEnd() + 1);
        } while (!rule.isValidNumberRange(i));
        return i;
    }

    private List<Integer> makeList() {
        List<Integer> temp = new ArrayList<>();
        do {
            temp.add(makeInt());
        } while (!rule.isValidList(temp));
        return rule.numbersArrange(temp);
    }

    @Override
    public Lotto getLotto() {
        return new Lotto(makeList(), rule);
    }
}
