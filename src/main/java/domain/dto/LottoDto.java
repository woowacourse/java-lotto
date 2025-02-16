package domain.dto;

import domain.Lotto;
import java.util.List;

public record LottoDto(
        List<Integer> numbers
) {
    public LottoDto(final Lotto lotto) {
        this(lotto.getNumbers());
    }
}
