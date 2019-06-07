package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class IssuedLottosTest {
    @Test
    void 발급된_로또들의_총_구매_가격을_되돌려주는지_테스트() {
        Set<LottoNumber> lottoNumbers = new TreeSet<>(Arrays.asList(LottoNumberPool.pickLottoNumber(1),
                LottoNumberPool.pickLottoNumber(2), LottoNumberPool.pickLottoNumber(3),
                LottoNumberPool.pickLottoNumber(4), LottoNumberPool.pickLottoNumber(5),
                LottoNumberPool.pickLottoNumber(6)));
        IssuedLottos issuedLottos = IssuedLottos.of(Arrays.asList(new IssuedLotto(lottoNumbers)));

        assertThat(issuedLottos.getPurchasedAmount()).isEqualTo(1000);
    }
}

