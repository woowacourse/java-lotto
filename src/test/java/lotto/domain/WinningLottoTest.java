package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스볼 중복")
    void bonus_ball_duplicated() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Lotto lotto = new Lotto(numbers);
        Ball bonusBall = new Ball("1");

        assertThatThrownBy(() -> {
            new WinningLotto(lotto,bonusBall);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
