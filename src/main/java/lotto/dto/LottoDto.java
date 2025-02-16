package lotto.dto;


import java.util.List;
import lotto.domain.Lotto;

public record LottoDto(List<Integer> numbers) {
    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }
}