package lotto.view;

import java.math.BigDecimal;
import java.util.List;
import lotto.controller.dto.WinningStatistic;

public class OutputView {
    private static final String LOTTO_COUNT_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_GUIDE = "\n당첨 통계\n---";
    private static final String MATCH_COUNT_MESSAGE = "%d개 일치";
    private static final String MATCH_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치";
    private static final String PRIZED_MONEY_MESSAGE = " (%,d원)";
    private static final String COUNT_MESSAGE = " - %d개";
    private static final String PROFIT_RATE_MESSAGE = "\n총 수익률은 %s%%입니다.";

    public void printLottoCount(int lottoCount) {
        System.out.printf(LOTTO_COUNT_MESSAGE, lottoCount);
    }

    public void printLottos(String lottos) {
        System.out.printf(lottos);
    }

    public void printWinningStatistics(List<WinningStatistic> winningStatistics) {
        System.out.println(WINNING_STATISTICS_GUIDE);
        for (WinningStatistic winningStatistic : winningStatistics) {
            printRankStatistic(winningStatistic);
        }
    }

    public void printProfitRate(BigDecimal profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate.toPlainString());
    }

    private void printRankStatistic(WinningStatistic winningStatistic) {
        if (winningStatistic.isBonus()) {
            System.out.printf(MATCH_COUNT_MESSAGE + MATCH_BONUS_NUMBER_MESSAGE + PRIZED_MONEY_MESSAGE + COUNT_MESSAGE,
                    winningStatistic.matchCount(), winningStatistic.prizeMoney(), winningStatistic.count());
        }

        System.out.printf(MATCH_COUNT_MESSAGE + PRIZED_MONEY_MESSAGE + COUNT_MESSAGE,
                winningStatistic.matchCount(), winningStatistic.prizeMoney(), winningStatistic.count());
    }
}
