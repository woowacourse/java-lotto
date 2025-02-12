package view.dto;

import java.util.List;
import java.util.Set;
import model.Lotto;
import model.Lottos;
import model.Number;

public record LottosDTO(List<LottoDTO> lottoDTOs) {

    public record LottoDTO(Set<Number> numbers){
        public static LottoDTO from(Lotto lotto){
            return new LottoDTO(lotto.getLottoNumbers());
        }
    }

    public static LottosDTO from(Lottos lottos){
        return new LottosDTO(lottos.getLottos()
                .stream()
                .map(LottoDTO::from)
                .toList());
    }
}
