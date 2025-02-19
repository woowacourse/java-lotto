package dto;

import domain.Lotto;
import domain.Lottos;
import java.util.List;

public record IssuedLottosDto(
        List<IssuedLottoDto> lottos
) {
    public static IssuedLottosDto from(Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottos();
        List<IssuedLottoDto> dtos = lottoList.stream().map(lotto -> {
            return new IssuedLottoDto(lotto.getSortedLottoNumbers());
        }).toList();
        return new IssuedLottosDto(dtos);
    }
}
