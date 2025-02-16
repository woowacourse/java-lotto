package util.random;

import java.util.List;

public class TestRandomUtil implements RandomUtil {

    private final List<Integer> fixedResults;

    public TestRandomUtil(List<Integer> fixedResults) {
        this.fixedResults = fixedResults;
    }

    @Override
    public List<Integer> generateRandomNumbers(int minNumber, int maxNumber, int count) {
        return fixedResults;
    }
}
