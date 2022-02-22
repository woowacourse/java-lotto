package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    public static final String DELIMITER = ",";

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(String input) {
        String[] stringArr = input.split(DELIMITER);
        validateDuplicate(stringArr);
        this.lottoNumbers = convertIntArrToIntegerList(stringArr);
    }

    private void validateDuplicate(String[] arr) {
        long distinctCount = calDistinctCountFromArray(arr);

        if (arr.length != distinctCount) {
            throw new IllegalArgumentException();
        }
    }

    private long calDistinctCountFromArray(String[] arr) {
        return Arrays.stream(arr).distinct().count();
    }

    private List<LottoNumber> convertIntArrToIntegerList(String[] stringArr) {
        return Arrays.stream(stringArr)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public boolean isContain(int number) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.getNumber() == number);
    }
}
