package lotto.model.creator.lotto;

import lotto.model.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCreatorStrategyTest {
        @Test
        void 수동_로또_생성() {
                LottoCreatorStrategy lottoCreatorStrategy = new ManualLottoCreatorStrategy(new String[]{"1","2","3","4","5","6"});

                assertThat(lottoCreatorStrategy.create().getLottoNumbers()).isEqualTo(Arrays.asList(LottoNumber.getInstance(1)
                    , LottoNumber.getInstance(2)
                    , LottoNumber.getInstance(3)
                    , LottoNumber.getInstance(4)
                    , LottoNumber.getInstance(5)
                    , LottoNumber.getInstance(6)));
        }
}