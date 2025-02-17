package lotto.dto;


import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public record LottoDto(List<LottoNumber> numbers) {
    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }
}