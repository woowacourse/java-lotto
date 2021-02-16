package lotto;

import java.util.ArrayList;
import java.util.Collections;

public class LottoGenerator {
    ArrayList<Integer> nums = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= 45; i++)
            nums.add(i);
    }

    public ArrayList<Integer> generateLottoNums() {
        Collections.shuffle(nums);
        ArrayList<Integer> generatedLotto = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            generatedLotto.add(nums.get(i));
        Collections.sort(generatedLotto);
        return generatedLotto;
    }
}
