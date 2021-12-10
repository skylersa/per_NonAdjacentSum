//Skyler Sauer
//11/29/21
//rev 3
// notes:
//currently caps out at 100 numbers neg/pos, 
//can be increased by increasing the size of 
//the digit array

package per_NonAdjacentSum;

public class BaseN
{
	private int length;
	private int base;
	private int[] digit = new int[100];
	private int counter;
	private int min;

	public BaseN(int baseIn, int lengthIn, int minIn)
	{
		base = baseIn;
		length = lengthIn;
		counter = 0;
		min = minIn;

		for (int i = 0; i <= length; i++)
		{
			digit[i] = min;
		}

	}

	public void incrament()
	{
		this.incramentValBy(1);
	}

	public boolean incramentValBy(int byNum)
	{
		counter++;
		digit[length] += byNum;

		int cursor = length;

		while (cursor > 0)
		{
			if (digit[cursor] > base)
			{
				digit[cursor - 1]++;

				digit[cursor] = min + (base - digit[cursor] + 1);
			}
			cursor--;
		}
		if (cursor != 0)
			System.out.println("err, BaseN, incrament, cursor not 0 !!");
		if (digit[0] > base)
			return false;
		else
			return true;

	}

	// need method to ensure proper length of digit

	public boolean contains(int val)
	{
		int i = length;

		while (i > 0)
		{
//			System.out.println(i);
			if (digit[i] == val)
				return true;
			else
				i--;

		}

//		System.out.println("err, BaseN, contains, i start at 0 / length <= 0");
		return false;
	}

	public boolean containsOnly(int val)
	{
//		boolean hasOtherDigit = false;

		for (int i = length; i >= 0; i--)
		{
			if (digit[i] == val)
				continue;
			else
				return false;
		}
		return true;
	}

	public String getNumberString()
	{
		String result = "" + digit[0];
		int cursorGetCombo = 1;

		while (cursorGetCombo <= length)
		{
			result += (", " + digit[cursorGetCombo]);
			cursorGetCombo++;
		}
		return result;
	}

	public int getLength()
	{
		return length;
	}

	public int getCounter()
	{
		return counter;
	}

	public int sumDigits()
	{
		int total = 0;
		for (int i = 0; i <= length; i++)
		{
			total += digit[i];
		}
		return total;
	}

	public void setDigit(int place, int val)
	{

	}

	public int getDigit(int place)
	{
		return digit[place];
	}
}
