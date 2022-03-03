package domain;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
=======
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
<<<<<<< HEAD
import domain.LottoGenerator.WinningLottoGenerator;
<<<<<<< HEAD
import domain.player.Money;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
=======
import domain.LottoGenerator.ManualLottoGenerator;
>>>>>>> 27b9569 (refactor : 인터페이스에서 원하는 추상 메서드만 몸체를 구현하고자 어댑터 클래스 추가)
import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
<<<<<<< HEAD
<<<<<<< HEAD

class PlayerTest {

    private static final int BONUS_BALL_NUMBER = 22;
    private final int money = 15000;
    private final Player player = new Player(money);


    @BeforeEach
    void setUp() {
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        while (player.canBuyLotto()) {
            player.purchaseLotto(lottoGenerator.generateLotto());
        }
    }

    @Test
    @DisplayName("로또를 최대한으로 구매한다.")
    void getNumberOfPurchases() {
        List<Lotto> actual = player.getLottos();
        int expected = 15;
        assertThat(actual.size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Player의 모든 로또에 대해 당첨 번호와 비교한다.")
    void judgeAll() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(i);
        }
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateLotto(lottoNumbers), new LottoNumber(BONUS_BALL_NUMBER));
        List<Result> actual = player.judgeAll(winningLotto);
        int expected = 15;

        assertThat(actual.size()).isEqualTo(expected);
    }
=======
=======
import org.junit.jupiter.api.BeforeEach;
>>>>>>> 5b2a52c (refactor: 로또 생성 기능 인터페이스로 분리)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
=======
>>>>>>> 94c4d43 (style: 코드 포멧팅)
=======
import static org.assertj.core.api.Assertions.assertThatThrownBy;
>>>>>>> e1315e9 (test : 수동 로또 갯수에 대한 테스트)

class PlayerTest {

    private static final int BONUS_BALL_NUMBER = 22;
    private final int money = 15000;
    private final Player player = new Player(money);


    @BeforeEach
    void setUp() {
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        while (player.canBuyLotto()) {
            player.purchaseLotto(lottoGenerator.generateLotto());
        }
    }

    @Test
    @DisplayName("로또를 최대한으로 구매한다.")
    void getNumberOfPurchases() {
        List<Lotto> actual = player.getLottos();
        int expected = 15;
        assertThat(actual.size()).isEqualTo(expected);
    }
<<<<<<< HEAD
>>>>>>> 20a623d (feat: 로또 구매 기능 구현)
=======

    @Test
    @DisplayName("Player의 모든 로또에 대해 당첨 번호와 비교한다.")
    void judgeAll() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(i);
        }
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateLotto(lottoNumbers), new LottoNumber(BONUS_BALL_NUMBER));
        List<Result> actual = player.judgeAll(winningLotto);
        int expected = 15;

        assertThat(actual.size()).isEqualTo(expected);
    }
<<<<<<< HEAD
>>>>>>> 6996318 (feat: 로또 판정 로직 및 전체 판정 구현)
=======

    @Test
    @DisplayName("Player의 총 수익을 계산한다.")
    void calculateIncomeRateTest() {
        double totalIncome = 15000;
        double actual = player.calculateIncomeRate(totalIncome);
        assertThat(actual).isEqualTo(1.0);
    }

    @Test
    @DisplayName("수동 로또 갯수가 총 구매할 수 있는 로또 갯수를 초과할 경우 예외 발생")
    void manualLottoCountExcessTotalCountTest() {
        int manualLottoCount = 17;
        assertThatThrownBy(() -> player.selectLottoCount(manualLottoCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.MANUAL_LOTTO_PURCHASE_MONEY_EXCESS_TOTAL_MONEY);
    }

    @Test
    @DisplayName("수동 로또 갯수로 음수를 입력할 경우 예외 발생")
    void manualLottoCountNegativeTest() {
        int manualLottoCount = -1;
        assertThatThrownBy(() -> player.selectLottoCount(manualLottoCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.MANUAL_LOTTO_COUNT_IS_NOT_UNDER_ZERO);
    }
>>>>>>> e1315e9 (test : 수동 로또 갯수에 대한 테스트)
}