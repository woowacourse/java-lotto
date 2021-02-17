package util;

import domain.lotto.LottoNumber;
import domain.lotto.LottoNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomLottoUtil {

    private RandomLottoUtil() {
    }

    public static LottoNumbers generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        IntStream intStream = new Random().ints(1, 46);
        intStream.distinct().limit(6).sorted()
                .forEach(i -> lottoNumbers.add(new LottoNumber(i)));
        return LottoNumbers.of(lottoNumbers);
    }
}
