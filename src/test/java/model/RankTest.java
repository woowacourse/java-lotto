package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.LottoDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RankTest {

    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto("1,2,3,4,5,6");
        winningLotto.setBonus("7");
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되면 2등이다")
    @Test
    void secondRankTest() {
        LottoDto lottoDto = new LottoDto(List.of(1, 2, 3, 4, 5, 7));
        assertThat(Rank.getRank(winningLotto, lottoDto)).isEqualTo(Rank.SECOND);
    }


    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되지 않으면 3등이다")
    @Test
    void thirdRankTest() {
        LottoDto lottoDto = new LottoDto(List.of(1, 2, 3, 4, 5, 8));
        assertThat(Rank.getRank(winningLotto, lottoDto)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("당첨 번호가 로또 번호와 3개 미만으로 매치되면 낙첨이다")
    @Test
    void failRankTest() {
        Assertions.assertAll(
                () -> assertThat(Rank.getRank(winningLotto, new LottoDto(List.of(1, 2, 9, 10, 11, 12)))).isEqualTo(Rank.FAIL),
                () -> assertThat(Rank.getRank(winningLotto, new LottoDto(List.of(1, 13, 9, 10, 11, 12)))).isEqualTo(Rank.FAIL),
                () -> assertThat(Rank.getRank(winningLotto, new LottoDto(List.of(45, 13, 9, 10, 11, 12)))).isEqualTo(Rank.FAIL)
        );

    }
}
