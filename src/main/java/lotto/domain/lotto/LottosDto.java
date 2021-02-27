package lotto.domain.lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottosDto {

    private final List<LottoDto> lottosDTO;

    private LottosDto(List<LottoDto> lottos) {
        this.lottosDTO = lottos;
    }

    public static LottosDto from(Lottos lottos) {
        List<LottoDto> lottosDto = lottos.getLottos()
            .stream()
            .map(lotto -> LottoDto.from(lotto))
            .collect(Collectors.toList());
        return new LottosDto(lottosDto);
    }

    public List<LottoDto> getLottosDTO() {
        return lottosDTO;
    }

    public int getNumOfLottos() {
        return lottosDTO.size();
    }
}
