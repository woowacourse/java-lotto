package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponsePurchaseResultsTest {

    @DisplayName("로또 구매 결과를 저장하는 객체를 생성한다.")
    @Test
    void response_purchase_money_dto_test() {
        // given
        Money money = new Money(1000);
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        Lotto compareLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList()));
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(lottoNumbers));

        // when
        ResponsePurchaseResults dto = new ResponsePurchaseResults(lottos, money);

        // then
        assertThat(dto.getLottos()).hasSize(1);
        assertThat(dto.getLottos().get(0)).isEqualTo(compareLotto);
        assertThat(dto.getChanges().get()).isEqualTo(1000);
    }
}
