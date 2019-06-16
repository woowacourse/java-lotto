package lotto.domain;

import lotto.utils.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPaperTest {

    WinningLotto winningLotto;

    Lotto customLotto1;
    Lotto customLotto2;
    Lotto customLotto3;
    Lotto customLotto4;

    @BeforeEach
    void setUp() {
        Lotto winningNumbers = new CustomLottoGenerator(Parser.parsingLottoNumbers("1,2,3,4,5,6".split(","))).makeLotto();
        LottoNumber bonusNumber = LottoNumber.generateNumber(7);
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        customLotto1 = new CustomLottoGenerator(Parser.parsingLottoNumbers("1,2,3,4,5,6".split(","))).makeLotto();
        customLotto2 = new CustomLottoGenerator(Parser.parsingLottoNumbers("1,2,3,4,5,7".split(","))).makeLotto();
        customLotto3 = new CustomLottoGenerator(Parser.parsingLottoNumbers("1,2,3,4,5,8".split(","))).makeLotto();
        customLotto4 = new CustomLottoGenerator(Parser.parsingLottoNumbers("1,2,3,4,5,6".split(","))).makeLotto();
    }

    @Test
    void 매치_테스트() {
        LottoPaper lottoPaper = new LottoPaper(Arrays.asList(customLotto1, customLotto2, customLotto3, customLotto4));
        List<Rank> ranks = Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FIRST);
        assertThat(lottoPaper.matchLotto(winningLotto)).isEqualTo(ranks);
    }
}