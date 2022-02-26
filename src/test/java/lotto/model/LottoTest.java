package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private Lotto lotto;

    @Test
    void 로또_생성_테스트_정상() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        lotto = new Lotto(() -> lottoNumbers);
        assertThat(lotto).isInstanceOf(Lotto.class);
    }


    @Test
    void 로또_생성_테스트_길이() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4));
        assertThatThrownBy(() ->
                lotto = new Lotto(() -> lottoNumbers))
                .isInstanceOf(RuntimeException.class);
    }


    @Test
    void 로또_생성_테스트_중복() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(4), new LottoNumber(6));
        assertThatThrownBy(() ->
                lotto = new Lotto(() -> lottoNumbers))
                .isInstanceOf(RuntimeException.class);
    }

    @BeforeEach
    void init() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        lotto = new Lotto(() -> lottoNumbers);
    }

    @Test
    void 로또_1등_당첨_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(7));
        assertThat(rank).isEqualTo((Rank.FIRST));
    }

    @Test
    void 로또_2등_당첨_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(7));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(6));
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_3등_당첨_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(7));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(8));
        assertThat(rank).isEqualTo((Rank.THIRD));
    }

    @Test
    void 로또_4등_당첨_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(7),new LottoNumber(8));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(9));
        assertThat(rank).isEqualTo((Rank.FOURTH));
    }

    @Test
    void 로또_5등_당첨_테스트() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(7),new LottoNumber(8),new LottoNumber(9));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(10));
        assertThat(rank).isEqualTo((Rank.FIFTH));
    }

    @DisplayName("0개 일치")
    @Test
    void 로또_낙첨_테스트1() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(7),new LottoNumber(8),new LottoNumber(9),new LottoNumber(10),new LottoNumber(11),new LottoNumber(12));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("1개 일치")
    @Test
    void 로또_낙첨_테스트2() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(8),new LottoNumber(9),new LottoNumber(10),new LottoNumber(11),new LottoNumber(12));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("2개 일치")
    @Test
    void 로또_낙첨_테스트3() {
        List<LottoNumber> winningNumbers = Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(9),new LottoNumber(10),new LottoNumber(11),new LottoNumber(12));
        Rank rank = lotto.calculateRank(winningNumbers, new BonusNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }
}
