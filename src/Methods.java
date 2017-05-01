
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Methods{
    DataMethods dataMethods = new DataMethods();

    public void registerPatient(String docID, String patID, String chosenTime, MedicalCenter mc){

        boolean checkIfGoodId = false;
        boolean exists = false;
        for(Patient p : mc.patients){
            if((p.getId().equals(patID) && (p.appointments == null))){
                p.appointments.add(patID + "," + docID + "_" + chosenTime);
                exists = true;
            }
            if(p.getId().equals(patID))
                checkIfGoodId = true;
        }
        if(!checkIfGoodId)
            System.out.println("Toks pacientas neegzistuoja sistemoje");
        if(!exists){
            String lineToReplace = null;
            for(Patient p : mc.patients){
                if(p.getId().equals(patID)){
                    p.appointments.add(docID + "_" + chosenTime);
                }
            }
        }
        dataMethods.updateDatesFile(mc.patients);
        System.out.println("Added sucessfuly");
    }

    public JPanel selectTimePanel(Doctor doctor, Patient patient, Calendar time, MedicalCenter mc){
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

        yearBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                monthBox.removeAllItems();
                for(int i = (String.valueOf(time.get(Calendar.YEAR)).equals(yearBox.getSelectedItem()) ? time.get(Calendar.MONTH) + 1 : 1); i <= 12; i++){
                    monthBox.addItem(i);
                }
            }
        });

        yearBox.setSelectedIndex(0);
        monthBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dayBox.removeAllItems();
                //String.valueOf(time.get(Calendar.YEAR)).equals(yearBox.getSelectedItem()) &&
                cal.set(Calendar.YEAR, Integer.parseInt((String) yearBox.getSelectedItem()));
                cal.set(Calendar.MONTH, 1);
                if(monthBox.getSelectedItem() != null)
                    cal.set(Calendar.MONTH, (Integer) monthBox.getSelectedItem() - 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                if(cal.get(Calendar.MONTH) == time.get(Calendar.MONTH) && cal.get(Calendar.YEAR) == time.get(Calendar.YEAR)){
                    cal.set(Calendar.DAY_OF_MONTH, time.get(Calendar.DAY_OF_MONTH));
                }
                Calendar tempCal = (Calendar) cal.clone();
                tempCal.set(Calendar.DAY_OF_MONTH, 1);
                tempCal.add(Calendar.MONTH, 1);
                for(Calendar c = (Calendar) cal.clone(); c.before(tempCal); c.add(Calendar.DAY_OF_MONTH, 1)){
                    if(c.get(Calendar.DAY_OF_MONTH) >= cal.get(Calendar.DAY_OF_MONTH))
                        dayBox.addItem(c.get(Calendar.DAY_OF_MONTH));
                }
            }
        });
        monthBox.setSelectedIndex(0);

        dayBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cal.set(Calendar.YEAR, Integer.parseInt((String) yearBox.getSelectedItem()));
                if(monthBox.getSelectedItem() != null)
                    cal.set(Calendar.MONTH, (Integer) monthBox.getSelectedItem() - 1);
                if(dayBox.getSelectedItem() != null)
                    cal.set(Calendar.DAY_OF_MONTH, (Integer) dayBox.getSelectedItem());
//////////////////////////
                centerPanel.removeAll();
                centerPanel.add(dayShedule(cal, doctor, patient, mc));
                returnPanel.updateUI();
