package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    public void 로또_6자리_번호_리스트_자동_생성_테스트() {
        System.out.println(LottoFactory.createLottoAutomatically());
    }

    @Test
    public void 로또_6자리_번호_리스트_수동_생성_테스트() {
        Set<LottoNumber> numbers = new HashSet<>(
                Arrays.asList(
                        LottoNumber.get(1),
                        LottoNumber.get(2),
                        LottoNumber.get(3),
                        LottoNumber.get(4),
                        LottoNumber.get(5),
                        LottoNumber.get(6)
                ));

        assertThat(LottoFactory.createLottoManually("1, 2, 3, 4, 5, 6"))
                .isEqualTo(new Lotto(numbers));
    }
}
