package per_NonAdjacentSum;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NonAdjacentSum extends JFrame implements ActionListener
{
	private JTextField inputNum1, inputNum2, inputNum3;

	public NonAdjacentSum()
	{
		super("Non Adjacent Sum");

		JPanel panel = new JPanel();

		GridLayout panelLayout = new GridLayout(3, 2);
		panel.setLayout(panelLayout);

		JLabel labelNum1 = new JLabel("#1");
		panel.add(labelNum1);

		inputNum1 = new JTextField(5);
		inputNum1.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(inputNum1);

		Container myContainer = getContentPane();
		myContainer.add(panel, BorderLayout.CENTER);
//		myContainer.add(myContainer)
	}

	public static void main(String[] args)
	{
		NonAdjacentSum window = new NonAdjacentSum();
		window.setBounds(300, 300, 200, 180);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{

	}

}
