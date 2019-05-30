package lotto.creator;

import lotto.domain.Lotto;
import lotto.domain.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoCreator implements LottoCreator {
    private final int lottosSize;

    public AutoLottoCreator(final int lottosSize) {
        this.lottosSize = lottosSize;
    }

    @Override
    public List<Lotto> create() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottosSize; i++ ) {
            lottos.add(createLotto());
        }
        return lottos;
    }

    private Lotto createLotto() {
        List<Number> numbers = Number.getNumberList();
        Collections.shuffle(numbers);
        return new Lotto(new ArrayList<>(numbers.subList(0, 6)));
    }

}
