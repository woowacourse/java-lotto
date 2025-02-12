package lotto;

import java.util.List;

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
