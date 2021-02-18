package lotto.domain.lotto;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;

public class LottoNumbers {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateDuplicate(lottoNumbers);
        validateLottoNumberCount(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumbers valueOf(String unparsedLottoNumbers) {
        return getLottoNumbersFromStringList(splitLottoNumber(unparsedLottoNumbers));
    }

    private static LottoNumbers getLottoNumbersFromStringList(List<String> lottoNumbers) {
        return new LottoNumbers(lottoNumbers.stream()
            .map(lottoNumber -> LottoNumber.valueOf(lottoNumber.trim()))
            .collect(Collectors.toList()));
    }

    private static List<String> splitLottoNumber(String lottoNumber) {
        return Arrays.asList(lottoNumber.split(",", -1));
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 넘버에 중복이 있습니다.");
        }
    }

    private void validateLottoNumberCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 넘버가 6개가 아닙니다.");
        }
    }

    public int getMatchCount(LottoNumbers lottoNumbers) {
        return (int) this.lottoNumbers.stream().filter(lottoNumbers::contains).count();
    }

    public List<Integer> toIntegerList() {
        return lottoNumbers.stream().map(LottoNumber::toInt).collect(toList());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
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