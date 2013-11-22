package tp.coding_challenge;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Message_Prioritisation_Spec
{
	Instruction_Queue queue = new Instruction_Queue(Constraint.NONE, Prioritisation.by_instruction_type);

	Message high_message = new Message(), medium_message = new Message(), low_message = new Message();

	@Before public void DEFINITIONS()
	{
		high_message.instruction_type = 10;
		medium_message.instruction_type = 90;
		low_message.instruction_type = 91;
	}

	@Test public void prioritises_high_over_low()
	{
		assertThat(queue.add(low_message).add(high_message).next_message(), is(high_message));
	}

	@Test public void prioritises_high_over_medium()
	{
		assertThat(queue.add(medium_message).add(high_message).next_message(), is(high_message));
	}

	@Test public void prioritises_medium_over_low()
	{
		assertThat(queue.add(low_message).add(medium_message).next_message(), is(medium_message));
	}
}
