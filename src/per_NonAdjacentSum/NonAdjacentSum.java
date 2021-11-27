package per_NonAdjacentSum;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NonAdjacentSum extends JFrame implements ActionListener
{

	private static final int NUMBER_OF_NUMBERS = 10;

	private JLabel[] numberLabel = new JLabel[NUMBER_OF_NUMBERS];
	private JTextField numberInput[] = new JTextField[NUMBER_OF_NUMBERS];
	private int valueArray[] = new int[NUMBER_OF_NUMBERS];
	private static Dimension myDimension = new Dimension(80, 26);

	public NonAdjacentSum()
	{
		super("Non Adjacent Sum");

		JPanel panel = new JPanel();

		// rows will be auto calculate based on how many it needs -
		// # of components probably needs to be even
		GridLayout panelLayout = new GridLayout(NUMBER_OF_NUMBERS + 1, 2);
		panel.setLayout(panelLayout);
//		JLabel labelNum1 = new JLabel("#1");
//		panel.add(labelNum1);

		/*
		 * creates the the necessary labels and input fields made
		 * possible by the constant NUMBER_OF_NUMBERS current
		 */

		int currentDisplayNum = 1;
		for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
		{

			numberLabel[i] = new JLabel("#" + currentDisplayNum);
			numberLabel[i].setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(numberLabel[i]);

			numberInput[i] = new JTextField();
			numberInput[i].setHorizontalAlignment(JTextField.LEFT);
			panel.add(numberInput[i]);

			currentDisplayNum++;
		}
//		ActionEvent solvePressed = new ActionEvent(this)
		JButton buttonToCalculate = new JButton("solve");
		buttonToCalculate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
					valueArray[i] = Integer.parseInt(numberInput[i].getText());
				solveSum();
			}
		});
		panel.add(buttonToCalculate);

		JButton buttonToRandomize = new JButton("rand");
		buttonToRandomize.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
					numberInput[i].setText(Integer.toString((int) (Math.random() * 20)));
			}
		});
		panel.add(buttonToRandomize);

		Container myContainer = getContentPane();
		myContainer.add(panel, BorderLayout.CENTER);
//		myContainer.add(buttonToCalculate);
//		myContainer.add(buttonToRandomize);

//		myContainer.add(myContainer);
	}

	public int solveSum()
	{
		int maxNonAdjacentSum = 0;
		int searchCounter = 0;
		String binarySearchString = "0";
		int binarySearcherCursor;
		int valueCursor;
		int currentBestTotal = -1;
		String highestEarlyCombo = "L";
		int totalForRound;

		// runs once for each possible combonation of skips
		while (binarySearchString.contains("0"))
		{
			binarySearchString = Integer.toBinaryString(searchCounter);
			while (binarySearchString.length() < NUMBER_OF_NUMBERS / 2/* + (NUMBER_OF_NUMBERS % 2) */)
			{
				binarySearchString = "0" + binarySearchString;
			}
//			System.out.println("combo of skips for this round: " + binarySearchString);

			valueCursor = -2;
			totalForRound = 0;
			binarySearcherCursor = 0;
			// runs once for each skip
			while (binarySearcherCursor <= binarySearchString.length() - 1)
			{
				valueCursor += 2;
//				System.out.println(binarySearchString.substring(binarySearcherCursor, binarySearcherCursor + 1));
				if (binarySearchString.substring(binarySearcherCursor, binarySearcherCursor + 1).equals("1"))
					valueCursor++;

				if (valueCursor < NUMBER_OF_NUMBERS)
					totalForRound = totalForRound + valueArray[valueCursor];
				binarySearcherCursor++;
			}
			if (totalForRound > currentBestTotal)
			{
				currentBestTotal = totalForRound;
				highestEarlyCombo = binarySearchString;
			}
//			System.out.println("for that round: " + totalForRound + ", best so far: " + currentBestTotal);
			searchCounter++;
		}
		// gives final answer with first combo that got high answer
		System.out.println("the highest non adjacent sum is " + currentBestTotal + " using the combonation: "
				+ highestEarlyCombo);
		return maxNonAdjacentSum;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	public static void main(String[] args)
	{
		NonAdjacentSum window = new NonAdjacentSum();
		window.setBounds(	300,
							50,
							(int) (myDimension.getWidth() * 2),
							(int) ((NUMBER_OF_NUMBERS) * myDimension.getHeight()));
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
