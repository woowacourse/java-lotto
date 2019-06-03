package lotto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomaticLottoMachine implements LottoMachine{

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MAX_NUM = 45;
    private static final int MIN_NUM = 1;

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
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            numbers.add(generateRandomLottoNumberIndex());
        }
        return new ArrayList<>(numbers);
    }

    private static int generateRandomLottoNumberIndex() {
        return (int)(Math.random() * (MAX_NUM) + MIN_NUM);
    }

}
