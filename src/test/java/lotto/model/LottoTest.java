package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private Lotto lotto;

    @BeforeEach
    void init() {
        LottoNumbers lottoNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        lotto = new Lotto(lottoNumbers);
    }

    @DisplayName("로또 생성 정상 테스트")
    @Test
    void lottoTest() {
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @DisplayName("로또 1등 당첨 테스트")
    @Test
    void winFirstLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(7));
        assertThat(rank).isEqualTo((Rank.FIRST));
    }

    @DisplayName("로또 2등 당첨 테스트")
    @Test
    void winSecondLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 7});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(6));
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("로또 3등 당첨 테스트")
    @Test
    void winThirdLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 7});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(8));
        assertThat(rank).isEqualTo((Rank.THIRD));
    }

    @DisplayName("로또 4등 당첨 테스트")
    @Test
    void winFourthLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 7, 8});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(9));
        assertThat(rank).isEqualTo((Rank.FOURTH));
    }

    @DisplayName("로또 5등 당첨 테스트")
    @Test
    void winFifthLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 7, 8, 9});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(10));
        assertThat(rank).isEqualTo((Rank.FIFTH));
    }

    @DisplayName("로또 0개 일치 테스트")
    @Test
    void zeroMatchingLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{7, 8, 9, 10, 11, 12});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("로또 1개 일치 테스트")
    @Test
    void oneMatchingLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 8, 9, 10, 11, 12});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("로또 2개 일치 테스트")
    @Test
    void threeMatchingLottoTest() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 9, 10, 11, 12});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }
}
