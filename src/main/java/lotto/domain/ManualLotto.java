package lotto.domain;

import java.util.List;
import java.util.Objects;

public class ManualLotto {

    private final List<Integer> lottoNumbers;

    public ManualLotto(List<Integer> inputNumbers) {
        this.lottoNumbers = inputNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManualLotto that = (ManualLotto) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
