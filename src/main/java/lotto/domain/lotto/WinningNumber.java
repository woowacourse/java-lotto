package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;

public class WinningNumber {

    private LottoNumbers lottoNumbers;

    public WinningNumber(String value) {
        List<String> parsedValue = Arrays.asList(value.split(",", -1));
        lottoNumbers = new LottoNumbers(
            parsedValue.stream()
                .map(v -> new LottoNumber(new Number(v.trim())))
                .collect(Collectors.toList())
        );
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningNumber that = (WinningNumber) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
