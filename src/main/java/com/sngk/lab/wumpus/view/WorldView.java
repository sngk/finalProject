package com.sngk.lab.wumpus.view;

import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Andrew U. (SID: s0253796)
 * @version 30-Sep-2015
 * This class is view part of our project
 * it creates all GUI and action listeners for buttons.
 */
@Singleton
public class WorldView {

	@Inject
	private WorldView worldView;

	private JButton newGameBtn = new JButton();
	private JButton saveGameBtn = new JButton();
	private JButton loadGameBtn = new JButton();
	private JButton helpBtn = new JButton();
	private JButton inputButton = new JButton();
	private JButton clearButton = new JButton("Clear");
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea outputTxtArea = new JTextArea();
	private JTextField inputTxtField = new JTextField();
	private JFrame jFrame = new JFrame();

	private void render() {
		jFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jFrame.setLocationRelativeTo(null);

		outputTxtArea.setEditable(false);
		outputTxtArea.setColumns(20);
		outputTxtArea.setRows(5);
		outputTxtArea.setBackground(Color.darkGray);
		outputTxtArea.setForeground(Color.green);
		scrollPane.setViewportView(outputTxtArea);

		DefaultCaret caret = (DefaultCaret)outputTxtArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		newGameBtn.setText("New Game");
		newGameBtn.setToolTipText("Starts the new game");

		saveGameBtn.setText("Save Game");
		saveGameBtn.setToolTipText("Saves the current game");

		loadGameBtn.setText("Load Game");
		loadGameBtn.setToolTipText("Loads the game");

		inputButton.setText("Enter");

		helpBtn.setText("Help");
		helpBtn.setToolTipText("Displays help menu.");

		GroupLayout layout = new GroupLayout(jFrame.getContentPane());
		jFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(inputTxtField)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(inputButton))
						.addComponent(scrollPane)
						.addGroup(layout.createSequentialGroup()
								.addComponent(newGameBtn)
								.addGap(18, 18, 18)
								.addComponent(saveGameBtn)
								.addGap(18, 18, 18)
								.addComponent(loadGameBtn)
								.addGap(18, 18, 18)
								.addComponent(clearButton)
								.addGap(18, 18, 18)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
								.addComponent(helpBtn)
								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(newGameBtn)
												.addComponent(saveGameBtn)
												.addComponent(loadGameBtn)
												.addComponent(helpBtn)
												.addComponent(clearButton)
								)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(inputTxtField, GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
										.addComponent(inputButton)))
		);

		clearButton.addActionListener(new clearListener());
		jFrame.pack();
	}

	public void show() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				worldView.render();
				jFrame.setVisible(true);
			}
		});
	}

	public void print(String text) {
		outputTxtArea.append(text);
	}

	public void println(String text) {
		print(text);
		print("\r\n");
	}

	public void showHelp() {
		JOptionPane.showMessageDialog(null, "<html>Following commands are recognised in the Wumpus world!" +
						"<ul><li><b>save/save_game </b> - save the current game.</li>"+
						"<li><b>enter/e + room#</b> - enters the room.</li>" +
						"<li><b>path/p + room#</b> - shows the shortest part to the room.</li>" +
						"<li><b>shoot/s + room#</b> - shoot the wumpus in the room.</li>" +
						"<li><b>quit/q</b> - finish the game.</li></ul> <br/> <br/>" +
						"<p><strong>For more info, pls contact:</strong></p>" +
						"<p>andrey.udodov@cqumail.com</p>" +
						"<p>guto_mengue@hotmail.com</p></html>",
				"Help menu",JOptionPane.INFORMATION_MESSAGE);
	}

	public String readCommand() {
		String str = inputTxtField.getText();
		inputTxtField.setText("");
		println(str);
		return str;
	}

	private class clearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			outputTxtArea.setText(null);

		}
	}

	public void newGameListener(AbstractAction action) {
		newGameBtn.addActionListener(action);
	}

	public void loadGameListener(AbstractAction action) {
		loadGameBtn.addActionListener(action);
	}

	public void saveGameListener(AbstractAction action) {
		saveGameBtn.addActionListener(action);
	}

	public void helpListener(AbstractAction action) {
		helpBtn.addActionListener(action);
	}

	public void commandListener(AbstractAction action) {
		inputTxtField.addActionListener(action);
		inputButton.addActionListener(action);
	}


}
