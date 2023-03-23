import Model.Responsemodel;
import Network.ConectURI;
import model.Responsemodel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JPanel panel1;
    private JButton submit;
    private JButton Close;
    private JTextField massage;
    private JTextField stattus;
    private JTextField comment;
    private JButton Minimizebutton;


    public GUI() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConectURI koneksisaya = new ConectURI();
                    URL myAddres = koneksisaya.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
                    String response = koneksisaya.getResponseFromHttpUrl(myAddres);
                    System.out.println(response);

                    JSONArray responJSON = new JSONArray(response);
                    ArrayList<Responsemodel> responsemodels = new ArrayList<>();
                    for (int i = 0; i < responJSON.length(); i++) {
                        Responsemodel resModel = new Responsemodel();
                        JSONObject myJSONobject = responJSON.getJSONObject(i);
                        resModel.setMsg(myJSONobject.getString("message"));
                        resModel.setStatus(myJSONobject.getString("status"));
                        resModel.setComment(myJSONobject.getString("comment"));
                        responsemodels.add(resModel);}
//                    System.out.println("respone Are :");
                    for (int index = 0; index < responsemodels.size(); index++) {
                        massage.setText(responsemodels.get(index).getMsg());
                        stattus.setText(responsemodels.get(index).getStatus());
                        comment.setText(responsemodels.get(index).getComment());
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                massage.setText(" ");
                stattus.setText(" ");
                comment.setText(" ");


            }
        });

        Minimizebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(ICONIFIED);
                System.exit(0);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setUndecorated(true);
        frame.setSize(400,300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        frame.setResizable(false);
        frame.setLocationRelativeTo(null);