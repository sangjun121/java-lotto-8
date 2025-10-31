package lotto.domain;

public class WinningChecker {
    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    public WinningChecker(WinningNumber winningNumber, BonusNumber bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto lotto) {
        int matchedCountWithWinningNumbers = countMatchedWithWinningNumbers(lotto);
        boolean isMatchedWithBonusNumber = isMatchedWithBonusNumber(lotto);
        return Rank.valueOf(matchedCountWithWinningNumbers, isMatchedWithBonusNumber);
    }

    private int countMatchedWithWinningNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumber.getNumbers()::contains)
                .count();
    }

    private boolean isMatchedWithBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber.getValue());
    }
}
