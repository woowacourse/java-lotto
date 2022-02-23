package lotto.receiver;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.exception.BonusNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberReceiverTest {

    @ParameterizedTest(name = "당첨 번호와 중복되는 보너스볼이 입력되면 예외발생 - case : {0}")
    @ValueSource(strings = {"1", "4"})
    void checkDuplication(String input) {
        List<LottoNumber> winningNumbers = List.of(LottoNumber.NUMBER_1,
                LottoNumber.NUMBER_4,
                LottoNumber.NUMBER_10,
                LottoNumber.NUMBER_20,
                LottoNumber.NUMBER_30,
                LottoNumber.NUMBER_45);
        Assertions.assertThatThrownBy(() -> BonusNumberReceiver.receive(input, winningNumbers))
                .isInstanceOf(BonusNumberException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
