package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumber;

public class Lottos {
    private static final int COUNT_UNIT = 1;
    private static final int PERCENTAGE_SCALE = 100;
    private static final int CALCULATION_SCALE = 3;
    private static final int DISPLAY_SCALE = 1;

    private final List<Lotto> values;

    public Lottos(List<Lotto> values) {
        this.values = List.copyOf(values);
    }

    public List<Lotto> getValues() {
        return values;
    }

    public Map<Rank, Integer> countByRank(WinningNumber winningNumber, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

        for (Lotto lotto : values) {
            Rank rank = Rank.valueOf(
                    winningNumber.countMatchesWith(lotto),
                    bonusNumber.isMatchedWith(lotto)
            );
            mergeCount(rankCounts, rank);
        }
        return rankCounts;
    }

    public BigDecimal calculateTotalPrize(WinningNumber winningNumber, BonusNumber bonusNumber) {
        return values.stream()
                .map(lotto -> {
                    Rank rank = Rank.valueOf(
                            winningNumber.countMatchesWith(lotto),
                            bonusNumber.isMatchedWith(lotto)
                    );
                    return BigDecimal.valueOf(rank.getPrizeMoney());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateProfitRate(WinningNumber winningNumber, BonusNumber bonusNumber, int purchaseAmount) {
        BigDecimal totalPrizeMoney = calculateTotalPrize(winningNumber, bonusNumber);

        return totalPrizeMoney
                .divide(BigDecimal.valueOf(purchaseAmount), CALCULATION_SCALE, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(PERCENTAGE_SCALE))
                .setScale(DISPLAY_SCALE, RoundingMode.HALF_UP);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : values) {
            sb.append(lotto.toString())
                    .append("\n");
        }

        return sb.toString().trim();
    }

    private void mergeCount(Map<Rank, Integer> rankCounts, Rank rank) {
        if (rank != Rank.NONE) {
            rankCounts.merge(rank, COUNT_UNIT, Integer::sum);
        }
    }
}
