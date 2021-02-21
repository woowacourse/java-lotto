package lotto.domain.lotto;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;

public class LottoNumbers {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateDuplicate(lottoNumbers);
        validateLottoNumberCount(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumbers valueOf(String lottoNumbers) {
        return new LottoNumbers(
                getSplitLottoNumber(lottoNumbers).stream()
                        .map(lottoNumber -> new LottoNumber(new Number(lottoNumber.trim())))
                        .collect(toList())
        );
    }

    private static List<String> getSplitLottoNumber(String lottoNumber) {
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
        return (int) this.lottoNumbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    //todo changeMethodName
    public List<Integer> toIntegerList() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValueAsInt)
                .collect(toList());
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

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(joining("-"));
    }
}