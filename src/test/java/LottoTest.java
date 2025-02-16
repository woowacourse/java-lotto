import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    @DisplayName("로또 번호를 생성할 때 로또 번호는 중복되지 않고, 6개라면 예외가 발생하지 않는다")
    void not_exception_lotto_number_is_not_duplicated_and_lotto_number_count_is_6_when_ctor() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto.getNumbers()).containsAll(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또는 로또 번호들은 오름차순으로 저장한다")
    void check_lotto_number_is_sorted() {
        // given & when
        Lotto lotto = new Lotto(List.of(6, 5, 1, 2, 3, 4));

        // then
        assertThat(lotto.getNumbers()).isSorted();
    }

    @Test
    @DisplayName("로또를 생성할 때 로또 번호가 6개가 아니면 예외가 발생한다")
    void exception_lotto_number_count_is_not_6_when_ctor() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generateOutboundLottoNumber() {
        return Stream.of(
                arguments(List.of(0, 1, 2, 3, 4, 5)),
                arguments(List.of(46, 1, 2, 3, 4, 5))
        );
    }

    @ParameterizedTest
    @DisplayName("로또를 생성할 때 로또 번호가 범위가 벗어날 경우 예외가 발생한다")
    @MethodSource("generateOutboundLottoNumber")
    void exception_lotto_number_is_outbound_when_ctor(List<Integer> numbers) {
        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또를 생성할 때 로또 번호가 중복되면 예외가 발생한다")
    void exception_lotto_number_is_duplicated_when_ctor() {
        // given
        List<Integer> numbers = List.of(1, 1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또를 구매할 때 금액이 로또 가격의 배수라면 로또 개수를 반환한다")
    void should_return_lotto_count_when_money_is_multiple_of_lotto_price() {
        // given
        Money money = new Money(1000);

        // when
        int lottoCount = Lotto.countPurchasableLottosByMoney(money);

        // then
        assertThat(lottoCount).isEqualTo(1);
    }

    @Test
    @DisplayName("로또를 구매할 때 금액이 로또 가격의 배수가 아니면 예외가 발생한다")
    void should_throw_exception_when_money_is_not_multiple_of_lotto_price() {
        // given
        Money money = new Money(1500);

        // when & then
        assertThatThrownBy(() -> Lotto.countPurchasableLottosByMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

}