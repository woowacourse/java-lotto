package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumberFactoryTest {
    @Test
    void 팩토리_생성_테스트() {
        LottoNumber lottoNumber = LottoNumberFactory.getInstance(1);
        Assertions.assertThat(lottoNumber).hasFieldOrPropertyWithValue("number", 1);
    }

    @Test
    void 캐시를_이용해_값이_같으면_동일한_객체_생성() {
        LottoNumber lottoNumber1 = LottoNumberFactory.getInstance(1);
        LottoNumber lottoNumber2 = LottoNumberFactory.getInstance(1);
        Assertions.assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
