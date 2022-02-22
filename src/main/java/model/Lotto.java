package model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = RandomLottoNumbersGenerator.pickSixNumbers();
    }
}
