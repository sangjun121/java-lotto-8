package lotto.controller.dto;

public record WinningStatistic(int matchCount,
                               int prizeMoney,
                               int count,
                               boolean isBonus) {
}
