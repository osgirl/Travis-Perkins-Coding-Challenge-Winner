package tp.coding_challenge;

import static java.lang.String.format;

public class Message
{
	public static final int DEFAULT_VALUE = 1;

	int instruction_type, product_code, quantity, UOM, time_stamp;

	public Message()
	{
		this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE);
	}

	public Message(final int instruction_type, final int product_code, final int quantity, final int UOM, final int time_stamp)
	{
		this.instruction_type = instruction_type;
		this.product_code = product_code;
		this.quantity = quantity;
		this.UOM = UOM;
		this.time_stamp = time_stamp;
	}

    public int instruction_type()
    {
        return instruction_type;
    }

    public int product_code()
    {
        return product_code;
    }

    public int quantity()
    {
        return quantity;
    }

    public int UOM()
    {
        return UOM;
    }

    public int time_stamp()
    {
        return time_stamp;
    }

	public String toString()
	{
		return format("Message instruction type: %s product code: %s quantity: %s UOM: %s timestamp: %s",
				instruction_type, product_code,quantity, UOM, time_stamp);
	}
}
