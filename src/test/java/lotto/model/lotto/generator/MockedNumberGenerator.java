package lotto.model.lotto.generator;

import java.util.List;

public class MockedNumberGenerator implements NumberGenerator {

    private List<Integer> numbersToGenerate;

    public MockedNumberGenerator() {
        this.numbersToGenerate = null;
    }

    public void setNumbersToGenerate(List<Integer> numbersToGenerate) {
        this.numbersToGenerate = numbersToGenerate;
    }

    @Override
    public List<Integer> generate(int min, int max, int size) {
        return this.numbersToGenerate;
    }
}
