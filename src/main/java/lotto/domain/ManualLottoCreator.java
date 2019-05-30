package lotto.domain;

import java.util.List;
import java.util.ArrayList;

public class ManualLottoCreator implements LottoCreator {

    private final String[] numbers;

    public ManualLottoCreator(String[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto create() {
        List<Number> lotto = new ArrayList();
        for (String number : numbers) {
            lotto.add(Number.valueOf(Integer.parseInt(number)));
        }

        return new Lotto(lotto);
    }
}
