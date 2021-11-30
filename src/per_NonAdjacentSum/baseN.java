package per_NonAdjacentSum;

public class BaseN
{
	private int length;
	private int base;
	private int[] vals;
	private int base10Counter;
	private int cursor;

	public BaseN(int baseIn, int lengthIn)
	{
		base = baseIn;
		length = lengthIn;
		base10Counter = 0;

		for (int i = 0; i < length; i++)
		{
			vals[i] = 0;
		}

	}

	public boolean incramentVal(int byNum)
	{
		base10Counter++;
		vals[vals.length] += byNum;

		cursor = vals.length;

		while (cursor > 0)
		{
			if (vals[cursor] > base)
			{
				vals[cursor - 1]++;
				vals[cursor] -= base;
			}
			cursor--;
		}
		if (cursor != 0)
			System.out.println("err, BaseN, incrament, cursor not 0 !!");
		if (vals[0] > base)
			return false;
		else
			return true;

	}

	public int getCounter()
	{
		return base10Counter;
	}

	public int getVal(int place)
	{
		return vals[place];
	}
}
