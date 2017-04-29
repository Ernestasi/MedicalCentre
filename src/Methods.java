//maybe will need this for global methods

import sun.awt.geom.AreaOp;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Methods {
    MedicalCenter mc;

    public void registerPatient(String docID, String patID, String chosenTime, MedicalCenter mc) {

        boolean checkIfGoodId = false;
        boolean exists = false;
        for (Patient p : mc.patients) {
            if ((p.getId().equals(patID) && (p.appointments == null))) {
                p.appointments.add(patID + "," + docID + "_" + chosenTime);
                exists = true;
            }
            if (p.getId().equals(patID))
                checkIfGoodId = true;
        }
        if (!checkIfGoodId)
            System.out.println("Toks pacientas neegzistuoja sistemoje");
        if (!exists) {
            String lineToReplace = null;
            for (Patient p : mc.patients) {
                if (p.getId().equals(patID)) {
                    p.appointments.add(docID + "_" + chosenTime);
                }
            }
        }
        updateDatesFile(mc.patients);
        System.out.println("Added sucessfuly");
    }


    public void updateDatesFile(ArrayList<Patient> patients) {
// 20926475361,0_2017-04-24_11:00,0_2017-04-24_10:00
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("./src/Data/Dates.txt");
            bw = new BufferedWriter(fw);
            for (Patient p : patients) {
                if (p.getAppointments() != null) {
                    String string = p.getId();
                    for (String times : p.appointments) {
                        string = string + "," + times;
                    }
                    string = string + "\n";
                    bw.write(string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void updatePatientsFile(ArrayList<Patient> patients) {

        String string = "";
        for (Patient p : patients) {
            //Ona_Petraite_99286715699_0_ _
            String a = "_";
            string = string + p.getName() + a + p.getSurName() + a + p.getId() + a + p.getInsurance() + a + p.getDescription() + a + "\n";
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("./src/Data/Patients.txt"));
            writer.write(string);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }

    public JPanel selectTimePanel(Doctor doctor, Patient patient, Calendar time) {
        JPanel returnPanel = new JPanel(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new GridLayout(1, 1, 1, 1));


        Calendar cal = Calendar.getInstance();

        String[] year = {String.valueOf(time.get(Calendar.YEAR)), String.valueOf(time.get(Calendar.YEAR) + 1), String.valueOf(time.get(Calendar.YEAR) + 2)};

        JComboBox yearBox = new JComboBox(year);
        JComboBox monthBox = new JComboBox();
        JComboBox dayBox = new JComboBox();

        yearBox.setPreferredSize(new Dimension(70, 20));
        monthBox.setPreferredSize(new Dimension(50, 20));
        dayBox.setPreferredSize(new Dimension(50, 20));

        yearBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monthBox.removeAllItems();
                for (int i = (String.valueOf(time.get(Calendar.YEAR)).equals(yearBox.getSelectedItem()) ? time.get(Calendar.MONTH) + 1 : 1); i <= 12; i++) {
                    monthBox.addItem(i);
                }
            }
        });

        yearBox.setSelectedIndex(0);
        monthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayBox.removeAllItems();
                //String.valueOf(time.get(Calendar.YEAR)).equals(yearBox.getSelectedItem()) &&
                cal.set(Calendar.YEAR, Integer.parseInt((String) yearBox.getSelectedItem()));
                cal.set(Calendar.MONTH, 1);
                if (monthBox.getSelectedItem() != null)
                    cal.set(Calendar.MONTH, (Integer) monthBox.getSelectedItem() - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                if (cal.get(Calendar.MONTH) == time.get(Calendar.MONTH) && cal.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
                    cal.set(Calendar.DAY_OF_MONTH, time.get(Calendar.DAY_OF_MONTH));
                }
                Calendar tempCal = (Calendar) cal.clone();
                tempCal.set(Calendar.DAY_OF_MONTH, 1);
                tempCal.add(Calendar.MONTH, 1);
                for (Calendar c = (Calendar) cal.clone(); c.before(tempCal); c.add(Calendar.DAY_OF_MONTH, 1)) {
                    if (c.get(Calendar.DAY_OF_MONTH) >= cal.get(Calendar.DAY_OF_MONTH))
                        dayBox.addItem(c.get(Calendar.DAY_OF_MONTH));
                }
            }
        });
        monthBox.setSelectedIndex(0);

        dayBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cal.set(Calendar.YEAR, Integer.parseInt((String) yearBox.getSelectedItem()));
                if (monthBox.getSelectedItem() != null)
                    cal.set(Calendar.MONTH, (Integer) monthBox.getSelectedItem() - 1);
                if (dayBox.getSelectedItem() != null)
                    cal.set(Calendar.DAY_OF_MONTH, (Integer) dayBox.getSelectedItem());
//////////////////////////
                centerPanel.removeAll();
                centerPanel.add(dayShedule(cal, doctor));
                returnPanel.updateUI();
////////////////////////////
            }
        });

        northPanel.add(new JLabel("Year: "));
        northPanel.add(yearBox);
        northPanel.add(new JLabel("Month: "));
        northPanel.add(monthBox);
        northPanel.add(new JLabel("Day: "));
        northPanel.add(dayBox);


        returnPanel.add(northPanel, BorderLayout.NORTH);
        returnPanel.add(new JButton("AS"), BorderLayout.CENTER);
        returnPanel.add(centerPanel, BorderLayout.CENTER);
        return returnPanel;
    }

    public JPanel dayShedule(Calendar time, Doctor doctor) {
        JPanel returnPanel = new JPanel(new GridLayout(16, 1, 1, 1));
        if (time.get(Calendar.DAY_OF_WEEK) - 2>=0) {
            System.out.println("#1");
            if (!doctor.getTimeDay(time.get(Calendar.DAY_OF_WEEK) - 2).equals("-")) {
                String[] temp = (doctor.getTimeDay(time.get(Calendar.DAY_OF_WEEK) - 2).split("-|:"));
                System.out.println("#" + doctor.getTimeDay(0));
                time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[0]));
                time.set(Calendar.MINUTE, Integer.parseInt(temp[1]));

                Calendar time2 = (Calendar) time.clone();
                time2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[2]));
                time2.set(Calendar.MINUTE, Integer.parseInt(temp[3]));


                SimpleDateFormat hoursFormat = new SimpleDateFormat("HH:mm");
                for (Calendar cal = (Calendar) time.clone(); cal.before(time2); cal.add(Calendar.MINUTE, 30)) {
                    JLabel jl = new JLabel(hoursFormat.format(cal.getTime()));
                    returnPanel.add(jl);
                }
            }else{
                returnPanel.add(new JLabel("Doctor doesn't work on selected day"));
            }
        }else{
            returnPanel.add(new JLabel("Doctor doesn't work on selected day"));
        }

        return returnPanel;
    }
}
