package edu.sjsu.cs.tinnitus.controller;

import edu.sjsu.cs.tinnitus.model.*;
import edu.sjsu.cs.tinnitus.view.frames.MedicalHistoryView;
import edu.sjsu.cs.tinnitus.view.frames.MedicationView;
import edu.sjsu.cs.tinnitus.view.frames.PatientView;
import edu.sjsu.cs.tinnitus.view.frames.VisitView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MedicalHistoryController implements Controller {


    /**
     * Constructor for a controller
     * @param medicalHistoryView - view that is being controlled
     * @param medicalHistory - model
     */
    public MedicalHistoryController(MedicalHistoryView medicalHistoryView, MedicalHistory medicalHistory,
                                     Visit visit, ClinicController clinicController) {
        this.medicalHistoryView = medicalHistoryView;
        this.medicalHistory = medicalHistory;
        this.visit = visit;
        this.patient = null;
        this.clinicController = clinicController;
        initTable();
        initController();
        initMedHistory();
        clinicController.setHeaderContent("Medical History");
    }

    /**
     * Constructs a MedicalHistoryController that is associated with a patient
     * @param medicalHistoryView - medical history view
     * @param medicalHistory - medical history
     * @param patient - patient
     * @param clinicController - clinic controller
     */
    public MedicalHistoryController(MedicalHistoryView medicalHistoryView, MedicalHistory medicalHistory,
                                     Patient patient, ClinicController clinicController) {
        this.medicalHistoryView = medicalHistoryView;
        this.medicalHistory = medicalHistory;
        this.visit = null;
        this.patient = patient;
        this.clinicController = clinicController;
        initTable();
        initController();
        initMedHistory();
        clinicController.setHeaderContent("Medical History");
    }

    /**
     * Sets ActionListeners in MedicalHistoryView
     */
    @Override
    public void initController() {
        medicalHistoryView.getSaveButton().addActionListener(e-> {
            if(visit == null){
                returnToPatient();
            }
            else{
                returnToVisit();
            }
        });
        medicalHistoryView.getAddMedication().addActionListener(e->{
            addMedication();
        });

    }

    /**
     * Creates a Medication Frame to add a medication
     * @param medication
     */
    public void goToMedication(Medication medication){
        MedicationView medicationView = new MedicationView();
        MedicationController medicationController =
                new MedicationController(medicalHistory, medicationView, medication, this);
    }

    /**
     * Switches control to PatientController
     */
    public void returnToPatient(){
        updateMedication();
        JFrame frame = clinicController.getFrame();
        frame.remove(medicalHistoryView.getPanel());
        PatientView view = new PatientView();
        PatientController patientController = new PatientController(patient, view, clinicController);
        frame.add(view.getPanel(), BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
    }

    /**
     * Switches Control to VisitController
     */
    public void returnToVisit(){
        updateMedication();
        JFrame frame = clinicController.getFrame();
        frame.remove(medicalHistoryView.getPanel());
        VisitView view = new VisitView();
        VisitController visitController = new VisitController(visit, view, clinicController);
        frame.add(view.getPanel(), BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
    }

    /**
     * adds a new medication to the model
     */
    public void addMedication(){
        Medication medication = new Medication();
        MedicationView medicationView = new MedicationView();
        MedicationController medicationController =
                new MedicationController(medicalHistory, medicationView, medication, this);
        medicalHistory.getMedHistoryTable().addToMedicationList(medication);
    }

    /**
     * Initializes the Table with information from medicalHistoryTable
     */
    public void initTable(){
        ArrayList<Medication> medicationList = medicalHistory.getMedHistoryTable().getMedicationList();
        Object[][] data = new Object[medicationList.size()][9];
        String [] columnNames = {"Name","Generic Name", "Dose(mg)", "Duration(days)", "Chemical Category",
                                "Action", "Application", "Usual Dose(mg)", "Induces Tinnitus?"};
        int i = 0;
        for(Medication medication: medicationList){
            data[i][0] = medication.getName();
            data[i][1] = medication.getGenericName();
            data[i][2] = medication.getDose();
            data[i][3] = medication.getDuration();
            data[i][4] = medication.getChemicalCategory();
            data[i][5] = medication.getAction();
            data[i][6] = medication.getApplication();
            data[i][7] = medication.getUsualDose();
            data[i][8] = medication.inducesTinnitus();
            i++;
        }

        JTable table = new JTable(data, columnNames);
        medicalHistoryView.setMedicationTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        medicalHistoryView.setScrollPane(scrollPane);
        clinicController.getFrame().validate();
        clinicController.getFrame().repaint();
    }

    /**
     * Initializes the medicalHistory text area
     */
    public void initMedHistory(){
        medicalHistoryView.getMedicalHistoryArea().setText(medicalHistory.getMedHistoryTable().getMedHistoryDescription());
    }

    /**
     * Save any updates to the medical information from textFields in View
     */
    public void updateMedication(){
        //save medical History Description
        medicalHistory.getMedHistoryTable().setMedHistoryDescription(
                medicalHistoryView.getMedicalHistoryArea().getText()
        );

    }

    /**
     * Getter for medicalHistoryView
     *
     * @return medicalHistoryView
     */
    public MedicalHistoryView getMedicalHistoryView() {
        return medicalHistoryView;
    }

    /**
     * Setter for medicalHistoryView
     *
     * @param medicalHistoryView - medicalHistoryView
     */
    public void setMedicalHistoryView(MedicalHistoryView medicalHistoryView) {
        this.medicalHistoryView = medicalHistoryView;
    }

    /**
     * Getter for medicalHistory
     *
     * @return medicalHistory
     */
    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Setter for medicalHistory
     *
     * @param medicalHistory - medicalHistory
     */
    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Getter for clinicController
     *
     * @return clinicController
     */
    public ClinicController getClinicController() {
        return clinicController;
    }

    /**
     * Setter for clinicController
     *
     * @param clinicController - clinicController
     */
    public void setClinicController(ClinicController clinicController) {
        this.clinicController = clinicController;
    }

    /**
     * Getter for visit
     *
     * @return visit
     */
    public Visit getVisit() {
        return visit;
    }

    /**
     * Setter for visit
     *
     * @param visit - visit
     */
    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    /**
     * Getter for patient
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Setter for patient
     *
     * @param patient - patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private MedicalHistoryView medicalHistoryView;
    private MedicalHistory medicalHistory;
    private ClinicController clinicController;
    private Visit visit;
    private Patient patient;
}
