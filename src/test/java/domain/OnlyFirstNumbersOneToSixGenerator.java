package domain;

import java.util.List;
import util.NumberGenerator;

public class OnlyFirstNumbersOneToSixGenerator implements NumberGenerator {

    private boolean first = true;

    @Override
    public List<Integer> generate() {
        if (first) {
            first = false;
            return List.of(1, 2, 3, 4, 5, 6);
        }
        return List.of(40, 41, 42, 43, 44, 45);
    }
}
