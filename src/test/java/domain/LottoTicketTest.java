package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LottoTicketTest {
    private static Stream<List<LottoNumber>> setUpLottoTicket() {
        return Stream.of(
                new ArrayList(Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)
                )),
                new ArrayList(Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(5)
                ))
        );
    }

    @DisplayName("비정상적인 값으로 LottoTicket 을 생성할 때 예외가 출력되는지 테스트")
    @ParameterizedTest
    @MethodSource("setUpLottoTicket")
    void lottoTicketConstructorTest(List<LottoNumber> lottoNumbers) {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoTicket 에 입력한 숫자가 있는지 확인하는 테스트")
    @Test
    void containsTest() {
        List<LottoNumber> lottoNumbers = new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        Assertions.assertThat(lottoTicket.getLottoTicket()).containsExactly(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
    }
}
