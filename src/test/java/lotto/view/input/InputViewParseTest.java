package lotto.view.input;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.view.input.reader.CustomReader;

class InputViewParseTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);

    @DisplayName("구입 금액으로 입력된 문자열은 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-1", "1", "100", "3898"})
    void requestMoneyTest(final String inputLine) {
        customReader.initText(inputLine);

        final int actualMoney = inputView.requestMoney();
        final int expectedMoney = Integer.parseInt(inputLine);
        assertThat(actualMoney).isEqualTo(expectedMoney);
    }

    @DisplayName("로또 개수로 입력된 문자열은 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-1", "1", "100", "3898"})
    void requestTicketCountTest(final String inputLine) {
        customReader.initText(inputLine);

        final int actualTicketCount = inputView.requestTicketCount();
        final int expectedTicketCount = Integer.parseInt(inputLine);
        assertThat(actualTicketCount).isEqualTo(expectedTicketCount);
    }

    @DisplayName("로또 번호를 구성하는 볼 번호는 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @MethodSource("provideForRequestTicketNumbersTest")
    void requestTicketNumbersTest(final String inputLine, final List<Integer> expectedTicketNumbers) {
        customReader.initText(inputLine);

        final List<Integer> actualTicketNumbers = inputView.requestTicketNumbers();
        assertThat(actualTicketNumbers).isEqualTo(expectedTicketNumbers);
    }

    public static Stream<Arguments> provideForRequestTicketNumbersTest() {
        return Stream.of(
                Arguments.of("-1", List.of(-1)),
                Arguments.of("1", List.of(1)),
                Arguments.of("1,1,1", List.of(1, 1, 1)),
                Arguments.of("1,2,3,4,5", List.of(1, 2, 3, 4, 5)),
                Arguments.of("1 ,  2  ,  3  ", List.of(1, 2, 3))
        );
    }

    @DisplayName("구입 금액으로 입력된 문자열은 숫자로 변환되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"-1", "1", "100", "3898"})
    void requestBonusNumberTest(final String inputLine) {
        customReader.initText(inputLine);

        final int actualBonusNumber = inputView.requestBonusNumber();
        final int expectedBonusNumber = Integer.parseInt(inputLine);
        assertThat(actualBonusNumber).isEqualTo(expectedBonusNumber);
    }

}
