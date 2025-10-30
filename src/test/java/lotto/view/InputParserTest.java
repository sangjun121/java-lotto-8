package lotto.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.LottoError;
import lotto.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        this.inputParser = new InputParser();
    }

    @Test
    void 로또_구입_금액_입력_문자열이_null인_경우_예외가_발생한다() {
        String input = null;

        assertThatThrownBy(() -> inputParser.parsePurchaseAmount(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(LottoError.PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void 로또_구입_금액_입력_문자열이_empty거나_blank인_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputParser.parsePurchaseAmount(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(LottoError.PURCHASE_AMOUNT_INPUT_NULL_OR_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"amount", "number"})
    void 로또_구입_금액_입력_문자열이_숫자가_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputParser.parsePurchaseAmount(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(LottoError.PURCHASE_AMOUNT_INPUT_NOT_NUMBER.getMessage());
    }

    @Test
    void 당첨_번호_입력_문자열이_null인_경우_예외가_발생한다() {
        String input = null;

        assertThatThrownBy(() -> inputParser.parseWinningNumber(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(LottoError.WINNING_NUMBER_INPUT_NULL_OR_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void 당첨_번호_입력_문자열이_empty거나_blank인_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputParser.parseWinningNumber(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(LottoError.WINNING_NUMBER_INPUT_NULL_OR_BLANK.getMessage());
    }
}
