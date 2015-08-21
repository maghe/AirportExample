package nl.lunatech.assignment.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import nl.lunatech.assignment.core.AirportsManager;
import nl.lunatech.assignment.elements.Airport;
import nl.lunatech.assignment.elements.Runway;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class AirportSearcherGUI {

	private AirportsManager airportsManager;

	private JRadioButton searchByCodeRB;
	private JRadioButton searchByNameRB;
	private ButtonGroup radioButtons;
	private JTextField searchByCodeInputField;
	private JTextField searchByNameInputField;
	private JButton searcButton;
	private JButton closeButton;

	public AirportSearcherGUI(AirportsManager airportsManager) {
		this.airportsManager = airportsManager;

		createAndShowGUI();
	}

	//
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void createAndShowGUI() {

		JFrame mainFrame = new JFrame("Airport searcher");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// AirportSearcherGUI airportSearcherGUI = new AirportSearcherGUI(new
		// AirportsManager());

		Container contentPane = mainFrame.getContentPane();
		contentPane.add(createOptionSearchPane(), BorderLayout.CENTER);
		contentPane.add(createButtonPane(), BorderLayout.PAGE_END);

		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null); // center it
		mainFrame.setVisible(true);
	}

	private Component createOptionSearchPane() {

		searchByCodeRB = new JRadioButton("Country code");
		searchByCodeRB.setSelected(true);
		searchByCodeRB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchByCodeInputField.setEditable(true);
				searchByNameInputField.setEditable(false);
				searchByNameInputField.setText("");
			}
		});

		searchByNameRB = new JRadioButton("Country name");
		searchByNameRB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchByCodeInputField.setEditable(false);
				searchByCodeInputField.setText("");
				searchByNameInputField.setEditable(true);
			}
		});

		radioButtons = new ButtonGroup();
		radioButtons.add(searchByCodeRB);
		radioButtons.add(searchByNameRB);

		searchByCodeInputField = new JTextField("");
		searchByCodeInputField.setBorder(new LineBorder(Color.BLACK));

		searchByNameInputField = new JTextField("");
		searchByNameInputField.setBorder(new LineBorder(Color.BLACK));
		searchByNameInputField.setEditable(false);

		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(2, 2));
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pane.add(searchByCodeRB);
		pane.add(searchByCodeInputField);
		pane.add(searchByNameRB);
		pane.add(searchByNameInputField);
		return pane;

	}

	// Create the button that goes in the main window.
	protected JComponent createButtonPane() {
		searcButton = new JButton("Search");

		searcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAirportsInfo();
			}
		});

		closeButton = new JButton("Close");

		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// MAGHE check a better solution
				System.exit(0);
			}
		});

		// Center the button in a panel with some space around it.
		JPanel pane = new JPanel(); // use default FlowLayout
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pane.add(searcButton);
		pane.add(closeButton);

		return pane;
	}

	public void showAirportsInfo() {

		List<Airport> airportsFound;
		String input = "tmp";
		
		if (searchByCodeInputField.isEditable()) {
			input=searchByCodeInputField.getText();
			airportsFound = airportsManager.getAirportsDataFromCountryCode(	input=searchByCodeInputField.getText());

		} else {
			input=searchByNameInputField.getText();
			airportsFound = airportsManager.getAirportsDataFromCountryName(input);
			
		}

		if (airportsFound == null || airportsFound.isEmpty()) {
			showWarningDialog(input);
		} else {
			JFrame airportsDataFrame = new JFrame("Aiport data");
			JPanel airportsDataPanel = new JPanel();
			JScrollPane airportsDataScrollPane = new JScrollPane();

			List<String> airportsDataStringList = new ArrayList<>();

			for (Airport airport : airportsFound) {

				StringBuilder airportInfoSB = new StringBuilder();
				airportInfoSB.append(airport.toString());

				airportInfoSB.append("   - Runways: ");
				for (Runway runway : airport.getRunways()) {
					airportInfoSB.append("[" + runway.toString() + "] ");
				}

				airportsDataStringList.add(airportInfoSB.toString());
			}

			JList<String> airportsDataJList = new JList<String>(
					airportsDataStringList.toArray(new String[airportsDataStringList.size()]));
			airportsDataJList.setVisibleRowCount(40);

			airportsDataScrollPane.setViewportView(airportsDataJList);
			airportsDataPanel.setLayout(new BorderLayout());
			airportsDataPanel.add(airportsDataScrollPane);

			airportsDataFrame.add(airportsDataPanel);
			airportsDataFrame.pack();
			airportsDataFrame.setVisible(true);
		}
	}

	private void showWarningDialog(String input) {
		
		String warningMessage = "There is no Country with code or name equal to: " +input;
		JOptionPane optionPane = new JOptionPane(warningMessage, JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

}