package lotto.controller;

import java.util.List;
import lotto.model.Lotto;

public record LottoDto(List<Integer> numbers) {

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(sortAsc(lotto));
    }

    private static List<Integer> sortAsc(Lotto lotto) {
        return lotto.getNumbers()
                .stream()
                .sorted()
                .toList();
    }
}
