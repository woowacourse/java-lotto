package dto;

import domain.WinningCase;
import java.util.Map;

public record DrawResultDto(Map<WinningCase, Integer> drawResult, double earnMoneyRatio) {
}
