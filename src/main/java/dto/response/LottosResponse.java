package dto.response;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import java.util.List;

public record LottosResponse(
        List<LottoNumbers> lottos
) {
    public static LottosResponse from(Lottos lottos) {
        return new LottosResponse(
                lottos.getLottos().stream()
                        .map(LottoNumbers::from)
                        .toList()
        );
    }

    public record LottoNumbers(
            List<Integer> value
    ) {
        public static LottoNumbers from(Lotto lotto) {
            return new LottoNumbers(
                    lotto.getNumbers().stream()
                            .map(LottoNumber::getValue)
                            .toList());
        }
    }
}