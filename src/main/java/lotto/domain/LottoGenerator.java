package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int MAXIMUM_NUMBER = 45;
    private final List<Integer> nums = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            nums.add(i);
        }
    }

    public ArrayList<String> generateLottoNums() {
        Collections.shuffle(nums);
        ArrayList<String> generatedLotto = new ArrayList<>();
        List<Integer> generatedNumber = nums.subList(0, 6);
        Collections.sort(generatedNumber);
        for (int num : generatedNumber) {
            generatedLotto.add(Integer.toString(num));
        }
        return generatedLotto;
    }
}
