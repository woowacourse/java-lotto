package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private static final int MINIMUM_VALUE = 1;
    private static final int MAXIMUM_VALUE = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(MINIMUM_VALUE, MAXIMUM_VALUE)
            .boxed()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);

        final List<LottoNumber> pickedNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
            pickedNumbers.add(lottoNumbers.get(i));
        }
        Collections.sort(pickedNumbers);

        return pickedNumbers;
    }
}
