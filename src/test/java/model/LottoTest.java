package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void initTest() {
        assertThatThrownBy(() -> new Lotto("1,3,7,10,15,99"));
    }

    @Test
    void matchingTest() {
        assertThat(new Lotto("4,8 , 18 ,25,,27    ,42").match(new WinningNumbers()).get()).isEqualTo(LottoRank.SECOND);
    }

}