package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private static final int COUNT_UNIT = 1;

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Map<Rank, Integer> countByRank(WinningNumber winningNumber, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

        for (Lotto lotto : lottos) {
            Rank rank = Rank.valueOf(
                    winningNumber.countMatchesWith(lotto),
                    bonusNumber.isMatchedWith(lotto)
            );
            mergeCount(rankCounts, rank);
        }
        return rankCounts;
    }

    public int calculateTotalPrize(WinningNumber winningNumber, BonusNumber bonusNumber) {
        return lottos.stream()
                .mapToInt(lotto -> {
                    Rank rank = Rank.valueOf(
                            winningNumber.countMatchesWith(lotto),
                            bonusNumber.isMatchedWith(lotto)
                    );
                    return rank.getPrizeMoney();
                })
                .sum();
    }

    private void mergeCount(Map<Rank, Integer> rankCounts, Rank rank) {
        if (rank != Rank.NONE) {
            rankCounts.merge(rank, COUNT_UNIT, Integer::sum);
        }
    }
}
