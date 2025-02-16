package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;
import service.MockingLottoService;

public class LottoDispenserTest {
    
    @Test
    @DisplayName("구입_금액이_0원이면_예외가_발생한다")
    public void 구입_금액이_0원이면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoDispenser("0");
        }).isInstanceOf(LottoException.class);
    }

    @Test
    @DisplayName("구입_금액이_1000원_단위가_아니면_예외가_발생한다")
    public void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoDispenser("1001");
        }).isInstanceOf(LottoException.class);
    }

    @Test
    @DisplayName("구입_금액이_숫자가_아니면_예외가_발생한다")
    public void 구입_금액이_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            new LottoDispenser("a");
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
        String winningNumber = "1, 2, 3, 4, 5, 6";
        String bonusNumber = "7";
        MockingLottoService mockingLottoService = new MockingLottoService(new LottoRepository(),
                new WinningNumberRepository(), new BonusNumberRepository());
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(testLottoNumbers));
        assertThat(mockingLottoService.winningCalculate(lottos, winningNumber, bonusNumber)).contains("총 수익률은 2000000");
    }
}
