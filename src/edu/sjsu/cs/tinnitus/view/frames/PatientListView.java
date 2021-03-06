package edu.sjsu.cs.tinnitus.view.frames;

import javax.swing.*;

/**
 * This class allows the user to view the list of patients controlled by PatientListController
 */
public class PatientListView {


    /**
     * Creates the Patient List View and
     * constructs all GUI objects and places them
     */
    public PatientListView() {
        initComponents();
        panel.setVisible(true);
    }

    /**
     * Getter for saveButton
     *
     * @return saveButton
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Setter for saveButton
     *
     * @param saveButton - saveButton
     */
    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    /**
     * Getter for patientTable
     *
     * @return patientTable
     */
    public JTable getPatientTable() {
        return patientTable;
    }

    /**
     * Setter for patientTable
     *
     * @param patientTable - patientTable
     */
    public void setPatientTable(JTable patientTable) {
        this.patientTable = patientTable;
    }

    /**
     * Getter for panel
     *
     * @return panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Setter for panel
     *
     * @param panel - panel
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * Initializes the components
     */
    private void initComponents(){
        panel = new JPanel();
        patientTable = new JTable();
        saveButton = new JButton("Save");
        scrollPane = new JScrollPane(patientTable);
    }


    /**
     * Getter for scrollPane
     *
     * @return scrollPane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Setter for scrollPane
     *
     * @param scrollPane - scrollPane
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        panel.add(scrollPane);
        panel.add(saveButton);
    }

    private JButton saveButton;
    private JTable patientTable;
    private JPanel panel;
    private JScrollPane scrollPane;
}
