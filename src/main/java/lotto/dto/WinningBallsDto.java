package lotto.dto;

import java.util.List;

public record WinningBallsDto(
        List<Integer> winningNumbers,
        int bonusNumber
) {
}
