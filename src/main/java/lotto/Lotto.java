package lotto;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Lotto {
    private static final String NUMBER_COUNT_ERROR = "[ERROR] 6개의 숫자를 입력해주세요";
    private static final String NUMBER_DUPLICATE_ERROR = "[ERROR] 숫자는 중복될 수 없습니다";
    private static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요";
    ArrayList<Integer> lotto;

    public Lotto(ArrayList<Integer> nums) {
        validateCount(nums);
        validateDuplicate(nums);
        validateNumsRange(nums);
        this.lotto = nums;
    }

    public ArrayList<Integer> getLotto() {
        return this.lotto;
    }

    private void validateCount(ArrayList<Integer> nums) {
        if (nums.size() != 6)
            throw new IllegalArgumentException(NUMBER_COUNT_ERROR);
    }

    private void validateDuplicate(ArrayList<Integer> nums) {
        Set<Integer> numbers = new HashSet<>();
        numbers.addAll(nums);
        if (nums.size() != numbers.size())
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR);
    }

    private void validateNumsRange(ArrayList<Integer> nums) {
        for (int i = 0; i < nums.size(); i++)
            validateNumRange(nums.get(i));
    }

    private void validateNumRange(int number) {
        if (number < 1 || number > 45)
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
    }

    public boolean isContainNum(int number) {
        return lotto.contains(number);
    }

    public int howManyWins(ArrayList<Integer> winningLotto) {
        ArrayList<Integer> wins = new ArrayList<>(lotto);
        wins.retainAll(winningLotto);
        return wins.size();
    }
}
