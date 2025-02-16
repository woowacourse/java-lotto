package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import view.InputErrorMessage;

class LottoTicketTest {

    @Nested
    @DisplayName("로또머신이 로또 번호를 정상적으로 발행하는지 테스트")
    class lottoMachineTest {
        @DisplayName("구매 금액에 따라 올바른 개수의 로또를 생성해야 한다.")
        @Test
        void shouldGenerateCorrectNumberOfTickets() {
            // given
            int purchaseAmount = 14000;
            LottoMachine lottoMachine = new LottoMachine();
            FixedIntegerGenerator generator = new FixedIntegerGenerator();

            // when
            List<LottoTicket> lottoTickets = lottoMachine.generateLottoTickets(purchaseAmount,
                    generator);

            // then
            Assertions.assertThat(lottoTickets.size()).isEqualTo(14);
        }

        @DisplayName("로또 번호의 개수가 6개가 아닌 경우 예외가 발생한다")
        @ParameterizedTest
        @MethodSource("notSixNumbers")
        void shouldGenerateSixNumbers(List<Integer> numbers) {
            // when & then
            Assertions.assertThatThrownBy(() -> {
                        LottoTicket.from(numbers);
                    }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(InputErrorMessage.REQUIRED_SIX_NUMBERS.getMessage());
        }

        static Stream<Arguments> notSixNumbers() {
            return Stream.of(
                    Arguments.of(List.of(1)),
                    Arguments.of(List.of(1, 2, 3)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)
                    ));
        }

        @DisplayName("로또 번호가 1 이상 45 이하가 아닌 경우 예외가 발생한다")
        @ParameterizedTest
        @MethodSource("validateRangeNumbers")
        void shouldGenerateValidRange(List<Integer> numbers) {
            // when & then
            Assertions.assertThatThrownBy(() -> {
                        LottoTicket.from(numbers);
                    }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(InputErrorMessage.NOT_FITTED_RANGE.getMessage());
        }

        static Stream<Arguments> validateRangeNumbers() {
            return Stream.of(
                    Arguments.of(List.of(0, 1, 2, 3, 4, 55)),
                    Arguments.of(List.of(-1, 1, 2, 3, 4, 5)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 100)
                    ));
        }

        @DisplayName("로또 번호가 중복된 경우 예외가 발생한다")
        @ParameterizedTest
        @MethodSource("duplicatedNumbers")
        void shouldNotGeneratedDuplicated(List<Integer> numbers) {
            // when & then
            Assertions.assertThatThrownBy(() -> {
                        LottoTicket.from(numbers);
                    }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(InputErrorMessage.DUPLICATED.getMessage());
        }

        static Stream<Arguments> duplicatedNumbers() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5, 5)),
                    Arguments.of(List.of(7, 7, 7, 7, 7, 7))
            );
        }
    }


    @DisplayName("로또 번호가 1 또는 45(경계값)일 때 정상적으로 로또를 발행하는지 테스트")
    @ParameterizedTest
    @MethodSource("randomLottoNumbers")
    void boundaryTest(List<Integer> numbers) {
        // when & then
        Assertions.assertThatCode(() -> LottoTicket.from(numbers))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> randomLottoNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(11, 12, 13, 14, 15, 45)),
                Arguments.of(List.of(1, 27, 42, 35, 8, 45)));
    }
}
