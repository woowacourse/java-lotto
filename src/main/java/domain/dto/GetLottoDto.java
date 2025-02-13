package domain.dto;

import java.util.List;

public record GetLottoDto(
        List<Integer> numbers
) {
}
