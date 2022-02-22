package lotto.domain;

import java.util.Arrays;

public class LottoNumbers {

    public static final String DELIMITER = ",";
    public static final String DUPLICATE_ERROR = "중복된 번호는 사용할 수 없습니다.";

    public LottoNumbers(String input) {
        validateDuplicate(input);
    }

    private void validateDuplicate(String input) {
        String[] splitArr = input.split(DELIMITER);
        long distinctCount = calDistinctCountFromArray(convertStringArrToIntArr(splitArr));

        if (splitArr.length != distinctCount) {
            throw new IllegalArgumentException();
        }
    }

    private long calDistinctCountFromArray(int[] arr) {
        return Arrays.stream(arr).distinct().count();
    }

    private int[] convertStringArrToIntArr(String[] stringArr) {
        int lenOfArr = stringArr.length;
        int[] intArr = new int[lenOfArr];
        for (int i = 0; i < lenOfArr; i++) {
            intArr[i] = Integer.parseInt(stringArr[i]);
        }
        return intArr;
    }

}
