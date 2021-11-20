package per_NonAdjacentSum;

import java.awt.BorderLayout;
import java.awt.Container;
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

	private final int NUMBER_OF_NUMBERS = 7;

	private JLabel[] numberLabel = new JLabel[NUMBER_OF_NUMBERS];
	private JTextField numberInput[] = new JTextField[NUMBER_OF_NUMBERS];
	private int valueArray[] = new int[NUMBER_OF_NUMBERS];

	private int buttonPressed = 0;

	public NonAdjacentSum()
	{
		super("Non Adjacent Sum");

		JPanel panel = new JPanel();

		// rows will be auto calculate based on how many it needs -
		// # of components probably needs to be even
		GridLayout panelLayout = new GridLayout(0, 2);
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
				String tempParsingValue;
				for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
				{
					tempParsingValue = numberInput[i].getText();
					valueArray[i] = Integer.parseInt(tempParsingValue);
				}

				{ // non block block for debug
					System.out.println(valueArray[0]);
					System.out.println(valueArray[1]);
					System.out.println(valueArray[2]);
					System.out.println(valueArray[3]);
					System.out.println(valueArray[4]);
					System.out.println(valueArray[5]);
					System.out.println(valueArray[6]);
				}

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
//		myContainer.add(buttonToCalculate, BorderLayout.SOUTH);
//		myContainer.add(myContainer)
	}

	public int solveSum(int[] values)
	{
		int maxNonAdjacentSum = 0;

		return maxNonAdjacentSum;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	public static void main(String[] args)
	{
		NonAdjacentSum window = new NonAdjacentSum();
		window.setBounds(300, 300, 100, 180);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
