package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class LottoBundleTest {
    @DisplayName("LottoBundle 생성 테스트")
    @Test
    void LottoBundleConstructorTest() {
        assertThatCode(() -> new LottoBundle(Arrays.asList(
                makeProperLotto(),
                makeProperLotto()
        ))).doesNotThrowAnyException();
    }

    private Lotto makeProperLotto() {
        List<LottoBall> lotto = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lotto.add(LottoBall.valueOf(i));
        }
        return new Lotto(lotto);
    }

}
