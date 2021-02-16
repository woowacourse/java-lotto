package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {

    private static Stream<Arguments> getInvalidLottoNumbers() {
        return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 3, 3, 4, 5, 6)));
    }

    @DisplayName("로또 티켓 발급에 필요한 로또 번호는 중복되지 않은 6자리 숫자이다")
    @ParameterizedTest
    @MethodSource("getInvalidLottoNumbers")
    void wrongLottoNumberCounts(List<Integer> numbers) {
        assertThatCode(() -> {
            LottoTicket.generateTicket(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.");
    }
}