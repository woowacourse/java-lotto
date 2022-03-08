package service.dto;

import java.util.List;

public class IssuedLottosDto {
    private List<LottoDto> lottos;

    public IssuedLottosDto(List<LottoDto> lottos) {
        this.lottos = lottos;
    }

    public List<LottoDto> getLottos() {
        return lottos;
    }
}
