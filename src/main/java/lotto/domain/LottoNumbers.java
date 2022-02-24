package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    public static final String DELIMITER = ",";
    public static final String BLANK = " ";
    private static final List<LottoNumber> candidateLottoNumbers = new ArrayList<>();

    static {
        for (int i = 0; i < 45; i++) {
            candidateLottoNumbers.add(new LottoNumber(Integer.toString(i + 1)));
        }
    }

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(String input) {
        String[] stringArr = reduceBlank(input).split(DELIMITER);
        validateDuplicate(stringArr);
        this.lottoNumbers = convertIntArrToIntegerList(stringArr);
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
