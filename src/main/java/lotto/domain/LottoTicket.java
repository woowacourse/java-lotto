package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String DELIMITER = ",";
    private static final String DUPLICATE_ERROR = "로또 번호는 중복이 불가능합니다.";
    private static final String COUNT_ERROR = String.format("로또 개수는 %s여야 합니다.", LOTTO_NUMBER_COUNT);

    private final List<LottoNumber> value;

    private LottoTicket() {
        this.value = generateRandomLottoNumbers();
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        return LottoNumberGenerator.getShuffledNumbers(LOTTO_NUMBER_COUNT);
    }

    public static LottoTicket generateRandom() {
        return new LottoTicket();
    }

    public LottoTicket(String lottoNumbers) {
        String[] parsedLottoNumbers = lottoNumbers.split(DELIMITER);
        validateDuplicateCount(parsedLottoNumbers);
        validateLottoCount(parsedLottoNumbers);
        this.value = generateLottoNumbers(parsedLottoNumbers);
    }

    private void validateDuplicateCount(String[] parsedLottoNumbers) {
        int distinctCount = calculateDistinctCount(parsedLottoNumbers);

        if (parsedLottoNumbers.length != distinctCount) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private int calculateDistinctCount(String[] parsedLottoNumbers) {
        return (int) Arrays.stream(parsedLottoNumbers).distinct().count();
    }

    private void validateLottoCount(String[] parsedLottoNumbers) {
        if (parsedLottoNumbers.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR);
        }
    }

    private List<LottoNumber> generateLottoNumbers(String[] parsedLottoNumbers) {
        return Arrays.stream(parsedLottoNumbers)
                .map(Integer::parseInt)
                .map(LottoNumber::valueOf)
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
    public String toString() {
        return "LottoNumbers{" +
                "lottoNumbers=" + value +
                '}';
    }
}
