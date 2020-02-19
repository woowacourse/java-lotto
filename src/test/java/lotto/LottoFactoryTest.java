package lotto;

import domain.LottoFactory;
import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {

    @Test
    void 로또_한장_생성(){
        List<LottoNumber> lotto = LottoFactory.createOneLotto();
        assertThat(lotto.size()).isEqualTo(6);
    }
}
