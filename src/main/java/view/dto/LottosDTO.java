package view.dto;

import java.util.List;
import model.Lotto;
import model.Lottos;
import model.LottoNumber;

public record LottosDTO(List<InnerLottoDetail> lottoDTOs) {

    public record InnerLottoDetail(List<Integer> numbers) {
        public static InnerLottoDetail from(Lotto lotto) {
            return new InnerLottoDetail(lotto.getLottoNumbers().stream().map(LottoNumber::value).toList());
        }
    }

    public static LottosDTO from(Lottos lottos) {
        return new LottosDTO(lottos.stream()
                .map(InnerLottoDetail::from)
                .toList());
    }
}
