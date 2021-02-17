package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BonusBallTest {

    @DisplayName("BonusBall를 생성하는 기능")
    @Test
    void generate() {
        //given
        int bonusBallValue = 7;

        //when
        BonusBall bonusBall = new BonusBall(bonusBallValue);

        //then
        assertThat(bonusBall).isNotNull();
    }
}