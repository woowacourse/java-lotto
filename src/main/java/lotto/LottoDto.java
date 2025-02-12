package lotto;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record LottoDto(Set<Integer> numbers) {

    public static LottoDto of(Lotto lotto) {
        return new LottoDto(sortAsc(lotto));
    }

    private static Set<Integer> sortAsc(Lotto lotto) {
        return lotto.getNumbers()
                .stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
