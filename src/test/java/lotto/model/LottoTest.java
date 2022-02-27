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

    @Test
    void 로또_생성_테스트_정상() {
        LottoNumbers lottoNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        lotto = new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers);
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @BeforeEach
    void init() {
        LottoNumbers lottoNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        lotto = new Lotto((minimumNumber, maximumNumber, lottoLength) -> lottoNumbers);
    }

    @Test
    void 로또_1등_당첨_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(7));
        assertThat(rank).isEqualTo((Rank.FIRST));
    }

    @Test
    void 로또_2등_당첨_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 7});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(6));
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_3등_당첨_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 7});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(8));
        assertThat(rank).isEqualTo((Rank.THIRD));
    }

    @Test
    void 로또_4등_당첨_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 7, 8});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(9));
        assertThat(rank).isEqualTo((Rank.FOURTH));
    }

    @Test
    void 로또_5등_당첨_테스트() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 2, 3, 7, 8, 9});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(10));
        assertThat(rank).isEqualTo((Rank.FIFTH));
    }

    @DisplayName("0개 일치")
    @Test
    void 로또_낙첨_테스트1() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{7, 8, 9, 10, 11, 12});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("1개 일치")
    @Test
    void 로또_낙첨_테스트2() {
        LottoNumbers winningNumbers = makeLottoNumbers(new int[]{1, 8, 9, 10, 11, 12});
        Rank rank = lotto.calculateRank(winningNumbers, new LottoNumber(13));
        assertThat(rank).isEqualTo((Rank.LOSER));
    }

    @DisplayName("2개 일치")
    @Test
    void 로또_낙첨_테스트3() {
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
