
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientsFrame extends JFrame{

    private MedicalCenter mc;
    public PatientsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void render(){

        JFrame frame = new JFrame("Medical Centre Application for Patients  ");
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(500   , 200 ));
        frame.setSize(500, 200);
        frame.getContentPane().setLayout(new GridLayout(1, 2));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
        JButton newB = new JButton("New patient");
        newB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registration reg = new Registration(mc);
                reg.render();
                frame.dispose();
            }
        });
        frame.add(newB);
        JButton oldB = new JButton ("Old patient");
        oldB.addMouseListener(new MouseAdapter() {
            String ID;
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                JFrame securityID = new JFrame("Enter your security ID");
                securityID.setLocationRelativeTo(null);
                securityID.setPreferredSize(new Dimension(300   , 150 ));
                securityID.setSize(300, 150);
                securityID.getContentPane().setLayout(new GridLayout(4, 1));
                securityID.setResizable(false);
                securityID.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                securityID.setVisible(true);
                securityID.requestFocus();
                JLabel test = new JLabel("Enter your ID");
                securityID.add(test);
                JPanel jp = new JPanel();
                JTextField textID = new JTextField(1);
                jp.add(textID);
                securityID.add(textID);
                JButton submit = new JButton("Submit");
                submit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ID = textID.getText();
                        if(ID != null) {
                            int check = 0;
                            for(Patient p: mc.patients){
                                if(ID.length() == 11){
                                    if(ID.equals(p.getId())){
                                        test.setText("ID is good (go to user inferface)");
                                        frame.dispose();
                                        PatientsFrame goBack = new PatientsFrame(mc);
                                        goBack.render();
                                        check++;
                                    }
                                    if(check == 0){
                                        test.setText("No such ID");
                                    }
                                }
                                else test.setText("ID has to be 11 digits long!");

                                }

                        }

                    }
                });
                securityID.add(submit);
                JButton back = new JButton("Back");
                back.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PatientsFrame goBack = new PatientsFrame(mc);
                        securityID.dispose();
                        goBack.render();
                    }
                });
                securityID.add(back);

            }
        });
        frame.add(oldB);


    }


}
//