package tp.coding_challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Theories.class)
public class Message_Validation_Spec
{
	@Mock Constraint satisfied_subconstraint, unsatisfied_subconstraint ;
    @Mock Message message  ;

	@DataPoints public static int[] values = { -1, 0, 1, 99, 100, 255, 256 };

    @Before public void DEFINITIONS()
    {
        initMocks(this);
        given(satisfied_subconstraint.is_satisfied_by(message)).willReturn(true);
    }

    @Test public void ands_subconstraints()
    {
        assertThat(Constraint.from(satisfied_subconstraint, satisfied_subconstraint).is_satisfied_by(message), is(true));
        assertThat(Constraint.from(unsatisfied_subconstraint, satisfied_subconstraint).is_satisfied_by(message), is(false));
        assertThat(Constraint.from(satisfied_subconstraint, unsatisfied_subconstraint).is_satisfied_by(message), is(false));
        assertThat(Constraint.from(unsatisfied_subconstraint, unsatisfied_subconstraint).is_satisfied_by(message), is(false));
    }

	@Theory public void accepts_valid_instruction_type(final int value)
	{
		assumeTrue(value > 0 && value < 100);

		assertThat(Constraint.INSTRUCTION_TYPE.is_satisfied_by(new Message(value, 0, 0, 0, 0)), is(true));
	}

	@Theory public void accepts_valid_product_code(final int value)
	{
		assumeTrue(value > 0);

		assertThat(Constraint.PRODUCT_CODE.is_satisfied_by(new Message(0, value, 0, 0, 0)), is(true));
	}

	@Theory public void accepts_valid_quantity(final int value)
	{
		assumeTrue(value > 0);

		assertThat(Constraint.QUANTITY.is_satisfied_by(new Message(0, 0, value, 0, 0)), is(true));
	}

	@Theory public void accepts_valid_time_stamp(final int value)
	{
		assumeTrue(value > 0);

		assertThat(Constraint.TIME_STAMP.is_satisfied_by(new Message(value, 0, 0, 0, value)), is(true));
	}

	@Theory public void accepts_valid_UOM(final int value)
	{
		assumeTrue(value >= 0 && value < 256);

		assertThat(Constraint.UOM.is_satisfied_by(new Message(0, 0, 0, value, 0)), is(true));
	}

	@Theory public void rejects_invalid_instruction_type(final int value)
	{
		assumeTrue(value <= 0 || value >= 100);

		assertThat(Constraint.INSTRUCTION_TYPE.is_satisfied_by(new Message(value, 0, 0, 0, 0)), is(false));
	}

	@Theory public void rejects_invalid_product_code(final int value)
	{
		assumeTrue(value <= 0);

		assertThat(Constraint.PRODUCT_CODE.is_satisfied_by(new Message(0, value, 0, 0, 0)), is(false));
	}

	@Theory public void rejects_invalid_quantity(final int value)
	{
		assumeTrue(value <= 0);

		assertThat(Constraint.QUANTITY.is_satisfied_by(new Message(0, 0, value, 0, 0)), is(false));
	}

	@Theory public void rejects_invalid_time_stamp(final int value)
	{
		assumeTrue(value <= 0);

		assertThat(Constraint.TIME_STAMP.is_satisfied_by(new Message(0, 0, 0, 0, value)), is(false));
	}

	@Theory public void rejects_invalid_UOM(final int value)
	{
		assumeTrue(value < 0 || value >= 256);

		assertThat(Constraint.UOM.is_satisfied_by(new Message(0, 0, 0, value, 0)), is(false));
	}
}
