package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    @DisplayName("map 초기화가 잘 되는 지 확인")
    void initializeMap() {
        EnumMap<Prize, Integer> prizeMap = Prize.initializeMap();
        for (Prize prize : Prize.values()) {
            assertThat(prizeMap.get(prize)).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("로또 번호 일치 갯수와 보너스 번호 일치 여부를 통해 Prize 를 잘 찾는 지 확인 : 1등")
    void findFirstPrize() {
        Prize prizeWithTrue = Prize.find(6, true);
        assertThat(prizeWithTrue).isEqualTo(Prize.match_six);

        Prize prizeWithFalse = Prize.find(6, false);
        assertThat(prizeWithFalse).isEqualTo(Prize.match_six);
    }

    @Test
    @DisplayName("로또 번호 일치 갯수와 보너스 번호 일치 여부를 통해 Prize 를 잘 찾는 지 확인 : 2등")
    void findSecondPrize() {
        Prize prizeWithTrue = Prize.find(5, true);
        assertThat(prizeWithTrue).isEqualTo(Prize.match_five_and_bonus);
    }

    @Test
    @DisplayName("로또 번호 일치 갯수와 보너스 번호 일치 여부를 통해 Prize 를 잘 찾는 지 확인 : 3등")
    void findThirdPrize() {
        Prize prizeWithTrue = Prize.find(5, false);
        assertThat(prizeWithTrue).isEqualTo(Prize.match_five);
    }

    @Test
    @DisplayName("로또 번호 일치 갯수와 보너스 번호 일치 여부를 통해 Prize 를 잘 찾는 지 확인 : 4등")
    void findFourthPrize() {
        Prize prizeWithTrue = Prize.find(4, true);
        assertThat(prizeWithTrue).isEqualTo(Prize.match_four);

        Prize prizeWithFalse = Prize.find(4, false);
        assertThat(prizeWithFalse).isEqualTo(Prize.match_four);
    }

    @Test
    @DisplayName("로또 번호 일치 갯수와 보너스 번호 일치 여부를 통해 Prize 를 잘 찾는 지 확인 : 5등")
    void findFifthPrize() {
        Prize prizeWithTrue = Prize.find(3, true);
        assertThat(prizeWithTrue).isEqualTo(Prize.match_three);

        Prize prizeWithFalse = Prize.find(3, false);
        assertThat(prizeWithFalse).isEqualTo(Prize.match_three);
    }
    @Test
    @DisplayName("로또 번호 일치 갯수와 보너스 번호 일치 여부를 통해 Prize 를 잘 찾는 지 확인 : 꼴등")
    void findNonePrize() {
        Prize prizeWithTrue = Prize.find(2, true);
        assertThat(prizeWithTrue).isEqualTo(Prize.match_none);

        Prize prizeWithFalse = Prize.find(1, false);
        assertThat(prizeWithFalse).isEqualTo(Prize.match_none);
    }
}