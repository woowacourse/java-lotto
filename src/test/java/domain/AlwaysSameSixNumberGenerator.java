package domain;

import java.util.Arrays;
import java.util.List;
import util.LottoNumberGenerator;

class AlwaysSameSixNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }
}
