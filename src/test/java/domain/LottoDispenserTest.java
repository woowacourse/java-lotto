package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.strategy.LottoRandomGeneratorStrategy;
import domain.strategy.TestRandomGeneratorStrategy;
import exception.LottoException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoDispenserTest {

    private LottoBuyResultFormatter lottoBuyResultFormatter;

    @BeforeEach
    public void setUp() {
        lottoBuyResultFormatter = new LottoBuyResultFormatter();
    }

    @Test
    @DisplayName("구입_금액이_0원이면_예외가_발생한다")
    public void 구입_금액이_0원이면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoDispenser("0", new LottoRandomGeneratorStrategy());
        }).isInstanceOf(LottoException.class);
    }

    @Test
    @DisplayName("구입_금액이_1000원_단위가_아니면_예외가_발생한다")
    public void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoDispenser("1001", new LottoRandomGeneratorStrategy());
        }).isInstanceOf(LottoException.class);
    }

    @Test
    @DisplayName("구입_금액이_숫자가_아니면_예외가_발생한다")
    public void 구입_금액이_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoDispenser("a", new LottoRandomGeneratorStrategy());
        }).isInstanceOf(LottoException.class);
    }

    private static Stream<Arguments> calculateWinningResult() {
        return Stream.of(
                Arguments.arguments(
                        List.of(1, 2, 3, 4, 5, 6)
                )
        );
    }

    @ParameterizedTest
    @DisplayName("당첨_통계_계산_및_출력_테스트")
    @MethodSource("calculateWinningResult")
    public void 당첨_통계_계산_및_출력_테스트(List<Integer> testLottoNumbers) {
        WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7");
        LottoDispenser lottoDispenser = new LottoDispenser("1000", new TestRandomGeneratorStrategy(testLottoNumbers));
        assertThat(lottoDispenser.winningCalculate(winningNumber, bonusNumber).get(WinningCase.SIX_SAME));
    }
}

