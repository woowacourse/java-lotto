package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoPrize;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.generator.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("구매한 로또들을 가진 Lottos 객체를 생성한다")
    @Test
    void lottos_constructor_test() {
        List<Lotto> randomLottos = new ArrayList<>();
        Generator lottoGenerator = new CustomLottoGenerator();
        randomLottos.add(lottoGenerator.generate());
        Lottos lottos = new Lottos(randomLottos);

        assertThat(lottos)
                .extracting("lottos")
                .asList()
                .hasSize(1);
    }

    @DisplayName("null가 파라미터로 주어지면 NullPointerException 예외를 발생시킨다")
    @Test
    void constructor_range_exception_test() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Lottos(null))
                .withMessage("null로 Lottos를 생성할 수 없습니다.");
    }

    @DisplayName("confirmWinnings 당첨 결과를 저장하는 Map을 반환한다.")
    @Test
    void confirmWinnings_test() {
        Generator lottoGenerator = new CustomLottoGenerator();
        Lottos lottos = new Lottos(new ArrayList<>() {{
            add(lottoGenerator.generate());
        }});

        WinningNumbers winningNumbers = getWinningNumbers();
        Map<LottoPrize, Integer> lottoMatches = lottos.confirmWinnings(winningNumbers);

        assertThat(lottoMatches.get(LottoPrize.FIRST)).isEqualTo(1);
    }

    private WinningNumbers getWinningNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return new WinningNumbers(new Lotto(lottoNumbers), new LottoNumber(30));
    }
}
