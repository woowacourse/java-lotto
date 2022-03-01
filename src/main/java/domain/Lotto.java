package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    public static final int LOTTO_NUMBER_START = 1;
    public static final int LOTTO_NUMBER_END = 45;
    public static final int SINGLE_LOTTO_PRICE = 1000;

    public static final List<Integer> LOTTO_NUMBERS;

    static final String ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS = String.format("%d개의 숫자를 골라주세요.", LOTTO_SIZE);

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LOTTO_NUMBER_START, LOTTO_NUMBER_END)
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);

        this.lottoNumbers = Collections.unmodifiableSet(lottoNumbers);
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS);
        }
    }

    public int getSameNumberCount(Lotto anotherLotto) {
        return (int) lottoNumbers.stream()
                .filter(anotherLotto::containsLottoNumber)
                .count();
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}
