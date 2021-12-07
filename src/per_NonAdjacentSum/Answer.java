package per_NonAdjacentSum;

public class Answer
{
	private int[] skips;
	private int total;

	public Answer(int[] skipsIn, int totalIn)
	{
		total = totalIn;
		skips = skipsIn;
	}

	public int[] getSkips()
	{
		return skips;
	}

	public int getTotal()
	{
		return total;
	}

}
