package lotto.domain;

import lotto.utils.LottoGenerator;

import java.util.Arrays;

public class FixedGenerator implements LottoGenerator {

    @Override
    public Lotto generate() {
        return new Lotto(Arrays.asList(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ));
    }
}