////////////////////////////
            }
        });
        dayBox.setSelectedIndex(0);
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

    private JPanel dayShedule(Calendar time, Doctor doctor, Patient patient, MedicalCenter mc){
        JPanel returnPanel = new JPanel(new GridLayout(16, 2, 1, 1));
        returnPanel.setBackground(Color.LIGHT_GRAY);
        returnPanel.add(new JLabel("Doctor doesn't work on selected day"));

        if(time.get(Calendar.DAY_OF_WEEK) - 2 >= 0){
            if(!doctor.getTimeDay(time.get(Calendar.DAY_OF_WEEK) - 2).equals("-")){
                returnPanel.removeAll();
                String[] temp = (doctor.getTimeDay(time.get(Calendar.DAY_OF_WEEK) - 2).split("-|:"));
                time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[0]));
                time.set(Calendar.MINUTE, Integer.parseInt(temp[1]));

                Calendar time2 = (Calendar) time.clone();
                time2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[2]));
                time2.set(Calendar.MINUTE, Integer.parseInt(temp[3]));


                SimpleDateFormat hoursFormat = new SimpleDateFormat("HH:mm");
                for(Calendar cal = (Calendar) time.clone(); cal.before(time2); ){
                    JPanel line = new JPanel(new BorderLayout());
                    JLabel jl = new JLabel(hoursFormat.format(cal.getTime()) + "-");
                    line.add(jl, BorderLayout.WEST);
                    line.add(checkIfBusyPanel(cal, doctor, patient, mc), BorderLayout.EAST);
                    cal.add(Calendar.MINUTE, 30);
                    jl.setText(jl.getText() + hoursFormat.format(cal.getTime()) + "  ");
                    returnPanel.add(line);
                }
            }
        }

        return returnPanel;
    }

    private JPanel checkIfBusyPanel(Calendar time, Doctor doctor, Patient patient, MedicalCenter mc){
        JPanel returnPanel = new JPanel();
        for(Patient p : mc.patients){
            if(p.getDocId() != null){
                for(int j = 0; j < p.getDocId().size(); j++){
                    if(Integer.parseInt(p.getDocId().get(j)) == doctor.getId()){
                        Calendar tempCal = (Calendar) p.getTime().get(j).clone();
                      //  SimpleDateFormat minutesFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
                        if(time.get(Calendar.YEAR) == tempCal.get(Calendar.YEAR)
                                && time.get(Calendar.DAY_OF_YEAR) == tempCal.get(Calendar.DAY_OF_YEAR)
                                && time.get(Calendar.HOUR_OF_DAY) == tempCal.get(Calendar.HOUR_OF_DAY)
                                && time.get(Calendar.MINUTE) == tempCal.get(Calendar.MINUTE)
                                ){
                            if(patient == null){
                                JPanel line = new JPanel(new BorderLayout(1, 1));
                                line.add(new JLabel(p.fullName()), BorderLayout.WEST);
                                JButton infoBut = new JButton("i");
                                infoBut.addMouseListener(new MouseAdapter(){
                                    @Override
                                    public void mouseClicked(MouseEvent e){
                                        patientInfoFrame(p, mc);
                                    }
                                });
                                line.add(infoBut, BorderLayout.CENTER);
                                JButton removeBut = new JButton("x");
                                line.add(removeBut, BorderLayout.EAST);
                                int a = j;
                                removeBut.addMouseListener(new MouseAdapter(){
                                    @Override
                                    public void mouseClicked(MouseEvent e){
                                        DoctorsFrame df = new DoctorsFrame();
                                        if(removeBut.isEnabled()){
                                            JDialog cancelApointment = new JDialog(mc.docFrame.frame, "Remove", Dialog.ModalityType.DOCUMENT_MODAL);
                                            cancelApointment.setLayout(new GridLayout(2, 1));
                                            cancelApointment.setSize(300, 100);
                                            cancelApointment.setResizable(false);
                                            JButton yesBut = new JButton("YES");
                                            yesBut.addMouseListener(new MouseAdapter(){
                                                @Override
                                                public void mouseClicked(MouseEvent e){
                                                    p.getAppointments().remove(a);
                                                    p.AppointmentToTime();
                                                    dataMethods.updateDatesFile(mc.patients);
                                                    cancelApointment.dispose();
                                                    removeBut.setEnabled(false);
                                                }
                                            });
                                            JButton noBut = new JButton("NO");
                                            noBut.addMouseListener(new MouseAdapter(){
                                                @Override
                                                public void mouseClicked(MouseEvent e){
                                                    cancelApointment.dispose();
                                                }
                                            });

                                            cancelApointment.add(new JLabel("Remove appointment?", SwingConstants.CENTER));
                                            JPanel jPan = new JPanel(new GridBagLayout());
                                            jPan.add(yesBut);
                                            jPan.add(new JLabel(" "));
                                            jPan.add(noBut);
                                            cancelApointment.add(jPan);
                                            cancelApointment.setVisible(true);

                                        }
                                    }
                                });

                                returnPanel.add(line);
                            }else{
                                if(patient.getId().equals(p.getId())){
                                    returnPanel.add(new JLabel("You registered at this time"));
                                }else{
                                    returnPanel.add(new JLabel("This time is unavailable"));
                                }
                            }
                        }
                    }
                }
            }
        }
        if(returnPanel.getComponentCount() == 0){
            JButton addBut;
            if(patient == null){
                addBut = new JButton("Add new patient");
                if(time.before(mc.cal)){
                    addBut.setEnabled(false);
                }
                Calendar cal = (Calendar) time.clone();
                addBut.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(addBut.isEnabled()){
                            mc.docFrame.addPatientAppointment(cal, doctor);// todo need repaint update or smth like that
                            addBut.setEnabled(false);
                            returnPanel.updateUI();
                        }
                    }
                });
            }else{
                addBut = new JButton("Register");
                if(time.before(mc.cal)){
                    addBut.setEnabled(false);
                }
                Calendar cal = (Calendar) time.clone();
                addBut.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(addBut.isEnabled()){
                            SimpleDateFormat tempFormat = new SimpleDateFormat("_YYYY-MM-dd_HH:mm");
                            patient.setAppointments(doctor.getId() + tempFormat.format(cal.getTime()));// todo need repaint update or smth like that
                            dataMethods.updateDatesFile(mc.patients);
                            addBut.setEnabled(false);
                            returnPanel.updateUI();
                        }
                    }
                });
            }
            returnPanel.add(addBut, BorderLayout.CENTER);
        }
        return returnPanel;
    }

    public void patientInfoFrame(Patient p, MedicalCenter mc){
        JFrame infoF = new JFrame(p.getName() + " " + p.getSurName());
        infoF.setSize(400, 500);
        infoF.setLocation(200, 160);
        infoF.setLocationByPlatform(true);
        infoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JPanel info = new JPanel();
        info.add(new JLabel("Patient: "));
        JTextField txt = new JTextField(p.getName().toUpperCase() + " " + p.getSurName().toUpperCase(), 15);
        txt.setEditable(false);
        info.add(txt);
        info.add(new JLabel("  ID: "));
        JTextField txt1 = new JTextField(p.getId(), 8);
        txt1.setEditable(false);
        info.add(txt1);

        JPanel info2 = new JPanel();
        info2.add(new JLabel("Insurance: "));
        JTextField txt2 = new JTextField(p.getInsType(), 13);
        info2.add(txt2);
        info2.add(new JLabel("    Description:            "));
        txt2.setEditable(false);

        JPanel info3 = new JPanel();
        info3.setLayout(new BorderLayout(10, 0));
        JTextArea txt3 = new JTextArea(p.getDescription().replaceAll("#n#", "\n"), 14, 20);

        JScrollPane descriptionScrollable = new JScrollPane(txt3);
        descriptionScrollable.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        txt3.setLineWrap(true);
        txt3.setWrapStyleWord(true);
        txt3.setEditable(false);
        info3.add(descriptionScrollable, BorderLayout.CENTER);
        JButton editBut = new JButton(" Edit ");
        JButton closeBut = new JButton(" Close");

        final String[] tempText = {txt3.getText()};
        editBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!txt3.isEditable()){ //edit
                    txt3.setEditable(true);
                    editBut.setText(" Save  ");
                    closeBut.setText("Cancel");
                    txt3.setText(tempText[0] + "");
                    txt3.requestFocus();
                }else{ //save
                    txt3.setEditable(false);
                    tempText[0] = txt3.getText();
                    editBut.setText(" Edit ");
                    closeBut.setText(" Close");
                    p.setDescription(txt3.getText().replaceAll("\n", " #n#"));

                    dataMethods.updatePatientsFile(mc.patients);
                }
            }
        });
        closeBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(closeBut.getText().equals(" Close")){ //close
                    infoF.dispose();
                }else{ //cancel
                    txt3.setEditable(false);
                    editBut.setText(" Edit ");
                    closeBut.setText(" Close");
                    txt3.setText(tempText[0]);
                }
            }
        });
        JPanel jl = new JPanel();
        jl.setLayout(new GridBagLayout());
        jl.add(editBut);
        jl.add(closeBut);

        info3.add(jl, BorderLayout.PAGE_END);
        panel.setLayout(new BorderLayout(10, 10));
        JPanel info0 = new JPanel();
        info0.setLayout(new GridLayout(0, 1));
        info0.add(info);
        info0.add(info2);
        panel.add(info0, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(info3, BorderLayout.CENTER);
        panel.add(new JLabel(" "), BorderLayout.BEFORE_LINE_BEGINS);
        panel.add(new JLabel(" "), BorderLayout.AFTER_LINE_ENDS);
        panel.add(new JLabel(), BorderLayout.PAGE_END);

        infoF.add(panel);
        infoF.setVisible(true);
    }

}

