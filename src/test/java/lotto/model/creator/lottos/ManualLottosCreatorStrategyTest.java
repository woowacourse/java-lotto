package lotto.model.creator.lottos;

import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

class ManualLottosCreatorStrategyTest {
        @Test
        void 수동_로또_생성_일치_검사() {
                String[] lotto1 = new String[]{"1","2","3","4","5","6"};
                String[] lotto2 = new String[]{"1","2","3","4","5","6"};

                LottosCreator lottosCreator = new LottosCreator(new ManualLottosCreatorStrategy(Arrays.asList(lotto1, lotto2)));

                List<Lotto> lottos = lottosCreator.create();

                assertThat(lottos.get(0).getLottoNumbers().get(0) == LottoNumber.getInstance(1)).isTrue();
        }
}