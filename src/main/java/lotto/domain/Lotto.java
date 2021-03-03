package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private static final String DELIMITER = ",";
    public static final String NUMBER_COUNT_ERROR = "[ERROR] 6개의 숫자를 입력해주세요";
    public static final String NUMBER_DUPLICATE_ERROR = "[ERROR] 숫자는 중복될 수 없습니다";
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(ArrayList<String> nums) {
        validateCount(nums);
        validateDuplicate(nums);
        this.lottoNumbers = changeToLottoNumber(nums);
    }

    private void validateCount(ArrayList<String> nums) {
        if (nums.size() != LOTTO_NUMBER_LIMIT) {
            throw new IllegalArgumentException(NUMBER_COUNT_ERROR);
        }
    }

    private void validateDuplicate(ArrayList<String> nums) {
        Set<String> numbers = new HashSet<>(nums);
        if (nums.size() != numbers.size()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR);
        }
    }

    private ArrayList<LottoNumber> changeToLottoNumber(ArrayList<String> nums) {
        ArrayList<LottoNumber> changedNums = new ArrayList<>();
        for (String num : nums) {
            changedNums.add(new LottoNumber(num));
        }
        return changedNums;
    }

    public ArrayList<Integer> changeToList() {
        ArrayList<Integer> changedNums = new ArrayList<>();
        for (LottoNumber num : this.lottoNumbers) {
            changedNums.add(num.getNumber());
        }
        return changedNums;
    }

    public static Lotto changeStringToLotto(String numberInput) {
        return new Lotto(Arrays.stream(numberInput.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public boolean isContainNum(int number) {
        return changeToList().contains(number);
    }
}
