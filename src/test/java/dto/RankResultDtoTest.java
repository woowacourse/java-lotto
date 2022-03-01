package dto;

import static org.assertj.core.api.Assertions.assertThat;

import model.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankResultDtoTest {

    @Test
    @DisplayName("로또 번호를 담는 dto 객체를 생성한다.")
    void generateLottoDto() {
        final Rank rank = Rank.FOURTH;
        final int winningCount = 5;

        final RankResultDto rankResultDto = new RankResultDto(rank, winningCount);
        assertThat(rankResultDto.getRankPrize()).isEqualTo(rank.getPrize());
        assertThat(rankResultDto.getMatchCount()).isEqualTo(rank.getMatchCount());
        assertThat(rankResultDto.getWinningCount()).isEqualTo(winningCount);
    }
}