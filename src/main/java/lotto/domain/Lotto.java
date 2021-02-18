package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Lotto {

    private static final String NUMBER_COUNT_ERROR = "[ERROR] 6개의 숫자를 입력해주세요";
    private static final String NUMBER_DUPLICATE_ERROR = "[ERROR] 숫자는 중복될 수 없습니다";
    private static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요";
    private static final int MAXIMUM_NUMBER = 45;
    private static final int MINIMUM_NUMBER = 1;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final ArrayList<Integer> lotto;

    public Lotto(ArrayList<Integer> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
        validateNumsRange(numbers);
        this.lotto = numbers;
    }

    public ArrayList<Integer> getLotto() {
        return this.lotto;
    }

    private void validateCount(ArrayList<Integer> nums) {
        if (nums.size() != LOTTO_NUMBER_LIMIT) {
            throw new IllegalArgumentException(NUMBER_COUNT_ERROR);
        }
    }

    private void validateDuplicate(ArrayList<Integer> nums) {
        Set<Integer> numbers = new HashSet<>(nums);
        if (nums.size() != numbers.size()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR);
        }
    }

    private void validateNumsRange(ArrayList<Integer> nums) {
        for (Integer num : nums) {
            validateNumRange(num);
        }
    }

    private void validateNumRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    public boolean isContainNum(int number) {
        return lotto.contains(number);
    }
}
