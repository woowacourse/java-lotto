package lotto.domain;

        import org.junit.jupiter.api.Test;

        import java.util.Arrays;

        import static org.assertj.core.api.Assertions.assertThat;
        import static lotto.domain.Rank.*;

public class WinningLottoTest {

    @Test
    void 로또_등수_계산() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(lotto);
        assertThat(FIRST).isEqualTo(winningLotto.getWinning(lotto));
    }
}
