package controller.dto;

import domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottosDto {
    private final List<LottoDto> lottos;

    public LottosDto(List<LottoDto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<LottoDto> getLottos() {
        return lottos;
    }

    public static LottosDto from(Lottos lottos) {

        return lottos.getLottos()
                .stream()
                .map(LottoDto::from)
                .collect(Collectors.collectingAndThen(
                        Collectors.toUnmodifiableList(),
                        LottosDto::new
                ));
    }
}
