package tp.coding_challenge;

import java.util.Comparator;

import static java.util.Comparator.comparing;

public class Prioritisation
{
	public static final Comparator<Message> by_instruction_type =  comparing(Message::instruction_type);
}
