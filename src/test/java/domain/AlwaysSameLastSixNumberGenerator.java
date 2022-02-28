package domain;

import java.util.Arrays;
import java.util.List;
import util.LottoNumberGenerator;

class AlwaysSameLastSixNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        return Arrays.asList(40, 41, 42, 43, 44, 45);
    }
}

