package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;

public class LottoGenerator {
    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final ArrayList<Integer> nums = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++)
            nums.add(i);
    }

    public ArrayList<Integer> generateLottoNums() {
        Collections.shuffle(nums);
        ArrayList<Integer> generatedLotto = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_LIMIT; i++)
            generatedLotto.add(nums.get(i));
        Collections.sort(generatedLotto);
        return generatedLotto;
    }
}
