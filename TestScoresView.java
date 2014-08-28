
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TestScoresView extends JFrame{
   // >>>>>>> The model <<<<<<<<
   // Declare the model
   private TestScoresModel model = new TestScoresModel();
   // >>>>>>> The view <<<<<<<<
   // Declare and instantiate the window objects.
   private JButton    addButton       = new JButton("Add");
   private JButton    modifyButton    = new JButton("Modify");
   private JButton    firstButton     = new JButton("<<");
   private JButton    previousButton  = new JButton("<");
   private JButton    nextButton      = new JButton(">");
   private JButton    lastButton      = new JButton(">>");
   private JButton    highScoreButton = new JButton("Highest score");
   private JButton    aveScoreButton  = new JButton("Class Average");
 private JLabel     nameLabel       = new JLabel("Name");
   private JLabel     test1Label      = new JLabel("Test 1"); 
   private JLabel     test2Label      = new JLabel("Test 2"); 
   private JLabel     test3Label      = new JLabel("Test 3");
   private JLabel     averageLabel    = new JLabel("Student Average");
   private JLabel     countLabel      = new JLabel("Count");
   private JLabel     indexLabel      = new JLabel("Index");
    private JLabel     ClassAverageLabel      = new JLabel("Class Average");
     private JLabel     HighScoreLabel      = new JLabel("Class High Score");
   private JTextField nameField       = new JTextField("");
   private JTextField test1Field      = new JTextField("0");
   private JTextField test2Field      = new JTextField("0");
   private JTextField test3Field      = new JTextField("0");
   private JTextField averageField    = new JTextField("0");
   private JTextField countField      = new JTextField("0");
   private JTextField indexField      = new JTextField("-1");
   private JTextField ClassAverageField = new JTextField("0"); //Field to display average score of class
   private JTextField HighScoreField = new JTextField("0"); // Field to display highest score
   // Constructor
   public TestScoresView(TestScoresModel m){
      model = m;
      // Set attributes of fields
      averageField.setEditable(false);
      countField.setEditable(false);
      indexField.setEditable(false);
      ClassAverageField.setEditable(false);
      HighScoreField.setEditable(false);
      averageField.setBackground(Color.white);
      countField.setBackground(Color.white);
      indexField.setBackground(Color.white);
      ClassAverageField.setBackground(Color.white);
      HighScoreField.setBackground(Color.white);
      
      // Set up panels to organize widgets and
      // add them to the window
      JPanel northPanel = new JPanel();
      JPanel centerPanel = new JPanel(new GridLayout(5, 4, 10, 5));
      JPanel southPanel = new JPanel();
      Container container = getContentPane();
      container.add(northPanel, BorderLayout.NORTH);
      container.add(centerPanel, BorderLayout.CENTER);
      container.add(southPanel, BorderLayout.SOUTH);
      // Data access buttons
      northPanel.add(addButton);
      northPanel.add(modifyButton);
      northPanel.add(highScoreButton);
      northPanel.add(aveScoreButton);
      // Row 1
      centerPanel.add(nameLabel);
      centerPanel.add(nameField);
      centerPanel.add(countLabel);
      centerPanel.add(countField);
      // Row 2
      centerPanel.add(test1Label);
      centerPanel.add(test1Field);
      centerPanel.add(indexLabel);
      centerPanel.add(indexField);
      // Row 3
      centerPanel.add(test2Label);
      centerPanel.add(test2Field);
      centerPanel.add(new JLabel(""));  // For empty cell in grid
centerPanel.add(new JLabel(""));
      // Row 4
      centerPanel.add(test3Label);
      centerPanel.add(test3Field);
      centerPanel.add(ClassAverageLabel);
      centerPanel.add(ClassAverageField);
     
   
      // Row 5
      centerPanel.add(averageLabel);
      centerPanel.add(averageField);
      centerPanel.add(HighScoreLabel);
      centerPanel.add(HighScoreField);
   
     
      // Navigation buttons
      southPanel.add(firstButton);  
      southPanel.add(previousButton);
      southPanel.add(nextButton);  
      southPanel.add(lastButton);  
      // Attach listeners to buttons
      addButton.addActionListener(new AddListener());
      modifyButton.addActionListener(new ModifyListener());
      previousButton.addActionListener(new PreviousListener());
      nextButton.addActionListener(new NextListener());
      firstButton.addActionListener(new FirstListener());
      lastButton.addActionListener(new LastListener());
      highScoreButton.addActionListener(new HighScoreListener());
      aveScoreButton.addActionListener(new ClassAverageListener());
    
      // Set window attributes
      setTitle("Student Test Scores");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }
   // Updates fields with info from the model
   private void displayInfo(){
      Student s = model.currentStudent();
      if (s == null){  // No current student, so clear fields
         nameField.setText("");
         test1Field.setText("0");
         test2Field.setText("0");
         test3Field.setText("0");
         averageField.setText("0");
         countField.setText("0");
         indexField.setText("-1");
         ClassAverageField.setText("0");
         HighScoreField.setText("0");
      } else{          // Refresh with student's data
         nameField.setText(s.getName());
         test1Field.setText("" + s.getScore(1));
         test2Field.setText("" + s.getScore(2));
         test3Field.setText("" + s.getScore(3));
         averageField.setText("" + s.getAverage());
         countField.setText("" + model.size());
         indexField.setText("" + model.currentPosition());
         ClassAverageField.setText("" + model.getClassAverage());
         HighScoreField.setText("" + model.getHighScore());
      }
   }
   // Creates and returns new Student from field info
   private Student getInfoFromScreen(){
      Student s = new Student(nameField.getText());
      s.setScore(1, Integer.parseInt(test1Field.getText()));
  s.setScore(2, Integer.parseInt(test2Field.getText()));
      s.setScore(3, Integer.parseInt(test3Field.getText()));
      return s;
  }  
   // >>>>>>> The controller <<<<<<<<
   // Responds to a click on the Add button
   private class AddListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         // Get inputs, validate, and display error and quit if invalid
         Student s = getInfoFromScreen();
         String message = s.validateData();
         if (message != null){
            JOptionPane.showMessageDialog(TestScoresView.this, message);
            return;
         }
         // Attempt to add student and display error or update fields
         message = model.add(s);
         if (message != null)
            JOptionPane.showMessageDialog(TestScoresView.this, message);
         else
            displayInfo();
      }
   }
   // Responds to a click on the < button
   private class PreviousListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.previous();
         displayInfo();
      }
   }
   // Responds to a click on the > button
    private class NextListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.next();
         displayInfo();
      }
   }
    // responds to the << button w
     private class FirstListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.first();
         displayInfo();
      }
   }
      private class LastListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.last();
         displayInfo();
      }
   }
      
      private class ModifyListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         // Get inputs, validate, and display error and quit if invalid
         Student s = getInfoFromScreen();
         String message = s.validateData();
         if (message != null){
            JOptionPane.showMessageDialog(TestScoresView.this, message);
            return;
         }
         // Attempt to add student and display error or update fields
         message = model.replace(s);
         if (message != null)
            JOptionPane.showMessageDialog(TestScoresView.this, message);
         else
            displayInfo();
      }
   }
          private class HighScoreListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.getHighScore();
         displayInfo();
      }
   }
  
         private class ClassAverageListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.getClassAverage();
         displayInfo();
      }
   }
      
   // Respond
       /*
        private class PreviousListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.previous();
         displayInfo();
      }
   }
         private class PreviousListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.previous();
         displayInfo();
      }
   }
   */
   // Other listeners for modify, highest score, class average, and
   // navigation go here (exercise)
}
