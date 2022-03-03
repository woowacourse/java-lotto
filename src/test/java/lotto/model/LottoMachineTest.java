package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.model.generator.CustomLottoGenerator;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LottoMachineTest {

    private Money money;
    private Lottos lottos;
    private LottoGenerator lottoGenerator;
    private LottoMachine lottoMachine;

    @BeforeEach
    void init() {
        money = new Money(3000);
        lottoGenerator = new CustomLottoGenerator();
        lottos = makeLottos();
    }

    private Lottos makeLottos() {
        Lotto second = makeLotto(new int[]{2, 3, 4, 5, 6, 7}); //2등
        Lotto fourth = makeLotto(new int[]{3, 4, 5, 6, 7, 8}); //4등
        return new Lottos(new ArrayList<>(Arrays.asList(second, fourth)));
    }

    private Lotto makeLotto(int[] numbers) {
        return new Lotto(makeLottoNumbers(numbers));
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }

    @DisplayName("로또 머신 정상 생성 테스트")
    @Test
    void generateLottoMachineTest() {
        assertThatCode(() -> new LottoMachine(lottoGenerator, money, new LottoCount(2, money), lottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 머신 생성 시 수동 로또 갯수와 Lottos의 크기 불일치하여 예외 발생")
    @Test
    void generateLottoMachineInconsistencyTest() {
        assertThatThrownBy(() -> new LottoMachine(lottoGenerator, money, new LottoCount(1, money), lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 머신 생성 시 구매 가능 로또 수 초과하여 예외 발생")
    @Test
    void generateLottoMachineExcessTest() {
        money = new Money(1000);
        assertThatThrownBy(() -> new LottoMachine(lottoGenerator, money, new LottoCount(2, money), lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("1,2,4등 개수 테스트")
    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"FIRST", "SECOND", "FOURTH"})
    void calculateResultTest(Rank rank) {
        calculateLottoMachine();
        assertThat(lottoMachine.getEachRankCount(rank)).isEqualTo(1);
    }

    @DisplayName("수익률 테스트")
    @Test
    void getRevenueTest() {
        calculateLottoMachine();
        assertThat(lottoMachine.getRevenue()).isEqualTo((double) (2000000000 + 30000000 + 50000) / 3000);
    }

    private void calculateLottoMachine() {
        lottoMachine = new LottoMachine(lottoGenerator, money, new LottoCount(2, money), lottos);
        WinningLotto winningLotto = makeWinningLotto(new int[]{1, 2, 3, 4, 5, 6}, 7);
        lottoMachine.calculateResult(winningLotto);
    }

    private WinningLotto makeWinningLotto(int[] numbers, int bonusNumber) {
        return new WinningLotto(makeLottoNumbers(numbers), new LottoNumber(bonusNumber));
    }
}
