package lotto.domain.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lotto.domain.number.LottoNumber;

public class LottoNumbers {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String LOTTO_NUMBER_SEPARATOR = ",";

    private final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<LottoNumber> lottoNumbers) {
        validateLottoNumberCount(lottoNumbers);
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    public static LottoNumbers valueOf(List<LottoNumber> lottoNumbers) {
        validateDuplication(lottoNumbers);
        return new LottoNumbers(new HashSet<>(lottoNumbers));
    }

    public static LottoNumbers valueOf(String unparsedLottoNumbers) {
        return getLottoNumbersFromStringList(splitLottoNumber(unparsedLottoNumbers));
    }

    private static List<String> splitLottoNumber(String lottoNumber) {
        return Arrays.asList(lottoNumber.split(LOTTO_NUMBER_SEPARATOR, -1));
    }

    private static LottoNumbers getLottoNumbersFromStringList(List<String> lottoNumbers) {
        return lottoNumbers.stream()
            .map(lottoNumber -> LottoNumber.valueOf(lottoNumber.trim()))
            .collect(collectingAndThen(toList(), LottoNumbers::valueOf));
    }

    private static void validateDuplication(List<LottoNumber> lottoNumbers) {
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

    public int getMatchCount(LottoNumbers lottoNumbers) {
        return (int) this.lottoNumbers.stream()
            .filter(lottoNumbers::contains)
            .count();
    }

    public List<Integer> unwrap() {
        return lottoNumbers.stream()
            .map(LottoNumber::unwrap)
            .collect(toList());
    }

    public static int getLottoNumberCount() {
        return LOTTO_NUMBER_COUNT;
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