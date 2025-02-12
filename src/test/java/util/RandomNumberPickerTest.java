package util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberPickerTest {
    
    @Test
    void 중복_없는_번호를_생성한다() {
        // given
        RandomNumberPicker sut = new RandomNumberPicker(new Random());
        
        // when
        List<Integer> result = sut.pickUnique(1, 10, 3);
        
        // then
        assertThat(result).hasSize(3);
        assertThat(result.size()).isEqualTo(new HashSet<>(result).size());
    }
}
