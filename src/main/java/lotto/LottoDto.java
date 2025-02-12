package lotto;

import java.util.TreeSet;

public record LottoDto(TreeSet<Integer> numbers) {

    public static LottoDto of(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
