package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    public static final int SINGLE_LOTTO_PRICE = 1000;

    static final String ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS = String.format("%d개의 숫자를 골라주세요.", LOTTO_SIZE);

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(Set<Integer> lottoNumberValues) {
        validateSize(lottoNumberValues);
        this.lottoNumbers = createSetOfLottoNumberBySetOfInteger(lottoNumberValues);
    }

    private Set<LottoNumber> createSetOfLottoNumberBySetOfInteger(Set<Integer> lottoNumberValues) {
        return lottoNumberValues.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());
    }

    private void validateSize(Set<Integer> lottoNumberValues) {
        if (lottoNumberValues.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS);
        }
    }

    public int getSameNumberCount(Lotto otherLotto) {
        List<LottoNumber> intersections = new ArrayList<>(lottoNumbers);
        intersections.retainAll(otherLotto.lottoNumbers);

        return intersections.size();
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getSortedLottoNumbers() {
        return lottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) object;
        return lottoNumbers.equals(lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}
