package domain.dto;

import domain.Lotto;
import java.util.List;

public record GetLottoDto(
        List<Integer> numbers
) {
    public GetLottoDto(Lotto lotto) {
        this(lotto.getNumbers());
    }
}
