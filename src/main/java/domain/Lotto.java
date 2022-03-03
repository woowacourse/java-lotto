package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final String ERROR_MESSAGE_NOT_IN_SIZE = "로또 숫자 갯수는 6개이여야 합니다.";
    private static final String ERROR_MESSAGE_NOT_DUPLICATE = "로또 숫자는 중복일 수 없습니다.";
    private static final String ERROR_MESSAGE_NULL = "값이 null 입니다.";

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers, ERROR_MESSAGE_NULL);
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_SIZE);
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> deDuplicatedNumbers = new HashSet<>(lottoNumbers);

        if (deDuplicatedNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_DUPLICATE);
        }
    }

    public static Lotto fromLotto(List<LottoNumber> lottoNumbers) {
        return new Lotto(IntStream.range(0, LOTTO_NUMBER_SIZE)
            .mapToObj(lottoNumbers::get)
            .sorted()
            .collect(Collectors.toList()));
    }

    public int calculateMatchCount(Lotto otherLotto) {
        List<LottoNumber> copiedNumbers = new ArrayList<>(List.copyOf(lottoNumbers));
        copiedNumbers.retainAll(otherLotto.lottoNumbers);

        return copiedNumbers.size();
    }

    public boolean containsNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Lotto))
            return false;
        Lotto lotto = (Lotto)o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
