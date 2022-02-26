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
    private static final String DUPLICATE_ERROR = "로또 개수는 중복이 불가능합니다.";
    private static final String COUNT_ERROR = "로또 개수는 " + LOTTO_COUNT + "개로 제한됩니다.";

    private static final List<LottoNumber> candidateLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            candidateLottoNumbers.add(LottoNumber.of(i));
        }
    }

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(String input) {
        String[] stringArr = reduceBlank(input).split(DELIMITER);
        validateDuplicateCount(stringArr);
        validateLottoCount(stringArr);
        this.lottoNumbers = convertStringArrToIntegerList(stringArr);
    }

    public LottoNumbers() {
        this.lottoNumbers = generateRandomLottoNumbers();
    }

    public boolean isContain(LottoNumber otherLottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(otherLottoNumber));
    }

    public int calculateSameCount(LottoNumbers otherLottoNumbers) {
        return otherLottoNumbers.compareLottoNumbers(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private String reduceBlank(String input) {
        return input.replaceAll(BLANK, "");
    }

    private void validateDuplicateCount(String[] arr) {
        long distinctCount = calDistinctCountFromArray(arr);

        if (arr.length != distinctCount) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private long calDistinctCountFromArray(String[] arr) {
        return Arrays.stream(arr).distinct().count();
    }

    private void validateLottoCount(String[] array) {
        if (array.length != LOTTO_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR);
        }
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(candidateLottoNumbers);
        return new ArrayList<>(candidateLottoNumbers.subList(0, 6));
    }

    private List<LottoNumber> convertStringArrToIntegerList(String[] array) {
        return Arrays.stream(array)
                .map(string -> LottoNumber.of(Integer.parseInt(string)))
                .collect(Collectors.toList());
    }

    private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(this.lottoNumbers::contains)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumbers)) {
            return false;
        }

        LottoNumbers that = (LottoNumbers) o;

        return lottoNumbers.containsAll(that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }
}
