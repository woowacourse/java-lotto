package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        List<Integer> lottoNumbers = new ArrayList<>(AllLottoNumbers.getLottoNumbersKeySet());
        Collections.shuffle(lottoNumbers);
        return new Lotto(
                lottoNumbers.stream()
                        .limit(LOTTO_SIZE)
                        .map(AllLottoNumbers::get)
                        .collect(toList())
        );
    }
}
