package lotto.dto.response;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.util.List;

public record LottosResponse(
    List<InnerLotto> lottos
) {
    public static LottosResponse from(Lottos lottos) {
        return new LottosResponse(
            lottos.getLottos().stream()
                .map(InnerLotto::from)
                .toList()
        );
    }

    public record InnerLotto(
        List<Integer> numbers
    ) {
        public static InnerLotto from(Lotto lotto) {
            return new InnerLotto(
                lotto.getNumbers().stream()
                    .map(LottoNumber::number)
                    .toList());
        }
    }
}
