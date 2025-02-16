package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTicketTest {

    @DisplayName("로또머신이 로또 번호를 정상적으로 발행하는지 테스트")
    @Test
    void 로또_발행_테스트() {
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when
        LottoTicket lottoTicket = lottoMachine.generateLottoTicket(new FixedIntegerGenerator());

        // then
        Assertions.assertThat(lottoTicket.getSize()).isEqualTo(LottoTicket.LOTTO_SIZE);
        for (int number : lottoTicket.getNumbers()) {
            Assertions.assertThat(number).isGreaterThanOrEqualTo(LottoTicket.LOTTO_MIN_NUMBER);
            Assertions.assertThat(number).isLessThanOrEqualTo(LottoTicket.LOTTO_MAX_NUMBER);
        }
        Assertions.assertThat(lottoTicket.getNumbers().stream().distinct()).hasSize(LottoTicket.LOTTO_SIZE);
    }

    @DisplayName("로또 번호의 개수가 6개가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("notSixNumbers")
    void 로또_번호의_개수가_6개가_아닌_경우_테스트(List<Integer> numbers) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new LottoTicket(numbers);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    static Stream<Arguments> notSixNumbers() {
        return Stream.of(Arguments.of(
                List.of(1),
                List.of(1, 2, 3),
                List.of(1, 2, 3, 4, 5, 6, 7)
        ));
    }

    @DisplayName("로또 번호가 1 이상 45 이하가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("validateRangeNumbers")
    void 로또_번호가_1_이상_45_이하가_아닌_경우_테스트(List<Integer> numbers) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new LottoTicket(numbers);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 이상 45 이하이다.");
    }

    static Stream<Arguments> validateRangeNumbers() {
        return Stream.of(Arguments.of(
                List.of(0, 1, 2, 3, 4, 55),
                List.of(-1, 1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 100)
        ));
    }

    @DisplayName("로또 번호가 중복된 경우 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("duplicatedNumbers")
    void 로또_번호가_중복된_경우_테스트(List<Integer> numbers) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new LottoTicket(numbers);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 번호가 존재합니다.");
    }

    static Stream<Arguments> duplicatedNumbers() {
        return Stream.of(Arguments.of(
                List.of(1, 2, 3, 4, 5, 5),
                List.of(7, 7, 7, 7, 7, 7)
        ));
    }

    @DisplayName("로또 번호의 개수가 1 이상 45 이하일 때 정상적으로 로또를 발행하는지 테스트")
    @ParameterizedTest
    @MethodSource("randomLottoNumbers")
    void 로또_테스트(List<Integer> numbers) {
        // when & then
        Assertions.assertThatCode(() -> new LottoTicket(numbers))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> randomLottoNumbers() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 27, 42, 35, 8, 45)));
    }
}
