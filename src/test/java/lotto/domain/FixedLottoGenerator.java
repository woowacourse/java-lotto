package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class FixedLottoGenerator extends LottoGenerator {

    @Override
    public List<Integer> generateLotto() {
        List<Integer> lotto = new ArrayList<>();
        for (int i = 1; i <7; i++) {
            lotto.add(i);
        }
        return lotto; // 1 2 3 4 5 6
    }
}
