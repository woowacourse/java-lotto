package lotto.domain;

import lotto.utils.LottoGenerator;

import java.util.Arrays;

public class FixedGenerator implements LottoGenerator {

    @Override
    public Lotto generate() {
        return new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
    }

    public Lotto generateWinningLottoNumber() {
        return new Lotto(Arrays.asList(
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(9)
        ));
    }
}
