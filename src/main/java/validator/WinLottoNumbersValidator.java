package validator;

import java.util.HashSet;
import java.util.List;

public class WinLottoNumbersValidator {

    public static final String BONUS_DUPLICATED_ERROR_MESSAGE = "보너스 번호는 로또 번호와 중복될 수 없습니다.";
    public static final String LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다.";

    public static void validate(List<Integer> nums) {
        isNotDuplicated(nums);
    }

    public static void validateBonus(List<Integer> nums, int bonus) {
        isNumNotDuplicated(nums, bonus);
    }

    private static void isNumNotDuplicated(List<Integer> nums, int bonus) {
        if (nums.contains(bonus)) {
            throw new IllegalArgumentException(BONUS_DUPLICATED_ERROR_MESSAGE);
        }
    }

    private static void isNotDuplicated(List<Integer> nums) {
        HashSet<Integer> compareNums = new HashSet<>(nums);
        if (compareNums.size() != nums.size()) {
            throw new IllegalArgumentException(LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE);
        }
    }
}