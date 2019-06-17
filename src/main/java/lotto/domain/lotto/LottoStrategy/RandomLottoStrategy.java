package lotto.domain.lotto.LottoStrategy;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumberGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoStrategy implements LottoStrategy {
    private static final List<Integer> indexes = new ArrayList<>();

    static {
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
            indexes.add(i);
        }
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(indexes);
        return Collections.unmodifiableList(
                indexes.subList(0, LottoNumberGroup.LOTTO_SIZE)
        );
    }
}
