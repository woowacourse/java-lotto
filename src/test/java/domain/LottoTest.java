package domain;

import domain.numbergenerator.RandomNumberGenerator;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    static final Lotto basicLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @DisplayName("정적 팩토리 메서드로 로또 객체 생성이 정상적으로 작동하는지 확인한다.")
    @Test
    void 로또_객체_생성_테스트() {
        // given
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        // when
        Lotto lotto = Lotto.from(randomNumberGenerator);

        // then
        Assertions.assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @Nested
    class 로또_번호_검증_테스트 {
        static Stream<Arguments> getCorrectLottos() {
            return Stream.of(
                    Arguments.arguments(List.of(1, 2, 3, 4, 5, 6))
            );
        }

        static Stream<Arguments> getIncorrectLottos() {
            return Stream.of(
                    Arguments.arguments(List.of(1, 2, 3, 4, 5, 5)),  // 중복된 숫자 포함
                    Arguments.arguments(List.of(0, 1, 2, 3, 4, 5)),  // 범위를 벗어난 숫자 포함
                    Arguments.arguments(List.of(1, 2, 3, 4, 5, 6, 7)) // 6개 초과 숫자 포함
            );
        }

        @DisplayName("올바른 로또 번호를 정상적으로 생성하는지 확인한다.")
        @ParameterizedTest
        @MethodSource("getCorrectLottos")
        void 올바른_로또_번호_검증_테스트(List<Integer> correctLottoNumbers) {
            Assertions.assertThatNoException().isThrownBy(() -> new Lotto(correctLottoNumbers));
        }

        @DisplayName("올바르지 않은 로또 번호를 생성하면 예외가 발생하는지 확인한다.")
        @ParameterizedTest
        @MethodSource("getIncorrectLottos")
        void 올바르지_않은_로또_번호_검증_테스트(List<Integer> incorrectLottoNumbers) {
            Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(incorrectLottoNumbers));
        }
    }

    @Nested
    class has_메서드_동작_테스트 {
        static Stream<Arguments> provideHasTestArgsWillTrue() {
            return Stream.of(
                    Arguments.arguments(basicLotto, 6),
                    Arguments.arguments(basicLotto, 1)
            );
        }

        static Stream<Arguments> provideHasTestArgsWillFalse() {
            return Stream.of(
                    Arguments.arguments(basicLotto, 7),
                    Arguments.arguments(basicLotto, 8)
            );
        }

        @DisplayName("has() 메서드가 정상적으로 True 를 반환하는지 확인한다.")
        @ParameterizedTest
        @MethodSource("provideHasTestArgsWillTrue")
        void hasDuplicateNumber_True_테스트(Lotto lotto, int bonusNumber) {
            Assertions.assertThat(lotto.has(bonusNumber)).isTrue();
        }

        @DisplayName("has() 메서드가 정상적으로 False 를 반환하는지 확인한다.")
        @ParameterizedTest
        @MethodSource("provideHasTestArgsWillFalse")
        void hasDuplicateNumber_False_테스트(Lotto lotto, int bonusNumber) {
            Assertions.assertThat(lotto.has(bonusNumber)).isFalse();
        }
    }

    @DisplayName("getHitCountFrom() 메서드가 정상적으로 동작하는지 확인한다.")
    @Test
    void getHitCountFrom_테스트() {
        Assertions.assertThat(basicLotto.getHitCountFrom(new Lotto(List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(6);
        Assertions.assertThat(basicLotto.getHitCountFrom(new Lotto(List.of(1, 2, 3, 4, 5, 7)))).isEqualTo(5);
        Assertions.assertThat(basicLotto.getHitCountFrom(new Lotto(List.of(1, 2, 3, 4, 7, 8)))).isEqualTo(4);
        Assertions.assertThat(basicLotto.getHitCountFrom(new Lotto(List.of(7, 8, 9, 10, 11, 12)))).isEqualTo(0);
    }
}