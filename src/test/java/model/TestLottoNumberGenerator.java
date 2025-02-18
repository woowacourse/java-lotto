package model;

import java.util.List;

class TestLottoNumberGenerator implements view.util.LottoNumberGenerator {
    private List<Integer> numbers;
    private int index = 0;

    public TestLottoNumberGenerator(List<Integer> testNumbers) {
        this.numbers = testNumbers;
    }

    @Override
    public int generate() {
        return numbers.get(index++);
    }
}
