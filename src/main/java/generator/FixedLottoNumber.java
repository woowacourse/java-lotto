package generator;

import java.util.List;

public class FixedLottoNumber implements RandomGenerator {
    private final List<Integer> fixedNumbers;

    public FixedLottoNumber(List<Integer> fixedNumbers) {
        this.fixedNumbers = fixedNumbers;
    }

    @Override
    public List<Integer> generateNumbers() {
        return fixedNumbers;
    }
}
