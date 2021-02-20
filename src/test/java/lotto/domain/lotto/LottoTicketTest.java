package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTicketTest {

    @ParameterizedTest
    @DisplayName("잘못된 입력값 생성 불가")
    @MethodSource("invalidList_testcase")
    void fail_invalidList(List<LottoNumber> lottoNumbers) {
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> invalidList_testcase() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(makeLottoNumber(1, 2, 3, 4, 5)),
                Arguments.of(makeLottoNumber(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(makeLottoNumber(1, 2, 3, 4, 5, 5))
        );
    }

    @ParameterizedTest
    @DisplayName("수정 불가 리스트로 반환")
    @MethodSource("testcase")
    void toUnmodifiableList(ThrowingCallable throwingCallable) {
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    private static Stream<Arguments> testcase() {
        List<LottoNumber> lottoNumbers = new LottoTicket("1,2,3,4,5,6").toUnmodifiableList();
        LottoNumber lottoNumber = new LottoNumber(7);

        return Stream.of(
                Arguments.of((ThrowingCallable) () -> lottoNumbers.add(lottoNumber)),
                Arguments.of((ThrowingCallable) () -> lottoNumbers.remove(lottoNumber)),
                Arguments.of((ThrowingCallable) () -> lottoNumbers.set(0,lottoNumber)),
                Arguments.of((ThrowingCallable) () -> lottoNumbers.clear())
        );
    }

    private static List<LottoNumber> makeLottoNumber(int... ints) {
        return Arrays.stream(ints).boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}