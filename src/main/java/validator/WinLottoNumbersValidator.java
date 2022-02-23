package validator;

import java.util.HashSet;
import java.util.List;

public class WinLottoNumbersValidator {
    public static void validate(List<Integer> nums) {
        isNotDuplicated(nums);
    }

    public static void validateBonus(List<Integer> nums, int bonus) {
        isBonusNumberNotDuplicated(nums, bonus);
    }

    private static void isBonusNumberNotDuplicated(List<Integer> nums, int bonus) {
        if (nums.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 중복될 수 없습니다.");
        }
    }

    private static void isNotDuplicated(List<Integer> nums) {
        HashSet<Integer> compareNums = new HashSet<>(nums);
        if (compareNums.size() != nums.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }


}