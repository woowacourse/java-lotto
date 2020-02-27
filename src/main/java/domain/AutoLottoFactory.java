package domain;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AutoLottoFactory implements LottoFactory{

    @Override
    public Lotto createOneLotto() {
        List<Integer> keys = AllLottoNumbers.getLottoNumbersKeySet();
        Collections.shuffle(keys);
        return new Lotto(keys.stream()
                .limit(LOTTO_SIZE)
                .map(AllLottoNumbers::get)
                .collect(toList()));
    }
}
