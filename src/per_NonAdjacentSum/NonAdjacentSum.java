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
	private JTextField solutionOutputBox, solutionComboOutput;

	private int counterLength;

	private int valueArray[] = new int[NUMBER_OF_NUMBERS];

	private static Dimension boxSize = new Dimension(120, 20);

	public NonAdjacentSum()
	{
		super("Non Adjacent Sum");

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(NUMBER_OF_NUMBERS + 2, 2));

		addGraphics(panel);

		Container myContainer = getContentPane();
		myContainer.add(panel, BorderLayout.CENTER);
	}

	// creates the the necessary labels, inputs, outputs
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

		solutionOutputBox = new JTextField();
		solutionOutputBox.setHorizontalAlignment(SwingConstants.CENTER);
		solutionOutputBox.setEditable(false);
		solutionOutputBox.setBackground(Color.GRAY);
		panel.add(solutionOutputBox);

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
				outputAnswers("display", solveSum());
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
					if (Math.random() >= .5)
						numberInput[i].setText(Integer.toString((int) (Math.random() * RANGE_FOR_RAND)));
					else
						numberInput[i].setText(Integer.toString((int) (Math.random() * -RANGE_FOR_RAND)));
				}
			}
		});
		panel.add(buttonToRandomize);
	}

	public Answer solveSum()
	{
		BaseN searcher = new BaseN(NUMBER_OF_NUMBERS + 3, (NUMBER_OF_NUMBERS / 2) + (NUMBER_OF_NUMBERS % 2), 2);
		int currentBestTotal = -255;
		int totalForRound;
		int valueCursor;
		int searchCursor;
		counterLength = searcher.getLength();
		int[] currentSolutionSkips = new int[counterLength];
		int cursorCurrentSolution;
		int highSmallLateCombo[] = new int[counterLength];

		// runs once for each possible combination of skips
		while (!searcher.containsOnly(NUMBER_OF_NUMBERS + 3))
		{

			valueCursor = -2;
			totalForRound = 0;
			searchCursor = 0;
			cursorCurrentSolution = 0;

			// runs once for each skip
			// performs skip + adds skip to array thereof
			while (searchCursor < counterLength && valueCursor < NUMBER_OF_NUMBERS)
			{
				valueCursor += searcher.getDigit(searchCursor);
				if (valueCursor < NUMBER_OF_NUMBERS)
					totalForRound += valueArray[valueCursor];

				currentSolutionSkips[cursorCurrentSolution] = searcher.getDigit(searchCursor);

				searchCursor++;
				cursorCurrentSolution++;
			}

			if (totalForRound >= currentBestTotal)
			{
				currentBestTotal = totalForRound;
				for (int i = 0; i < counterLength; i++)
				{
					highSmallLateCombo[i] = currentSolutionSkips[i];
				}
			}

			searcher.incrament();
		}

		return new Answer(highSmallLateCombo, currentBestTotal);
	}

	// displays, console out the answer using Answer object with
	// getters/setters
	public void outputAnswers(String outType, Answer answer)
	{
		switch (outType)
		{

			case "display":
				solutionOutputBox.setText("" + answer.getTotal());

				int cursorOnVals = 0;
				int i = 0;

				String resultSkipsText = "" + (answer.getSkips()[0] - 2);
				cursorOnVals += answer.getSkips()[0] - 2;
				i++;
				while (i < counterLength)
				{

					cursorOnVals += answer.getSkips()[i];

					if (cursorOnVals <= NUMBER_OF_NUMBERS)
						resultSkipsText += (", " + answer.getSkips()[i]);
					i++;
				}

				solutionComboOutput.setText(resultSkipsText);
				highlight(answer.getSkips());

				break;
			case "console":

		}

	}

	public void highlight(int[] solutionSkips)
	{
		int cursorHighlighter = -2;
		int cursorCounterHighlighter = 0;

		while (cursorHighlighter <= NUMBER_OF_NUMBERS && cursorCounterHighlighter <= counterLength - 1)
		{
//			System.out.println("l" + solutionSkips[cursorCounterHighlighter]);
//			System.out.println("f" + cursorCounterHighlighter + "g" + cursorHighlighter);
//			System.out.println(cursorHighlighter + " " + NUMBER_OF_NUMBERS);

			cursorHighlighter += solutionSkips[cursorCounterHighlighter];
			if (cursorHighlighter <= NUMBER_OF_NUMBERS)
				numberInput[cursorHighlighter].setBackground(Color.YELLOW);
			cursorCounterHighlighter++;
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
							(int) (boxSize.getWidth() * 2),
							(int) ((NUMBER_OF_NUMBERS + 2) * boxSize.getHeight()));
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
