package tp.coding_challenge;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

@SuppressWarnings({"unused", "unchecked"})
public class Instruction_Queue_Spec
{
    Instruction_Queue queue;
    @Mock Constraint constraint;
    @Mock Message message;
    Exception rejection;

    @Test
    public void adds_valid_message()
    {
        assertThat(queue.add(message).next_message(), is(message));
    }

    @Before
    public void DEFINITIONS()
    {
        initMocks(this);
        queue = new Instruction_Queue(constraint, mock(Comparator.class));
        given(constraint.is_satisfied_by(message)).willReturn(true);
    }

    @Test
    public void knows_if_it_is_empty()
    {
        assertThat(queue.is_empty(), is(true));
    }

    @Test
    public void knows_if_it_is_not_empty()
    {
        assertThat(queue.add(message).is_empty(), is(false));
    }

    @Test
    public void knows_its_size()
    {
        assertThat(queue.add(message).size(), is(1));
    }

    @Test
    public void knows_next_message()
    {
        assertThat(queue.add(message).next_message(), is(message));
    }

    @Test
    public void rejects_addition_of_invalid_message()
    {
        given(constraint.is_satisfied_by(message)).willReturn(false);

        WHEN:try
        {
            queue.add(message);
        }
        catch (final Exception exception)
        {
            rejection = exception;
        }

        THEN: assertThat(rejection, is(instanceOf(Invalid_Message_Exception.class)));

        AND: queue.is_empty();
    }

    @Test
    public void removes_message()
    {
        GIVEN: queue.add(message);

        WHEN: queue.remove(message);

        THEN: assertThat(queue.next_message(), is(nullValue()));
    }
}
