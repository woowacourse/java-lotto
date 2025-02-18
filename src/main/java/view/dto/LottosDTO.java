package view.dto;

import java.util.List;
import model.Lotto;
import model.LottoNumber;
import model.Lottos;

public record LottosDTO(List<SingleLottoDTO> lottoDTOs) {

    public record SingleLottoDTO(List<Integer> numbers) {
        public static SingleLottoDTO from(Lotto lotto) {
            return new SingleLottoDTO(lotto.getLottoNumbers().stream().map(LottoNumber::value).toList());
        }
    }

    public static LottosDTO from(Lottos lottos) {
        return new LottosDTO(lottos.getLottos()
                .stream()
                .map(SingleLottoDTO::from)
                .toList());
    }
}
