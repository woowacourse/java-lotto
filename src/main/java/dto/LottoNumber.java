package dto;

import domain.Lotto;
import java.util.List;

public record LottoNumber(
        List<Integer> values
) {
    public static LottoNumber of(final Lotto lotto) {
        return new LottoNumber(lotto.getNumbers());
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
