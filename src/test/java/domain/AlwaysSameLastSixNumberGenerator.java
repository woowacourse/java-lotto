package domain;

import java.util.Arrays;
import java.util.List;
import util.NumberGenerator;

class AlwaysSameLastSixNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generate() {
        return Arrays.asList(40, 41, 42, 43, 44, 45);
    }
}

