package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningCheckerTest {

    @Test
    void 로또_번호가_당첨_번호와_3개_일치하는_경우_일치_개수는_3이_반환된다() {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of(45, winningNumber);
        WinningChecker winningChecker = new WinningChecker(winningNumber, bonusNumber);

        Lotto lotto = Lotto.from(List.of(1, 2, 3, 10, 11, 12));

        int result = winningChecker.calculateRank(lotto).getMatchCount();

        assertThat(result).isEqualTo(3);
    }

    // TODO: 우회 테스트. 수정 필요
    @Test
    void 로또_번호가_보너스_번호와_일치하는_경우_참_값이_반환된다() {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of(45, winningNumber);

        Lotto lotto = Lotto.from(List.of(7, 8, 9, 10, 11, 45));

        boolean result = lotto.getNumbers().contains(bonusNumber.getValue());

        assertThat(result).isTrue();
    }
}
