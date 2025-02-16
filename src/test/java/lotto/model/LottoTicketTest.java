package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import lotto.LottoFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 티켓 테스트")
class LottoTicketTest {

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("로또 티켓을 생성할 수 있다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 10})
        void createTest(int count) {
            assertThatCode(() -> new LottoTicket(createLottosByCount(count)))
                    .doesNotThrowAnyException();
        }

        @DisplayName("로또 티켓에 포함된 로또 개수를 확인할 수 있다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 10})
        void getLottoCountTest(int count) {
            LottoTicket lottoTicket = new LottoTicket(createLottosByCount(count));

            assertThat(lottoTicket.getLottoCount())
                    .isEqualTo(count);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("로또 목록이 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenLottosIsNull() {
            assertThatCode(() -> new LottoTicket(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("로또 목록은 null이 될 수 없습니다.");
        }

        @DisplayName("로또 개수가 0개 이하인 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        void shouldThrowException_WhenCountIsLessThanOne(int count) {
            assertThatCode(() -> new LottoTicket(createLottosByCount(count)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 티켓은 최소 1개 이상의 로또가 있어야 합니다.");
        }
    }

    private List<Lotto> createLottosByCount(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoFixtures.lottoOneToSix);
        }
        return lottos;
    }
}
