package lotto.domain;

import lotto.utils.NumberUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPaperTest {

    WinningLotto winningLotto;

    Lotto customLotto1;
    Lotto customLotto2;
    Lotto customLotto3;
    Lotto customLotto4;

    @BeforeEach
    void setUp() {
        Lotto winningNumbers = new CustomLottoGenerator(NumberUtil.parsingNumber("1,2,3,4,5,6".split(","))).makeLotto();
        LottoNumber bonusNumber = LottoNumber.generateNumber(7);
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        customLotto1 = new CustomLottoGenerator(NumberUtil.parsingNumber("1,2,3,4,5,6".split(","))).makeLotto();
        customLotto2 = new CustomLottoGenerator(NumberUtil.parsingNumber("1,2,3,4,5,7".split(","))).makeLotto();
        customLotto3 = new CustomLottoGenerator(NumberUtil.parsingNumber("1,2,3,4,5,8".split(","))).makeLotto();
        customLotto4 = new CustomLottoGenerator(NumberUtil.parsingNumber("1,2,3,4,5,6".split(","))).makeLotto();
    }

    @Test
    void 매치_테스트() {
        LottoPaper lottoPaper = new LottoPaper(Arrays.asList(customLotto1, customLotto2, customLotto3));

        Map<Lotto, Rank> ranks1 = new HashMap<>();
        ranks1.put(customLotto1, Rank.FIRST);
        ranks1.put(customLotto3, Rank.THIRD);
        ranks1.put(customLotto2, Rank.SECOND);

        assertThat(lottoPaper.matchLotto(winningLotto)).isEqualTo(ranks1);
    }
}