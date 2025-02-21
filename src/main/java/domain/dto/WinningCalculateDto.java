package domain.dto;

import domain.WinningCase;
import java.util.Map;

public record WinningCalculateDto(Map<WinningCase, Integer> winningCalculateResult, double earnMoney) {
}
