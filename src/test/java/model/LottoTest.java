package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @Test
    void initTest() {
        assertThatThrownBy(() -> new Lotto("1,3,7,10,15,99"));
    }

    @Test
    void matchingTest() {
        Set<LottoNumber> winningNumbers = new HashSet<LottoNumber>() {{
                add(LottoNumber.of(1));
                add(LottoNumber.of(3));
                add(LottoNumber.of(7));
                add(LottoNumber.of(10));
                add(LottoNumber.of(15));
                add(LottoNumber.of(22));
            }};
        assertThat(new Lotto("1,3 , 7 ,10,,15    ,21").match(winningNumbers, LottoNumber.of(21))).isEqualTo(LottoRank.SECOND);
    }

}