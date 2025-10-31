package lotto.domain;

public class WinningChecker {
    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    public WinningChecker(WinningNumber winningNumber,  BonusNumber bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    //TODO: 반환값 Rank로 수정 예정
    public void calculateRank(Lotto lotto) {
        int matchCount = countMatchedWithWinningNumbers(lotto);
    }

    private int countMatchedWithWinningNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumber.getNumbers()::contains)
                .count();
    }
}
