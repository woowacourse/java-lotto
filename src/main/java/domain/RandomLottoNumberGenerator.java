package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import constant.LottoConstant;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(LottoConstant.MINIMUM_LOTTO_NUMBER, LottoConstant.MAXIMUM_LOTTO_NUMBER)
            .boxed()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);

        final List<LottoNumber> pickedNumbers = new ArrayList<>();
        for (int i = 0; i < LottoConstant.LOTTO_NUMBER_SIZE; i++) {
            pickedNumbers.add(lottoNumbers.get(i));
        }
        Collections.sort(pickedNumbers);

        return pickedNumbers;
    }
}
