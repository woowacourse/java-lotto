package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoBundleTest {
    @DisplayName("LottoBundle 생성 테스트")
    @Test
    void LottoBundleConstructorTest() {
        assertThatCode(() -> new LottoBundle(Arrays.asList(
                makeProperLotto(),
                makeProperLotto()
        ))).doesNotThrowAnyException();
    }

    @DisplayName("LottoBundle countNumberOfLotto 테스트")
    @Test
    void countNumberOfLottoTest() {
        final LottoBundle lottoBundle = new LottoBundle(Arrays.asList(
                makeProperLotto(),
                makeProperLotto(),
                makeProperLotto()
                ));
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(3);
    }

    private Lotto makeProperLotto() {
        List<LottoBall> lotto = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lotto.add(LottoBall.valueOf(i));
        }
        return new Lotto(lotto);
    }

}
