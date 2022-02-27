package lotto.fixture;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;

public class LottoGenerator {

    public static Lotto valueOf(int i1, int i2, int i3, int i4, int i5, int i6) {
        return new Lotto(
                List.of(
                        new LottoNumber(i1),
                        new LottoNumber(i2),
                        new LottoNumber(i3),
                        new LottoNumber(i4),
                        new LottoNumber(i5),
                        new LottoNumber(i6)
                )
        );
    }
}
