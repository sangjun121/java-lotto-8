package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumber;
import lotto.exception.InvalidBonusNumberException;
import lotto.exception.LottoError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
    private static final List<Integer> VALID_WINNING_NUMBER = List.of(1, 2, 3, 4, 5, 6);

    @Test
    void 보너스_숫자가_음수인_경우_예외가_발생한다() {
        WinningNumber winningNumber = WinningNumber.from(VALID_WINNING_NUMBER);

        assertThatThrownBy(() -> BonusNumber.from(-1, winningNumber))
                .isInstanceOf(InvalidBonusNumberException.class)
                .hasMessage(LottoError.INVALID_BONUS_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 보너스_숫자가_1부터_45사이가_아닌_경우_예외가_발생한다(int value) {
        WinningNumber winningNumber = WinningNumber.from(VALID_WINNING_NUMBER);

        assertThatThrownBy(() -> BonusNumber.from(value, winningNumber))
                .isInstanceOf(InvalidBonusNumberException.class)
                .hasMessage(LottoError.INVALID_BONUS_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 보너스_숫자가_로또_번호와_중복인_경우_예외가_발생한다(int value) {
        WinningNumber winningNumber = WinningNumber.from(VALID_WINNING_NUMBER);

        assertThatThrownBy(() -> BonusNumber.from(value, winningNumber))
                .isInstanceOf(InvalidBonusNumberException.class)
                .hasMessage(LottoError.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER.getMessage());
    }

    @Test
    void 로또_번호가_보너스_번호와_일치하는_경우_참_값이_반환된다() {
        WinningNumber winningNumber = WinningNumber.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(45, winningNumber);

        Lotto lotto = Lotto.from(List.of(7, 8, 9, 10, 11, 45));

        boolean result = bonusNumber.isMatchedWith(lotto);

        assertThat(result).isTrue();
    }
}
