package lotto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.model.Lotto.LOTTO_NUMBER_LENGTH;
import static lotto.model.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.model.LottoNumber.MINMUM_LOTTO_NUMBERS;

public class AutomaticLottoMachine implements LottoMachine {

    private final int automaticLottoCount;

    public AutomaticLottoMachine(Money money, int manualLottoCount) {
        this.automaticLottoCount = money.calculateAutomatiLottoCount(manualLottoCount);
    }

    @Override
    public Lottos generateLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < automaticLottoCount; i++) {
            lottos.add(new Lotto(generateRandomLottoNumbers()));
        }
        return new Lottos(lottos);
    }

    private static List<Integer> generateRandomLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_NUMBER_LENGTH) {
            numbers.add(generateRandomLottoNumberIndex());
        }
        return new ArrayList<>(numbers);
    }

    private static int generateRandomLottoNumberIndex() {
        return (int) (Math.random() * (MAXIMUM_LOTTO_NUMBER) + MINMUM_LOTTO_NUMBERS);
    }

}
