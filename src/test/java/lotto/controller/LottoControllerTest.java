package lotto.controller;

import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_COUNT;
import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_MIN;
import static lotto.rule.LottoConstants.Price.LOTTO_PRICE_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lotto.model.Cashier;
import lotto.model.LottoMachine;
import lotto.model.TestNumberPicker;
import lotto.view.Console;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 컨트롤러 테스트")
class LottoControllerTest {

    private LottoController lottoController;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        lottoController = new LottoController(
                new InputView(),
                new OutputView(),
                new Cashier(new LottoMachine(new TestNumberPicker())));
    }

    @AfterEach
    void cleanStreams() {
        Console.close();
    }


    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("정상적으로 로또 구매를 진행할 수 있다.")
        @Test
        void start() {
            String input = """
                    1000
                    1,2,3,4,5,6
                    7
                    """;
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            lottoController.start();

            String output = outputStream.toString().trim();
            assertThat(output).contains("구입금액을 입력해 주세요.");
            assertThat(output).contains("1개를 구매했습니다.");
            assertThat(output).contains("[1, 2, 3, 4, 5, 6]");
            assertThat(output).contains("지난 주 당첨 번호를 입력해 주세요.");
            assertThat(output).contains("보너스 볼을 입력해 주세요.");
            assertThat(output).contains("""
                    당첨 통계
                    ---------
                    3개 일치 (5000원) - 0개
                    4개 일치 (50000원) - 0개
                    5개 일치 (1500000원) - 0개
                    5개 일치, 보너스 볼 일치 (30000000원) - 0개
                    6개 일치 (2000000000원) - 1개
                    """
            );
            assertThat(output).contains("총 수익률은 2000000.00입니다.");
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("구입 금액이 0원인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenZeroAmount() {
            String input = "0";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또를 구매하려면 최소 %,d원 이상이어야 합니다.".formatted(LOTTO_PRICE_UNIT));
        }

        @DisplayName("구입 금액 단위가 일치하지 않을 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"999", "1001"})
        void shouldThrowException_WhenInvalidUnit(String input) {
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또는 %,d원 단위로 구매할 수 있습니다.".formatted(LOTTO_PRICE_UNIT));
        }

        @DisplayName("구입 금액이 숫자가 아닌 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenAmountIsNotNumber() {
            String input = "천원";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("숫자를 입력해 주세요.");
        }

        @DisplayName("구입 금액이 0원보다 작은 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenNegativeAmount() {
            String input = "-1000";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("음수일 수 없습니다.");
        }

        @DisplayName("로또 번호가 6개가 아닌 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenNumberCountIsNot6() {
            String input = """
                    1000
                    1,2,3,4,5
                    7
                    """;
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 %d개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }

        @DisplayName("로또 번호 범위를 벗어나는 숫자가 있는 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"0", "46"})
        void shouldThrowException_WhenNumberIsOutOfRange(String invalidNumber) {
            String input = """
                    1000
                    1,2,3,4,5,%s
                    7
                    """.formatted(invalidNumber);
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 %d부터 %d 사이의 수여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }

        @DisplayName("보너스 볼이 로또 번호와 중복되는 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenBonusBallIsDuplicated() {
            String input = """
                    1000
                    1,2,3,4,5,6
                    6
                    """;
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        @DisplayName("보너스 볼이 범위를 벗어나는 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"0", "46"})
        void shouldThrowException_WhenBonusBallIsOutOfRange(String invalidNumber) {
            String input = """
                    1000
                    1,2,3,4,5,6
                    %s
                    """.formatted(invalidNumber);
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThatThrownBy(() -> lottoController.start())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 번호는 %d부터 %d 사이의 수여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
    }
}
