package lotto.model.lottomachine;

import lotto.model.Lotto;
import lotto.model.shufflemethod.RandomShuffleMethod;
import lotto.model.shufflemethod.ShuffleMethod;

import java.util.ArrayList;
import java.util.List;

public class AutomaticLottoMachine implements LottoMachine {
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    public static final int MINMUM_LOTTO_NUMBERS = 1;

    private ShuffleMethod shuffleMethod;
    private List<Integer> all45Numbers;

    public AutomaticLottoMachine() {
        this(new RandomShuffleMethod());
    }

    public AutomaticLottoMachine(ShuffleMethod shuffleMethod) {
        this.shuffleMethod = shuffleMethod;
        this.all45Numbers = create45Numbers();
    }

    private List<Integer> create45Numbers() {
        List<Integer> all45Numbers = new ArrayList<>();
        for (int i = MINMUM_LOTTO_NUMBERS; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            all45Numbers.add(i);
        }
        return all45Numbers;
    }

    @Override
    public Lotto generateLotto() {
        String shuffledLottoNumbers = shuffleMethod.shuffle(this.all45Numbers);
        return new Lotto(shuffledLottoNumbers);
    }
}
