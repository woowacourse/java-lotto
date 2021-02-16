package lotto;

import java.util.ArrayList;
import java.util.Collections;

public class Lotto {
    ArrayList<Integer> nums = new ArrayList<>();
    ArrayList<Integer> lotto;

    public Lotto() {
        this.lotto = generateLottoNums();
    }

    private ArrayList<Integer> generateLottoNums() {
        for (int i = 1; i <= 45; i++)
            nums.add(i);
        Collections.shuffle(nums);
        ArrayList<Integer> generatedLotto = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            generatedLotto.add(nums.get(i));
        Collections.sort(generatedLotto);
        return generatedLotto;
    }

    public ArrayList<Integer> getLotto() {
        return this.lotto;
    }
}
