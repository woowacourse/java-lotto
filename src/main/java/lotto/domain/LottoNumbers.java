package lotto.domain;

import java.util.Arrays;

public class LottoNumbers {

    public static final String DELIMITER = ",";
    public static final String 중복된_번호는_사용할_수_없습니다 = "중복된 번호는 사용할 수 없습니다.";

    public LottoNumbers(String input) {
        int[] intArr = convertStringArrToIntArr(input.split(DELIMITER));
        validateDuplicate(intArr);
    }

    private void validateDuplicate(int[] arr) {
        long distinctCount = calDistinctCountFromArray(arr);

        if (arr.length != distinctCount) {
            throw new IllegalArgumentException();
        }
    }

    private long calDistinctCountFromArray(int[] arr) {
        return Arrays.stream(arr).distinct().count();
    }

    private int[] convertStringArrToIntArr(String[] stringArr) {
        int lenOfArr = stringArr.length;
        int[] intArr = new int[lenOfArr];
        try {
            for (int i = 0; i < lenOfArr; i++) {
                intArr[i] = Integer.parseInt(stringArr[i].trim());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return intArr;
    }

}
