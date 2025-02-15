package lotto.controller;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoNumber;

public record LottoDto(List<Integer> numbers) {

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(sortAsc(lotto));
    }

    private static List<Integer> sortAsc(Lotto lotto) {
        return lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .toList();
    }
}
