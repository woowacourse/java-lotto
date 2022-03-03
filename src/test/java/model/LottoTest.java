package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
    @Test
    @DisplayName("로또 번호가 중복이 있을시 예외가 발생한다.")
    void lottoTicketDuplicationException() {
        // given
        List<LottoNumber> lottoNumbers = IntStream.rangeClosed(1, 5)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());

        // when
        lottoNumbers.add(LottoNumber.valueOf(5));

        // then
        assertThatThrownBy(() -> Lotto.from(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 번호는 고를 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
    @DisplayName("로또 번호를 리스트로 받을 때 리스트 크기가 6이 아닌 경우 예외가 발생한다.")
    void lottoTicketNot6Numbers(List<LottoNumber> lottoNumbers) {
        // then
        assertThatThrownBy(() -> Lotto.from(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 숫자는 6개만 입력해야 합니다");
    }

    @Test
    @DisplayName("로또 티켓, 정상 입력시 생성이 된다")
    void createLotto() {
        // given
        List<LottoNumber> lottoNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());

        // when
        Lotto actual = Lotto.from(lottoNumbers);

        // then
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("두 로또 사이에 일치하는 번호 개수를 반환한다")
    void countMatching() {
        // given
        Lotto lotto1 = Lotto.from(IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList()));
        Lotto lotto2 = Lotto.from(IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList()));

        // when
        int actual = lotto1.countMatching(lotto2);
        int expected = 6;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideInvalidNumbers() {
        List<LottoNumber> under6Numbers = IntStream.rangeClosed(1, 5)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
        List<LottoNumber> over6Numbers = IntStream.rangeClosed(1, 7)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());

        return Stream.of(
                Arguments.of(under6Numbers),
                Arguments.of(over6Numbers)
        );
    }
}
