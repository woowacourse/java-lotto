package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class RandomLottoNumberStrategy implements LottoNumberStrategy {

    private final static List<LottoNumber> lottoNumbers = LottoNumber.getNumbers();

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(Collectors.toList());
    }
}
