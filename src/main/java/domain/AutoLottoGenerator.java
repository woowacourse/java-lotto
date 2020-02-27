package domain;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AutoLottoGenerator implements LottoGenerator{

    @Override
    public Lotto generateLotto() {
        List<LottoNumber> values = (List<LottoNumber>) allLottoNumbers.values();
        Collections.shuffle(values);
        return new Lotto(values.stream()
                .limit(LOTTO_SIZE)
                .collect(toList()));
    }
}
