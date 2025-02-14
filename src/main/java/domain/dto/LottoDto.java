package domain.dto;

import domain.Lotto;
import java.util.List;

public record LottoDto(
        List<Integer> numbers
) {
    public LottoDto(Lotto lotto) {
        this(lotto.getNumbers());
    }
}
