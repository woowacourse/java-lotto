package lotto.domain.lottoticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;

public class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int FROM_INDEX = 0;

    private static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final String DUPLICATE_ERROR = "로또 번호는 중복이 불가능합니다.";
    private static final String COUNT_ERROR = "로또 개수는 " + LOTTO_NUMBER_COUNT + "여야 합니다.";

    private final List<LottoNumber> value;

    public LottoTicket() {
        this.value = generateRandomLottoNumbers();
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        final List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumbersCache.cache);
        Collections.shuffle(lottoNumbers);
        return Collections.unmodifiableList(lottoNumbers.subList(FROM_INDEX, LOTTO_NUMBER_COUNT));
    }

    public LottoTicket(String lottoNumbers) {
        String[] parsedLottoNumbers = reduceBlank(lottoNumbers).split(DELIMITER);
        validateDuplicateCount(parsedLottoNumbers);
        validateLottoCount(parsedLottoNumbers);
        this.value = generateLottoNumbers(parsedLottoNumbers);
    }

    private String reduceBlank(String lottoNumbers) {
        return lottoNumbers.replaceAll(BLANK, EMPTY);
    }

    private void validateDuplicateCount(String[] parsedLottoNumbers) {
        long distinctCount = calDistinctCountFromArray(parsedLottoNumbers);

        if (parsedLottoNumbers.length != distinctCount) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private long calDistinctCountFromArray(String[] parsedLottoNumbers) {
        return Arrays.stream(parsedLottoNumbers).distinct().count();
    }

    private void validateLottoCount(String[] parsedLottoNumbers) {
        if (parsedLottoNumbers.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR);
        }
    }

    private List<LottoNumber> generateLottoNumbers(String[] parsedLottoNumbers) {
        return Arrays.stream(parsedLottoNumbers)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isContains(LottoNumber otherLottoNumber) {
        return value.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(otherLottoNumber));
    }

    public int calculateSameCount(LottoTicket otherLottoNumbers) {
        return otherLottoNumbers.compareLottoNumbers(value);
    }

    private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(this.value::contains)
                .count();
    }

    public List<LottoNumber> getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LottoNumbers{" +
                "lottoNumbers=" + value +
                '}';
    }
}
