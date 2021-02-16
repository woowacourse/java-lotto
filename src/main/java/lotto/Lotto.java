package lotto;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Lotto {
    private static final String NUMBER_COUNT_ERROR = "[ERROR] 6개의 숫자를 입력해주세요";
    private static final String NUMBER_DUPLICATE_ERROR = "[ERROR] 숫자는 중복될 수 없습니다";
    private static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요";
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
