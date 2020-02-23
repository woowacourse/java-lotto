package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_MESSAGE_LOTTO_SIZE = "6개의 숫자가 아닙니다.";
    public static final String ERROR_MESSAGE_NULL_POINT_LOTTO = "입력값이 비어있습니다.";

    private final List<LottoNo> lottoNumbers;

    public Lotto(List<LottoNo> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private void validate(List<LottoNo> lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_LOTTO);
        }
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_SIZE);
        }
    }

    public boolean contains(LottoNo lottoNo) {
        return lottoNumbers.contains(lottoNo);
    }

    public int compare(Lotto winLotto) {
        return (int) lottoNumbers.stream()
                .filter(x -> winLotto.contains(x))
                .count();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
