package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumberRepository {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();
    private static final int MINIMUM_RANGE = 0;
    private static final int MAXIMUM_RANGE = 6;

    static {
        IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .mapToObj(LottoNumber::new)
                .forEach(lottoNumbers::add);
    }

    public static List<LottoNumber> shuffleLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> splitLottoNumbers = new ArrayList<>(lottoNumbers.subList(MINIMUM_RANGE, MAXIMUM_RANGE));
        return Collections.unmodifiableList(splitLottoNumbers);
    }
}
