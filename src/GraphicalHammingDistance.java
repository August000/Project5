import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.IOException;

public class GraphicalHammingDistance extends JFrame
{
    
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel rightPanel = new JPanel();
    
    JLabel distLabel = new  JLabel("Enter Hamming Dist:");
    JTextField distField = new JTextField(10);
    JSlider slider = new JSlider(JSlider.HORIZONTAL,1,4,2);
    JButton showStations = new JButton("Show Stations");
    JTextArea txarea = new JTextArea();
    JScrollPane stationsPane = new JScrollPane(txarea);
    
    JLabel compareWith = new JLabel("Compare with:");
    JComboBox stations = new JComboBox();
    
    JButton calcHD = new JButton("Calculate HD");
    
    JLabel distLabel0 = new JLabel("Distance 0");
    JTextField dist0 = new JTextField();
    JLabel distLabel1 = new JLabel("Distance 1");
    JTextField dist1 = new JTextField();
    JLabel distLabel2 = new JLabel("Distance 2");
    JTextField dist2 = new JTextField();
    JLabel distLabel3 = new JLabel("Distance 3");
    JTextField dist3 = new JTextField();
    JLabel distLabel4 = new JLabel("Distance 4");
    JTextField dist4 = new JTextField();
    
    JButton addStation = new JButton("Add Station");
    JTextField stationName = new JTextField("ZERO");
    
    public GraphicalHammingDistance() throws IOException {
        
        super("Hamming Distance");
        setSize(700,800);
        setLayout(new GridLayout(1,2));
        distField.setEditable(false);
       
        GridBagConstraints cst = new GridBagConstraints();
        
        cst.anchor = GridBagConstraints.NORTHWEST;
        cst.insets = new Insets(10,10,0,0);
        cst.weightx = 0.5;
        cst.weighty = 1;
        cst.gridx = 0;
        cst.gridy = 0;
        leftPanel.add(distLabel, cst);
        
        cst.insets = new Insets(5,0,0,0);
        cst.ipadx = 2;
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 0;
        leftPanel.add(distField, cst);
        cst.ipadx = 0;
               
        cst.insets = new Insets(0,15,0,0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        cst.gridx = 0;
        cst.gridy = 1;
        leftPanel.add(slider, cst);
        
        cst.gridx = 0;
        cst.gridy = 2;
        leftPanel.add(showStations, cst);
        
        cst.ipady = 250;
        cst.ipadx = 100;
        cst.gridx = 0;
        cst.gridy = 3;
        leftPanel.add(stationsPane, cst);
        cst.ipady = 0;
        cst.ipadx = 0;
        
        cst.gridx = 0;
        cst.gridy = 4;
        leftPanel.add(compareWith, cst);
        
        cst.gridx = 1;
        cst.gridy = 4;
        leftPanel.add(stations, cst);

        cst.gridx = 0;
        cst.gridy = 5;
        leftPanel.add(calcHD, cst);  
        
        cst.insets = new Insets(0,50,0,0);
        cst.gridx = 0;
        cst.gridy = 6;
        leftPanel.add(distLabel0, cst);
        
        cst.gridx = 1;
        cst.gridy = 6;
        leftPanel.add(dist0, cst);
        
        cst.gridx = 0;
        cst.gridy = 7;
        leftPanel.add(distLabel1, cst);
        
        cst.gridx = 1;
        cst.gridy = 7;
        leftPanel.add(dist1, cst);
        
        cst.gridx = 0;
        cst.gridy = 8;
        leftPanel.add(distLabel2, cst);
        
        cst.gridx = 1;
        cst.gridy = 8;
        leftPanel.add(dist2, cst);
        
        cst.gridx = 0;
        cst.gridy = 9;
        leftPanel.add(distLabel3, cst);
        
        cst.gridx = 1;
        cst.gridy = 9;
        leftPanel.add(dist3, cst);
        
        cst.gridx = 0;
        cst.gridy = 10;
        leftPanel.add(distLabel4, cst);
        
        cst.gridx = 1;
        cst.gridy = 10;
        leftPanel.add(dist4, cst);
        
        cst.gridx = 0;
        cst.gridy = 11;
        leftPanel.add(addStation, cst);
        
        cst.gridx = 1;
        cst.gridy = 11;
        leftPanel.add(stationName, cst);
        
        HammingDist hd = new HammingDist("WSTS","TRFS");
        
        for(String stID:hd.getStations("Mesonet.txt")) {
            stations.addItem(stID);
        }
        
       slider.addChangeListener(new ChangeListener() {
           public void stateChanged(ChangeEvent e) {
               distField.setText(Integer.toString(slider.getValue()));
           } 
       });
       
       calcHD.addActionListener((e) -> {
            
           int[] hammingDistances = hd.calculateHammingDist(stations.getSelectedItem().toString(),slider.getValue());
           dist0.setText(Integer.toString(hammingDistances[0]));
           dist1.setText(Integer.toString(hammingDistances[1]));
           dist2.setText(Integer.toString(hammingDistances[2]));
           dist3.setText(Integer.toString(hammingDistances[3]));
           dist4.setText(Integer.toString(hammingDistances[4]));
          
       });
       
       showStations.addActionListener((e) -> {
           
          txarea.removeAll();
          txarea.repaint();
          hd.calculateHammingDist(stations.getSelectedItem().toString(),slider.getValue());
          for(String stID : hd.getStationsList()) {
              txarea.append(stID + "\n");
          }
           
          
       });
        
       addStation.addActionListener((e) -> {
          
           hd.addStation(stationName.getText());
           stations.addItem(stationName.getText());           
           
        });
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    
    }
    
    public static void main(String [] args) throws IOException {
        new GraphicalHammingDistance();
    }

}
