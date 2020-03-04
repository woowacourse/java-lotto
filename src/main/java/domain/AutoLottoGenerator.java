package domain;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        List<Integer> lottoNumbers = LottoNumber.getAllLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return new Lotto(
                lottoNumbers.stream()
                        .limit(LOTTO_SIZE)
                        .map(LottoNumber::newLottoNumber)
                        .collect(toList())
        );
    }
}
