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

    public static Stream<Arguments> lottoNumbersWithWrongSize() {
        return Stream.of(
                Arguments.of(
                        List.of(1),
                        List.of(1, 2),
                        List.of(1, 2, 3),
                        List.of(1, 2, 3, 4),
                        List.of(1, 2, 3, 4, 5)
                )
        );
    }

    public static Stream<Arguments> lottoNumbersNotInRange() {
        return Stream.of(
                Arguments.of(
                        List.of(0, 1, 2, 3, 4, 5),
                        List.of(1, 2, 3, 4, 5, 46),
                        List.of(-1, 2, 3, 4, 5, 6)
                )
        );
    }

    public static Stream<Arguments> lottoNumbersInRange() {
        return Stream.of(
                Arguments.of(
                        // 경계값(1, 45) 테스트 케이스
                        List.of(1, 2, 3, 4, 5, 45),
                        List.of(1, 12, 13, 14, 16, 45),
                        List.of(1, 2, 3, 43, 44, 45)
                )
        );
    }

    @DisplayName("로또 번호의 개수가 6개가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("lottoNumbersWithWrongSize")
    void 로또_번호의_개수가_6개가_아닌_경우_테스트(List<Integer> numbers) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new LottoTicket(new LottoNumbers(numbers));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 1 이상 45 이하가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("lottoNumbersNotInRange")
    void 로또_번호가_1_이상_45_이하가_아닌_경우_예외_발생(List<Integer> numbers) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new LottoTicket(new LottoNumbers(numbers));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 이상 45 이하여야 합니다.");
    }

    @DisplayName("로또 번호가 1 이상 45 이하인 경우 정상적으로 로또가 발행된다.")
    @ParameterizedTest
    @MethodSource("lottoNumbersInRange")
    void 로또_번호가_1_이상_45_이하인_경우(List<Integer> numbers) {
        // when & then
        Assertions.assertThatCode(() -> {
            new LottoTicket(new LottoNumbers(numbers));
        }).doesNotThrowAnyException();
    }

    @DisplayName("로또 번호가 중복된 경우 예외가 발생한다")
    @Test
    void 로또_번호가_중복된_경우_예외_발생() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new LottoTicket(new LottoNumbers(numbers));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 번호가 존재합니다.");
    }
}
