package dto;

import domain.Prize;
import domain.WinningStatics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WinningStaticsDto {
    private final List<PrizeDto> prizeDtos;

    private WinningStaticsDto(final List<PrizeDto> prizeDtos) {
        this.prizeDtos = prizeDtos;
    }

    public static WinningStaticsDto from(final WinningStatics winningStatics) {
        final List<PrizeDto> prizeDtos = new ArrayList<>();

        winningStatics.toMap()
                .entrySet()
                .stream()
                .filter(entry -> !Objects.equals(Prize.NOTHING, entry.getKey()))
                .forEach(
                        entry -> {
                            prizeDtos.add(PrizeDto.of(entry.getKey(), entry.getValue()));
                        }
                );

        Collections.reverse(prizeDtos);
        return new WinningStaticsDto(prizeDtos);
    }

    public List<PrizeDto> getPrizeDtos() {
        return prizeDtos;
    }
}
