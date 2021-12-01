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

	private static final int NUMBER_OF_NUMBERS = 7;
	private final int RANGE_FOR_RAND = 15;

	private JLabel[] numberLabel = new JLabel[NUMBER_OF_NUMBERS];
	private JTextField numberInput[] = new JTextField[NUMBER_OF_NUMBERS];
	private JTextField solutionOutput, solutionComboOutput;

	private int counterLength;

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

		addGraphics(panel);

		Container myContainer = getContentPane();
		myContainer.add(panel, BorderLayout.CENTER);
	}

	private void addGraphics(JPanel panel)
	{
		for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
		{
			numberLabel[i] = new JLabel("#" + (i));
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
				{
					numberInput[i].setBackground(Color.WHITE);
					valueArray[i] = Integer.parseInt(numberInput[i].getText());
				}
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
					numberInput[i].setBackground(Color.WHITE);
					if (Math.random() > .5)
						numberInput[i].setText(Integer.toString((int) (Math.random() * RANGE_FOR_RAND)));
					else
						numberInput[i].setText(Integer.toString((int) (Math.random() * -RANGE_FOR_RAND)));
				}
			}
		});
		panel.add(buttonToRandomize);
	}

	public int solveSum()
	{
		BaseN searcher = new BaseN(NUMBER_OF_NUMBERS + 3, (NUMBER_OF_NUMBERS / 2) + (NUMBER_OF_NUMBERS % 2), 2);
		int currentBestTotal = -255;
		int totalForRound;
		int valueCursor;
		int searchCursor;
		counterLength = searcher.getLength();
		System.out.println(searcher.getLength() + "j");
		int[] currentSolutionSkips = new int[counterLength];
		int cursorCurrentSolution;
		int highestEarlyCombo[] = new int[counterLength];
//		int maxCount = (int) Math.pow((double) NUMBER_OF_NUMBERS - 2, (double) NUMBER_OF_NUMBERS - 2);

		// runs once for each possible combination of skips

		while (!searcher.containsOnly(NUMBER_OF_NUMBERS + 3))
		{
//			System.out.println("combo of skips for this round: " + binarySearchString);

			valueCursor = -2;
			totalForRound = 0;
			searchCursor = 0;
			cursorCurrentSolution = 0;

			// runs once for each skip
			// performs skip + add that value to the total for that
			// round
			while (searchCursor < counterLength)
			{
				valueCursor += searcher.getDigit(searchCursor);
				if (valueCursor < NUMBER_OF_NUMBERS)
				{
//					numberInput[valueCursor].setBackground(Color.YELLOW);
					totalForRound += valueArray[valueCursor];
					currentSolutionSkips[cursorCurrentSolution] = searcher.getDigit(searchCursor);
				}
				searchCursor++;
				cursorCurrentSolution++;
			}

//			System.out.println(totalForRound + "k" + currentBestTotal);
			if (totalForRound > currentBestTotal)
			{
				currentBestTotal = totalForRound;
				for (int i = 0; i < counterLength; i++)
					highestEarlyCombo[i] = currentSolutionSkips[i];
			}

//			System.out.println("for that round: " + totalForRound + ", best so far: " + currentBestTotal);
			searcher.incrament();
		}

		// gives final answer with first combo that got high answer
		solutionOutput.setText("" + currentBestTotal);
		{
			String highestEarlyComboOutput = "" + highestEarlyCombo[0];
			int cursorGetCombo = 1;

			while (cursorGetCombo < counterLength)
			{
				highestEarlyComboOutput += (", " + highestEarlyCombo[cursorGetCombo]);
				cursorGetCombo++;
			}
			solutionComboOutput.setText(highestEarlyComboOutput);
		}
		System.out.println(searcher.getNumberString());

		highlight(highestEarlyCombo);

		return currentBestTotal;
	}

	public void highlight(int[] solutionSkips)
	{
		int cursorHighlighter = -2;
//		System.out.println(searcher.getNumberString());

		int cursorCounterHighlighter = 0;
		cursorHighlighter += solutionSkips[cursorCounterHighlighter];
		while (cursorHighlighter < NUMBER_OF_NUMBERS && cursorCounterHighlighter < counterLength)
		{
			cursorCounterHighlighter++;
			System.out.println("l" + solutionSkips[cursorCounterHighlighter]);
			numberInput[cursorHighlighter].setBackground(Color.YELLOW);
			System.out.println("f" + cursorCounterHighlighter + "g" + cursorHighlighter);

			cursorHighlighter += solutionSkips[cursorCounterHighlighter];
			System.out.println(cursorHighlighter + " " + NUMBER_OF_NUMBERS);
		}

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
