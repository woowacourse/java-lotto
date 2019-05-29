package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    public void 로또_6자리_번호_리스트_자동_생성_테스트() {
        LottoFactory.createLottoAutomatically();
    }

    @Test
    public void 로또_6자리_번호_리스트_수동_생성_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        );

        assertThat(LottoFactory.createLottoManually(Arrays.asList(1,2,3,4,5,6)))
                .isEqualTo(new Lotto(numbers));
    }
}
