package lotto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.controller.dto.WinningStatistic;
import lotto.domain.BonusNumber;
import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningNumber;

public class LottoService {
    private static final int INITAL_SUM = 0;

    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public Lottos generateLotto(PurchaseAmount purchaseAmount) {
        return lottoMachine.generateLottos(purchaseAmount.calculateLottoCount());
    }

    public List<WinningStatistic> calculateWinningStatistic(Lottos lottos, WinningNumber winningNumber,
                                                            BonusNumber bonusNumber) {
        Map<Rank, Integer> rankCounts = lottos.countByRank(winningNumber, bonusNumber);
        return createWinningStatistics(rankCounts);
    }

    public BigDecimal calculateProfitRate(Lottos lottos, WinningNumber winningNumber, BonusNumber bonusNumber,
                                          PurchaseAmount purchaseAmount) {
        return lottos.calculateProfitRate(winningNumber, bonusNumber, purchaseAmount.getValue());
    }

    private List<WinningStatistic> createWinningStatistics(Map<Rank, Integer> rankCounts) {
        List<WinningStatistic> winningStatistics = new ArrayList<>();

        List<Rank> winningRanks = Rank.winningRanks();

        for (Rank rank : winningRanks) {
            int rankCount = rankCounts.getOrDefault(rank, INITAL_SUM);
            winningStatistics.add(createWinningStatistic(rank, rankCount));
        }

        winningStatistics.sort(Comparator.comparingInt(WinningStatistic::prizeMoney));

        return winningStatistics;
    }

    private WinningStatistic createWinningStatistic(Rank rank, int rankCount) {
        return new WinningStatistic(
                rank.getMatchCount(),
                rank.getPrizeMoney(),
                rankCount,
                rank.isBonus());
    }
}
