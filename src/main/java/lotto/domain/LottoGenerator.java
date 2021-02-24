package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;

public class LottoGenerator {
    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final ArrayList<String> nums = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            nums.add(Integer.toString(i));
        }
    }

    public ArrayList<String> generateLottoNums() {
        Collections.shuffle(nums);
        ArrayList<String> generatedLotto = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_LIMIT; i++) {
            generatedLotto.add(nums.get(i));
        }
        Collections.sort(generatedLotto);
        return generatedLotto;
    }
}
