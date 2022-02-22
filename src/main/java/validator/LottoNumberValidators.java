package validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumberValidators {

    public static int validateAndParseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    public static void validateLottoNumberRange(Integer num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException("1과 45 사이의 숫자를 입력해야 합니다.");
        }
    }

    public static void validateNoDuplicates(List<Integer> nums) {
        Set<Integer> numsSet = new HashSet<>(nums);

        if (nums.size() != numsSet.size()) {
            throw new IllegalArgumentException("당첨 번호는 서로 달라야 합니다.");
        }
    }

    public static void validateNoDuplicateInList(int target, List<Integer> nums) {
        for (int num : nums) {
            if (num == target) {
                throw new IllegalArgumentException("보너스 볼의 값은 당첨 번호와 달라야 합니다.");
            }
        }
    }
}
