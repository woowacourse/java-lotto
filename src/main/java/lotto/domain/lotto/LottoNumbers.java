package lotto.domain.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;

public class LottoNumbers {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String LOTTO_NUMBER_SEPARATOR = ",";
    private static final RandomLottoGenerator RANDOM_LOTTO_GENERATOR
        = RandomLottoGenerator.getInstance();

    private final Set<LottoNumber> lottoNumbers;
    private final LottoNumbersType type;

    private LottoNumbers(Set<LottoNumber> lottoNumbers, LottoNumbersType type) {
        validateLottoNumberCount(lottoNumbers);
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        this.type = type;
    }

    public static LottoNumbers valueOf() {
        return new LottoNumbers(RANDOM_LOTTO_GENERATOR.nextLottoNumbers(LOTTO_NUMBER_COUNT),
            LottoNumbersType.AUTO);
    }

    public static LottoNumbers valueOf(String unparsedLottoNumbers) {
        List<String> parsedLottoNumbers = splitLottoNumber(unparsedLottoNumbers);
        validateDuplication(parsedLottoNumbers);
        return valueOf(parsedLottoNumbers);
    }

    private static LottoNumbers valueOf(List<String> parsedNumbers) {
        return parsedNumbers.stream()
            .map(String::trim)
            .map(LottoNumber::valueOf)
            .collect(collectingAndThen(toSet(),
                lottoNumbers1 -> new LottoNumbers(lottoNumbers1, LottoNumbersType.MANUAL)));
    }

    private static List<String> splitLottoNumber(String lottoNumber) {
        return Arrays.stream(lottoNumber.split(LOTTO_NUMBER_SEPARATOR, -1))
            .collect(Collectors.toList());
    }

    private static void validateDuplication(List<String> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 넘버에 중복이 있습니다.");
        }
    }

    private static void validateLottoNumberCount(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 넘버가 6개가 아닙니다.");
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int match(LottoNumbers lottoNumbersToMatch) {
        return (int) lottoNumbers.stream()
            .filter(lottoNumbersToMatch::contains)
            .count();
    }

    public List<Integer> unwrap() {
        return lottoNumbers.stream()
            .map(LottoNumber::unwrap)
            .sorted()
            .collect(toList());
    }

    public LottoNumbersType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}