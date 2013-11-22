package tp.coding_challenge;

public interface Constraint
{
    public static Constraint NONE = message -> true;
    public static Constraint INSTRUCTION_TYPE = message -> message.instruction_type > 0 && message.instruction_type < 100;
    public static Constraint PRODUCT_CODE = message -> message.product_code > 0;
    public static Constraint QUANTITY = message -> message.quantity > 0;
    public static Constraint UOM = message -> message.UOM >= 0 && message.UOM < 256;
    public static Constraint TIME_STAMP = message -> message.time_stamp > 0;
    public static Constraint DEFAULT = from(INSTRUCTION_TYPE, PRODUCT_CODE, QUANTITY, UOM, TIME_STAMP);

    public static Constraint from(final Constraint... subconstraints)
    {
        return message ->
                {
                    for (final Constraint constraint : subconstraints)
                    {
                        if (! constraint.is_satisfied_by(message))
                            return false;
                    }

                    return true;
                };
    }

    public boolean is_satisfied_by(Message message);
}
