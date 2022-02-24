package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String DELIMITER = ",";
    private static final String BLANK = " ";
    static final String DUPLICATE_ERROR = "로또 개수는 중복이 불가능합니다.";
    static final String COUNT_ERROR = "로또 개수는 " + LOTTO_COUNT + "여야 합니다.";

    private static final List<LottoNumber> candidateLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            candidateLottoNumbers.add(new LottoNumber(Integer.toString(i)));
        }
    }

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(String input) {
        String[] stringArr = reduceBlank(input).split(DELIMITER);
        validateDuplicate(stringArr);
        validateLottoCount(stringArr);
        this.lottoNumbers = convertIntArrToIntegerList(stringArr);
    }

    private void validateLottoCount(String[] array) {
        if (array.length != LOTTO_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR);
        }
    }

    public LottoNumbers() {
        this.lottoNumbers = generateRandomLottoNumbers();
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(candidateLottoNumbers);
        return new ArrayList<>(candidateLottoNumbers.subList(0, 6));
    }

    private String reduceBlank(String input) {
        return input.replaceAll(BLANK, "");
    }

    private void validateDuplicate(String[] arr) {
        long distinctCount = calDistinctCountFromArray(arr);

        if (arr.length != distinctCount) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
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

    public boolean isContain(LottoNumber otherLottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(otherLottoNumber));
    }

    public int calculateSameCount(LottoNumbers otherLottoNumbers) {
        return otherLottoNumbers.compareLottoNumbers(lottoNumbers);
    }

    private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(this.lottoNumbers::contains)
                .count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
