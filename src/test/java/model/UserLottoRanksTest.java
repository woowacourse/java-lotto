package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import dto.LottoDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserLottoRanksTest {

    @DisplayName("당첨 번호와 구매 로또 번호들을 통해 구매 로또 별 등수를 계산한다")
    @Test
    void calculateAllLottosRanksTest() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        winningLotto.setBonus("45");
        List<LottoDto> lottoDtos = List.of(
                new LottoDto(List.of(1,2,3,4,5,6)),
                new LottoDto(List.of(7,8,9,10,11,12)),
                new LottoDto(List.of(5,6,7,8,9,10)),
                new LottoDto(List.of(1,2,3,4,5,45))
        );

        UserLottoRanks userLottoRanks = new UserLottoRanks(winningLotto, lottoDtos);
        assertThat(userLottoRanks.getUserLottoRanks())
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 0)
                .containsEntry(Rank.FOURTH, 0)
                .containsEntry(Rank.FIFTH, 0)
                .containsEntry(Rank.FAIL, 2);
    }
}
