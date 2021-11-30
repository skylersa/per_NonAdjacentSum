// skyler sauer
// 11/27
// rev 4
//notes:
//
package per_NonAdjacentSum;

import java.awt.BorderLayout;
import java.awt.Color;
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

	private static final int NUMBER_OF_NUMBERS = 30;
	private final int RANGE_FOR_RAND = 30;

	private JLabel[] numberLabel = new JLabel[NUMBER_OF_NUMBERS];
	private JTextField numberInput[] = new JTextField[NUMBER_OF_NUMBERS];
	private JTextField solutionOutput, solutionComboOutput;

	private int valueArray[] = new int[NUMBER_OF_NUMBERS];

	private static Dimension myDimension = new Dimension(120, 26);

	public NonAdjacentSum()
	{
		super("Non Adjacent Sum");

		JPanel panel = new JPanel();

		// rows will be auto calculate based on how many it needs -
		// # of components probably needs to be even
		GridLayout panelLayout = new GridLayout(NUMBER_OF_NUMBERS + 2, 2);
		panel.setLayout(panelLayout);

//		creates the the necessary labels and input fields made
//		possible by the constant NUMBER_OF_NUMBERS current

		for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
		{
			numberLabel[i] = new JLabel("#" + (i + 1));
			numberLabel[i].setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(numberLabel[i]);

			numberInput[i] = new JTextField();
			numberInput[i].setHorizontalAlignment(JTextField.LEFT);
			panel.add(numberInput[i]);
		}

		solutionOutput = new JTextField();
		solutionOutput.setHorizontalAlignment(SwingConstants.CENTER);
		solutionOutput.setEditable(false);
		solutionOutput.setBackground(Color.GRAY);
		panel.add(solutionOutput);

		solutionComboOutput = new JTextField();
		solutionComboOutput.setHorizontalAlignment(SwingConstants.CENTER);
		solutionComboOutput.setEditable(false);
		solutionComboOutput.setBackground(Color.GRAY);

		panel.add(solutionComboOutput);

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
				{
					if (Math.random() > .5)
						numberInput[i].setText(Integer.toString((int) (Math.random() * RANGE_FOR_RAND)));
					else
						numberInput[i].setText(Integer.toString((int) (Math.random() * -RANGE_FOR_RAND)));
				}
			}
		});
		panel.add(buttonToRandomize);

		Container myContainer = getContentPane();
		myContainer.add(panel, BorderLayout.CENTER);
	}

	public int solveSum()
	{
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
			while (binarySearchString.length() < NUMBER_OF_NUMBERS / 2)
			{
				binarySearchString = "0" + binarySearchString;
			}
//			System.out.println("combo of skips for this round: " + binarySearchString);

			valueCursor = -2;
			binarySearcherCursor = 0;
			totalForRound = 0;

			// runs once for each skip
			while (binarySearcherCursor <= binarySearchString.length() - 1)
			{
				valueCursor += 2;
				valueCursor += Integer
						.parseInt(binarySearchString.substring(binarySearcherCursor, binarySearcherCursor + 1));

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
		solutionOutput.setText("" + currentBestTotal);
		solutionComboOutput.setText(highestEarlyCombo);

		return currentBestTotal;
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
