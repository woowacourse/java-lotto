package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;
import lottogame.utils.InvalidLottoSizeException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_NUMBER_VOLUME = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = sort(lottoNumbers);
    }

    private void validate(List<LottoNumber> lottoNumber) {
        invalidLottoSize(lottoNumber);
        duplicateLottoNumbers(lottoNumber);
    }

    private void invalidLottoSize(List<LottoNumber> lottoNumber) {
        if (lottoNumber.size() != LOTTO_NUMBER_VOLUME) {
            throw new InvalidLottoSizeException(LOTTO_NUMBER_VOLUME);
        }
    }

    private void duplicateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream()
                .map(lottoNumber -> lottoNumber.getNumber())
                .distinct()
                .count() != lottoNumbers.size()) {
            throw new DuplicateLottoNumberException();
        }
    }

    private List<LottoNumber> sort(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());
    }

    public List<LottoNumber> values() {
        return lottoNumbers.stream()
                .map(lottoNumber -> lottoNumber.values())
                .collect(Collectors.toList());
    }

    public int matchNumberCount(WinningLotto winningLotto) {
        return winningLotto.matchNumbers(lottoNumbers);
    }

    public int matchNumberCount(List<LottoNumber> numbers) {
        return (int) lottoNumbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean matchBonusBall(LottoNumber bonusBall) {
        return lottoNumbers.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
