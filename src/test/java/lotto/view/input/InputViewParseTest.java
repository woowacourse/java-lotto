package lotto.view.input;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.AppConfig;
import lotto.view.input.reader.CustomReader;

class InputViewParseTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final InputView inputView = APP_CONFIG.inputView;
    private final CustomReader customReader = APP_CONFIG.reader;

    @DisplayName("구입 금액으로 입력된 문자열은 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-1", "1", "100", "3898"})
    void requestMoneyTest(final String inputLine) {
        customReader.initText(inputLine);

        final int actual = inputView.requestMoney();
        final int expected = Integer.parseInt(inputLine);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("로또 개수로 입력된 문자열은 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-1", "1", "100", "3898"})
    void requestTicketCountTest(final String inputLine) {
        customReader.initText(inputLine);

        final int actual = inputView.requestTicketCount();
        final int expected = Integer.parseInt(inputLine);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("로또 번호를 구성하는 볼 번호는 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @MethodSource("provideForRequestTicketNumbersTest")
    void requestTicketNumbersTest(final String inputLine, final List<Integer> expected) {
        customReader.initText(inputLine);

        final List<Integer> actual = inputView.requestTicketNumbers();
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> provideForRequestTicketNumbersTest() {
        return Stream.of(
                Arguments.of("-1", List.of(-1)),
                Arguments.of("1", List.of(1)),
                Arguments.of("1,1,1", List.of(1,1,1)),
                Arguments.of("1,2,3,4,5", List.of(1,2,3,4,5)),
                Arguments.of("1 ,  2  ,  3  ", List.of(1,2,3))
        );
    }

    @DisplayName("구입 금액으로 입력된 문자열은 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-1", "1", "100", "3898"})
    void requestBonusNumberTest(final String inputLine) {
        customReader.initText(inputLine);

        final int actual = inputView.requestBonusNumber();
        final int expected = Integer.parseInt(inputLine);
        assertThat(actual).isEqualTo(expected);
    }

}
