package controller.dto;

import domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottosDto {
    private final List<LottoDto> lottoDtos;
    private final Lottos lottos;

    public LottosDto(List<LottoDto> lottoDtos, Lottos lottos) {
        this.lottoDtos = new ArrayList<>(lottoDtos);
        this.lottos = lottos;
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }

    public Lottos getLottos() {
        return lottos;
    }

    public static LottosDto from(Lottos lottos) {
        return lottos.getLottos()
                .stream()
                .map(LottoDto::from)
                .collect(Collectors.collectingAndThen(
                        Collectors.toUnmodifiableList(),
                        lotto -> new LottosDto(lotto, lottos)
                ));
    }

    public int size(){
        return lottos.size();
    }
}
