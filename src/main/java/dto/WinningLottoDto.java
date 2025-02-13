package dto;

import java.util.List;

public record WinningLottoDto(
        List<Integer> numbers,
        Integer bonusNumber
) {
}
