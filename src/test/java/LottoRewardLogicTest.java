import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoRewardLogic;
import domain.Ranks;
import domain.Rewards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRewardLogicTest {

    @Test
    @DisplayName("로또 맞춘 개수와 보너스 넘버를 등수로 바꿔주는 기능 테스트_1등")
    void convertToRankTest() {
        LottoRewardLogic lottoRewardLogic = new LottoRewardLogic();

        assertThat(Ranks.of(6, false).getReward()).isEqualTo(Rewards.FIRST_REWARD);
    }

    @Test
    @DisplayName("로또 맞춘 개수와 보너스 넘버를 등수로 바꿔주는 기능 테스트_2등")
    void convertToRankTest2() {
        LottoRewardLogic lottoRewardLogic = new LottoRewardLogic();

        assertThat(Ranks.of(5, true).getReward()).isEqualTo(Rewards.SECOND_REWARD);
    }

    @Test
    @DisplayName("로또 맞춘 개수와 보너스 넘버를 등수로 바꿔주는 기능 테스트_3등")
    void convertToRankTest3() {
        LottoRewardLogic lottoRewardLogic = new LottoRewardLogic();

        assertThat(Ranks.of(5, false).getReward()).isEqualTo(Rewards.THIRD_REWARD);
    }

    @Test
    @DisplayName("로또 맞춘 개수와 보너스 넘버를 등수로 바꿔주는 기능 테스트_4등")
    void convertToRankTest4() {
        LottoRewardLogic lottoRewardLogic = new LottoRewardLogic();

        assertThat(Ranks.of(4, false).getReward()).isEqualTo(Rewards.FORTH_REWARD);
    }

    @Test
    @DisplayName("로또 맞춘 개수와 보너스 넘버를 등수로 바꿔주는 기능 테스트_5등")
    void convertToRankTest5() {
        LottoRewardLogic lottoRewardLogic = new LottoRewardLogic();

        assertThat(Ranks.of(3, false).getReward()).isEqualTo(Rewards.FIFTH_REWARD);
    }

    @Test
    @DisplayName("로또 맞춘 개수와 보너스 넘버를 등수로 바꿔주는 기능 테스트_미당첨")
    void convertToRankTest6() {
        LottoRewardLogic lottoRewardLogic = new LottoRewardLogic();

        assertThat(Ranks.of(2, true).getReward()).isEqualTo(Rewards.NO_REWARD);
    }
}
