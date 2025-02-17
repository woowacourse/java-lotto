package util;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Numbers;
import java.util.HashSet;
import java.util.Random;
import org.junit.jupiter.api.Test;

class RandomNumberPickerTest {

    @Test
    void 중복_없는_번호를_생성한다() {
        // given
        final RandomNumberPicker sut = new RandomNumberPicker(new Random());

        // when
        final Numbers result = sut.pickUnique(1, 10, 6);

        // then
        assertThat(result.getNumbers()).hasSize(6);
        assertThat(result.getNumbers().size()).isEqualTo(new HashSet<>(result.getNumbers()).size());
    }
}
