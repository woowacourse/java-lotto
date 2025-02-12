package domain;

import org.junit.jupiter.api.Test;
import util.RandomNumberPicker;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    
    @Test
    void 구입_금액만큼_로또를_구입한다() {
        // given
        int money = 14000;
        RandomNumberPicker randomNumberPicker = new RandomNumberPicker(new Random());
        
        // when
        List<Lotto> result = Lotto.purchase(money, randomNumberPicker);
        
        // then
        assertThat(result).hasSize(14);
    }
    
}