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
//	private JTextField inputNum1, inputNum2, inputNum3;
	private final int NUMBER_OF_NUMBERS = 7;
	private JLabel[] numberLabel = new JLabel[NUMBER_OF_NUMBERS];
	private JTextField numberInput[] = new JTextField[NUMBER_OF_NUMBERS];
	int valueArray[] = new int[NUMBER_OF_NUMBERS];
//	private Dimension labelDimension = new Dimension(5, 25);

	public NonAdjacentSum()
	{
		super("Non Adjacent Sum");

		JPanel panel = new JPanel();

		// rows - might want to add more for visualisation/ output of problem
		// 0 columns because it is ignored anyway, should be 2
		GridLayout panelLayout = new GridLayout(NUMBER_OF_NUMBERS + 1, 0);
		panel.setLayout(panelLayout);
//		JLabel labelNum1 = new JLabel("#1");
//		panel.add(labelNum1);

		/*
		 * creates the the necesary labels and input fields made possible by the
		 * constant NUMBER_OF_NUMBERS current
		 */

		int currentDisplayNum = 0;

		for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
		{
			currentDisplayNum = i + 1;
			numberLabel[i] = new JLabel("#" + currentDisplayNum);
			numberLabel[i].setHorizontalAlignment(SwingConstants.RIGHT);
//			numberLabel[i].setMaximumSize(labelDimension);
			panel.add(numberLabel[i]);

			numberInput[i] = new JTextField();
			numberInput[i].setHorizontalAlignment(JTextField.RIGHT);
			panel.add(numberInput[i]);

		}
		JButton buttonToCalculate = new JButton("Find highest non adjacent sum");
		buttonToCalculate.addActionListener(this);
		panel.add(buttonToCalculate);

		JButton buttonToRandomize = new JButton("randomize values");
		buttonToRandomize.addActionListener(this);
		panel.add(buttonToRandomize);

//		inputNum1 = new JTextField(5);
//		inputNum1.setHorizontalAlignment(JTextField.RIGHT);
//		panel.add(inputNum1);

		Container myContainer = getContentPane();
		myContainer.add(panel, BorderLayout.CENTER);
//		myContainer.add(buttonToCalculate, BorderLayout.SOUTH);
//		myContainer.add(myContainer)
	}

//	public void createInputWithLabel(int labelNumber, )
//	{
//		
//	}

	public void actionPerformed(ActionEvent e)
	{

		for (int i = 0; i < NUMBER_OF_NUMBERS; i++)
		{
			String tempParsingValue;
			System.out.println("l");
			tempParsingValue = numberInput[i].getText();
			valueArray[i] = Integer.parseInt(tempParsingValue);
		}
		System.out.println(valueArray);
	}

	public static void main(String[] args)
	{
		NonAdjacentSum window = new NonAdjacentSum();
		window.setBounds(300, 300, 200, 180);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
