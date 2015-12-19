package expertsystem;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;


public class MainFrame extends JFrame {
    
    private static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final Tests tests;
    private static int index = 1;
    private int[] checkedAnswers = new int[20];
    
    public MainFrame(Tests tests) {
        initComponents();
        this.tests = tests;
        finishBtn.setEnabled(false);
        createListeners();
        setTest();
        setVisible(true);
    }
    
    private void setTest() {
        setTitle("Question " + index + "/20");
        questionArea.setText(tests.getTest(index-1).getQuestion());
        answer1.setText(tests.getTest(index-1).getAnswer(1));
        answer2.setText(tests.getTest(index-1).getAnswer(2));
        answer3.setText(tests.getTest(index-1).getAnswer(3));
        
        switch (index) {
            case 1: {
                previousBtn.setEnabled(false);
            } break;        
            case 20: {
                nextBtn.setEnabled(false);
                finishBtn.setEnabled(true);
            } break;
            default: {
                nextBtn.setEnabled(true);
                previousBtn.setEnabled(true);
            }
        }
    }
    
    private boolean checkQuestions() {
        boolean result = true;
        for (int i = 0; i < 20; i++) {
            if (checkedAnswers[i] == 0) {
                result = false;
            }
        }
        return result;
    }
    private void finishTest() {
        if (!checkQuestions()) {
            JOptionPane.showMessageDialog(this, "Вы ответили не на все вопросы!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int counter = 0;
            String incorrectAnswers = "";
            for (int i = 0; i < 20; i++) {
                if (checkedAnswers[i] == tests.getTest(i).getTrueAnswerNumber()) {
                    counter++;
                }
                else {
                    incorrectAnswers += (i+1) + ", ";
                }
            }
            if (counter < 20) {
                incorrectAnswers = "\n" + "Номера неправильных ответов: " + incorrectAnswers;
            }
            JOptionPane.showMessageDialog(this, "Количество правильных ответов: " + counter + incorrectAnswers, "Результаты теста", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(screenWidth/4,screenHeight/4,screenWidth/2,screenHeight/2);

        questionArea.setColumns(20);
        questionArea.setLineWrap(true);
        questionArea.setRows(5);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);
        questionArea.setFont(new java.awt.Font("Tahoma", 0, 16));
        scrollPane.setViewportView(questionArea);

        previousBtn.setText("Назад");
        nextBtn.setText("Далее");
        finishBtn.setText("Завершить тест");

        buttonGroup.add(answer1);
        buttonGroup.add(answer2);
        buttonGroup.add(answer3);    

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(mainPanelLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(scrollPane))
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(187, 187, 187)
                        .add(previousBtn)
                        .add(25, 25, 25)
                        .add(finishBtn)
                        .add(26, 26, 26)
                        .add(nextBtn)
                        .add(0, 181, Short.MAX_VALUE)))
                .addContainerGap())
            .add(GroupLayout.TRAILING, answer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(GroupLayout.TRAILING, answer2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(answer3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(mainPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(previousBtn)
                    .add(nextBtn)
                    .add(finishBtn))
                .add(17, 17, 17)
                .add(answer1)
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(answer2)
                .addPreferredGap(LayoutStyle.UNRELATED)
                .add(answer3)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void createListeners() {
        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                if (e.getActionCommand().equals("Далее")) {
                    index++;
                    setTest();
                }
                else if (e.getActionCommand().equals("Назад")) {
                    index--;
                    setTest();
                } 
                else if (e.getActionCommand().equals("Завершить тест")) {
                    finishTest();
                }
                
                if (!(e.getActionCommand().equals("Завершить тест"))) {
                    buttonGroup.clearSelection();
                    if (checkedAnswers[index-1] != 0) {
                        switch (checkedAnswers[index-1]) {
                            case 1: answer1.setSelected(true); break;
                            case 2: answer2.setSelected(true); break;
                            case 3: answer3.setSelected(true); break;
                        }
                    }
                }
            }
        };
        
        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answer1.isSelected()) {
                    checkedAnswers[index-1] = 1;
                } 
                else if (answer2.isSelected()) {
                    checkedAnswers[index-1] = 2;
                }
                else if (answer3.isSelected()) {
                    checkedAnswers[index-1] = 3;
                }
                
                if (checkQuestions()) {
                    finishBtn.setEnabled(true);
                }
            }
        };   
        
        nextBtn.addActionListener(btnListener);
        previousBtn.addActionListener(btnListener);
        finishBtn.addActionListener(btnListener);
        
        answer1.addActionListener(radioListener);       
        answer2.addActionListener(radioListener);       
        answer3.addActionListener(radioListener);       
    }
    
    private JPanel mainPanel = new JPanel();   
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton answer1 = new JRadioButton();
    private JRadioButton answer2 = new JRadioButton();
    private JRadioButton answer3 = new JRadioButton();
    private JButton nextBtn = new JButton();
    private JButton previousBtn = new JButton();
    private JButton finishBtn = new JButton();
    private JTextArea questionArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane();
}
