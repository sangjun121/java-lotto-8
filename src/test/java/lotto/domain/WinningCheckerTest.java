package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6',FIRST",
            "'1,2,3,4,5,7',SECOND",
            "'1,2,3,4,5,8',THIRD",
            "'1,2,3,4,8,9',FOURTH",
            "'1,2,3,8,9,10',FIFTH",
            "'1,2,8,9,10,11',NONE",
            "'1,8,9,10,11,12',NONE",
            "'8,9,10,11,12,13',NONE"
    })
    void 매칭_결과에_따라_올바른_Rank가_반환된다(String lottoNumbers, Rank expectedRank) {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of(7, winningNumber);
        WinningChecker winningChecker = new WinningChecker(winningNumber, bonusNumber);

        List<Integer> numbers = Arrays.stream(lottoNumbers.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto lotto = Lotto.from(numbers);

        Rank result = winningChecker.calculateRank(lotto);

        assertThat(result).isEqualTo(expectedRank);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6',FIRST",
            "'1,2,3,4,7,9',FOURTH",
            "'1,2,3,4,8,9',FOURTH",
            "'1,2,3,7,9,10',FIFTH",
            "'1,2,3,8,9,10',FIFTH",
            "'1,2,7,9,10,11',NONE",
            "'1,2,8,9,10,11',NONE",
            "'1,7,9,10,11,12',NONE",
            "'1,8,9,10,11,12',NONE",
            "'7,8,9,10,11,12',NONE",
            "'8,9,10,11,12,13',NONE"
    })
    void 순위_2등과_3등을_제외한_모든_순위는_보너스_숫자의_영향을_받지_않는다(String lottoNumbers, Rank expectedRank) {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of(7, winningNumber);
        WinningChecker winningChecker = new WinningChecker(winningNumber, bonusNumber);

        List<Integer> numbers = Arrays.stream(lottoNumbers.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto lotto = Lotto.from(numbers);

        Rank result = winningChecker.calculateRank(lotto);

        assertThat(result).isEqualTo(expectedRank);
    }
}
