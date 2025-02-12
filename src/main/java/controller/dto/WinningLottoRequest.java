package controller.dto;

import java.util.List;

public record WinningLottoRequest(
        List<Integer> numbers,
        int bonusNumber
) {
}
