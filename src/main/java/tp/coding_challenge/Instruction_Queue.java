package tp.coding_challenge;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Instruction_Queue
{
	static final int DEFAULT_INITIAL_CAPACITY = 1000;

	final Constraint constraint;
	final PriorityBlockingQueue<Message> delegate;

	public Instruction_Queue()
	{
		this(Constraint.DEFAULT, Prioritisation.by_instruction_type);
	}

	Instruction_Queue(final Constraint constraint, final Comparator<Message> comparator)
	{
		delegate = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY, comparator);

		this.constraint = constraint;
	}

	public Instruction_Queue add(final Message message)
	{
		require(constraint.is_satisfied_by(message));

		delegate.add(message);

		return this;
	}

	public boolean is_empty()
	{
		return delegate.isEmpty();
	}

	public Message next_message()
	{
		return delegate.peek();
	}

	public Instruction_Queue remove(final Message message)
	{
		delegate.remove(message);

		return this;
	}

	public int size()
	{
		return delegate.size();
	}

	static void require(final boolean constraint_satisfied)
	{
		if (!constraint_satisfied)
			throw new Invalid_Message_Exception();
	}
}
