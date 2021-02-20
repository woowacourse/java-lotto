package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final List<Integer> nums = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            nums.add(i);
        }
    }

    public List<Integer> generateLottoNums() {
        Collections.shuffle(nums);
        List<Integer> generatedLotto = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_LIMIT; i++) {
            generatedLotto.add(nums.get(i));
        }
        Collections.sort(generatedLotto);
        return generatedLotto;
    }
}
