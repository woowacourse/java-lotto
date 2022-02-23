package validator;

import static utils.Messages.*;

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