import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class TeamsAttendanceBot extends JFrame implements ActionListener {

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel leaveAfterLabel;
    private JTextField leaveAfterField;
    private JLabel leaveAfterMembersLabel;
    private JTextField leaveAfterMembersField;
    private JLabel classLabel;
    private JLabel nameLabel;
    private JLabel timeLabel;
    private JLabel classOneLabel;
    private JLabel classTwoLabel;
    private JLabel classThreeLabel;
    private JTextField classOneField;
    private JTextField classTwoField;
    private JTextField classThreeField;
    private JComboBox<String> classOneBox;
    private JComboBox<String> classTwoBox;
    private JComboBox<String> classThreeBox;
    private JButton submitButton;
    private JButton executeButton;
    private String classOneStartTimeString;
    private String classTwoStartTimeString;
    private String classThreeStartTimeString;
    private String classOneMaxTimeString;
    private String classTwoMaxTimeString;
    private String classThreeMaxTimeString;
    private String usernameString;
    private String passwordString;
    private String teamNameOneString;
    private String teamNameTwoString;
    private String teamNameThreeString;
    private String urlString = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?response_type=id_token&scope=openid%20profile&client_id=5e3ce6c0-2b1f-4285-8d4b-75ee78787346&redirect_uri=https%3A%2F%2Fteams.microsoft.com%2Fgo&state=eyJpZCI6IjVmN2VkOWQ4LWJiOTctNGI1OS04YmM1LTE0OWI4M2EyYTUwMSIsInRzIjoxNjgyOTM5MzY3LCJtZXRob2QiOiJyZWRpcmVjdEludGVyYWN0aW9uIn0%3D&nonce=e0fc89ee-210d-4203-b3f7-952769dc18a4&client_info=1&x-client-SKU=MSAL.JS&x-client-Ver=1.3.4&client-request-id=f00de851-9240-44b1-8ddd-ced8370711eb&response_mode=fragment&sso_reload=true";

    public TeamsAttendanceBot() {

        super("Teams Bot");
        setLayout(new BorderLayout());

        usernameLabel = new JLabel("Username");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(20);

        classLabel = new JLabel("Class Number");
        nameLabel = new JLabel("Team Name");
        timeLabel = new JLabel("Class Time");

        String times[] = { "08:30-10:00", "10:05-11:35", "11:40-1:10" };
        classOneLabel = new JLabel("Class One");
        classOneField = new JTextField(20);
        classOneBox = new JComboBox<>(times);

        classTwoLabel = new JLabel("Class Two");
        classTwoField = new JTextField(20);
        classTwoBox = new JComboBox<>(times);

        classThreeLabel = new JLabel("Class Three");
        classThreeField = new JTextField(20);
        classThreeBox = new JComboBox<>(times);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);

        Panel panelOne = new Panel(new GridLayout(1, 2));
        panelOne.add(nameLabel);
        panelOne.add(timeLabel);

        Panel panelTwo = new Panel(new GridLayout(1, 2));
        panelTwo.add(classOneField);
        panelTwo.add(classOneBox);

        Panel panelThree = new Panel(new GridLayout(1, 2));
        panelThree.add(classTwoField);
        panelThree.add(classTwoBox);

        Panel panelFour = new Panel(new GridLayout(1, 2));
        panelFour.add(classThreeField);
        panelFour.add(classThreeBox);

        Panel panelFive = new Panel(new FlowLayout());
        panelFive.add(submitButton);
        panelFive.add(executeButton);

        Panel mainPanel = new Panel(new GridLayout(6, 2));
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(classLabel);
        mainPanel.add(panelOne);
        mainPanel.add(classOneLabel);
        mainPanel.add(panelTwo);
        mainPanel.add(classTwoLabel);
        mainPanel.add(panelThree);
        mainPanel.add(classThreeLabel);
        mainPanel.add(panelFour);

        add(mainPanel);
        add(panelFive, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                TeamsAttendanceBot app = new TeamsAttendanceBot();
                app.setSize(800, 800);
                app.setVisible(true);
                app.setDefaultCloseOperation(EXIT_ON_CLOSE);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {

            usernameString = usernameField.getText();
            passwordString = new String(passwordField.getPassword());

            teamNameOneString = classOneField.getText();
            teamNameTwoString = classTwoField.getText();
            teamNameThreeString = classThreeField.getText();

            if (classOneBox.getSelectedIndex() == 0) {

                classOneStartTimeString = "08:30";
                classOneMaxTimeString = "09:30";
            }

            else if (classOneBox.getSelectedIndex() == 1) {

                classOneStartTimeString = "10:05";
                classOneMaxTimeString = "11:05";
            }

            else {

                classOneStartTimeString = "11:40";
                classOneMaxTimeString = "12:40";
            }

            if (classTwoBox.getSelectedIndex() == 0) {

                classTwoStartTimeString = "08:30";
                classTwoMaxTimeString = "09:30";
            }

            else if (classTwoBox.getSelectedIndex() == 1) {

                classTwoStartTimeString = "10:05";
                classTwoMaxTimeString = "11:05";
            }

            else {

                classTwoStartTimeString = "11:40";
                classTwoMaxTimeString = "12:40";
            }

            if (classThreeBox.getSelectedIndex() == 0) {

                classThreeStartTimeString = "08:30";
                classThreeMaxTimeString = "09:30";
            }

            else if (classThreeBox.getSelectedIndex() == 1) {

                classThreeStartTimeString = "10:05";
                classThreeMaxTimeString = "11:05";
            }

            else {

                classThreeStartTimeString = "11:40";
                classThreeMaxTimeString = "12:40";
            }

        }

        if (e.getSource() == executeButton) {

            setExtendedState(JFrame.ICONIFIED);

            while (true) {

                LocalDateTime currenTime = LocalDateTime.now();

                DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("HH:mm");

                String formatTime = currenTime.format(myFormatter);

                // Class One

                if (formatTime.equals(classOneStartTimeString)) {

                    WebDriver driver = new ChromeDriver();

                    driver.get(urlString);

                    System.out.println("Teams Opened");

                    driver.manage().window().maximize();

                    System.out.println("Window Maximised");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("i0116")).sendKeys(usernameString);

                    System.out.println("Username Entered");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idSIButton9")).click();
                    System.out.println("Next Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("i0118")).sendKeys(passwordString);
                    System.out.println("Password Entered");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idSIButton9")).click();
                    System.out.println("Login Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idBtn_Back")).click();
                    System.out.println("No Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(8);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    String profileSelectionString = "//*[@data-test-id='" + usernameString + "']";

                    driver.findElement(By.xpath(profileSelectionString)).click();
                    System.out.println("Login Profile Selected");

                    try {
                        TimeUnit.SECONDS.sleep(120);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    String teamSelectionString = "//div[@aria-label='" + teamNameOneString + "']";

                    driver.findElement(By.xpath(teamSelectionString)).click();
                    System.out.println("Team Opened");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.xpath("//*[@aria-label='Join']")).click();
                    System.out.println("Meeting Joined");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    // Give Permissions for mic and camera
                    // Click Join Button
                    while (true) {

                        LocalDateTime currenTime1 = LocalDateTime.now();

                        DateTimeFormatter myFormatter1 = DateTimeFormatter.ofPattern("HH:mm");

                        String formatTime1 = currenTime1.format(myFormatter1);

                        if (formatTime1.equals(classOneMaxTimeString)) {

                            driver.quit();
                            break;
                        }

                    }

                }

                // Class Two
                if (formatTime.equals(classTwoStartTimeString)) {

                    WebDriver driver = new ChromeDriver();

                    driver.get(urlString);

                    System.out.println("Teams Opened");

                    driver.manage().window().maximize();

                    System.out.println("Window Maximised");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("i0116")).sendKeys(usernameString);

                    System.out.println("Username Entered");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idSIButton9")).click();
                    System.out.println("Next Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("i0118")).sendKeys(passwordString);
                    System.out.println("Password Entered");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idSIButton9")).click();
                    System.out.println("Login Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idBtn_Back")).click();
                    System.out.println("No Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    String profileSelectionString = "//*[@data-test-id='" + usernameString + "']";

                    driver.findElement(By.xpath(profileSelectionString)).click();
                    System.out.println("Login Profile Selected");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    String teamSelectionString = "//div[@aria-label='" + teamNameTwoString + "']";

                    driver.findElement(By.xpath(teamSelectionString)).click();
                    System.out.println("Team Opened");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.xpath("//*[@aria-label='Join']")).click();
                    System.out.println("Meeting Joined");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    // Give Permissions for mic and camera
                    // Click Join Button

                    while (true) {

                        LocalDateTime currenTime1 = LocalDateTime.now();

                        DateTimeFormatter myFormatter1 = DateTimeFormatter.ofPattern("HH:mm");

                        String formatTime1 = currenTime1.format(myFormatter1);

                        if (formatTime1.equals(classTwoMaxTimeString)) {

                            driver.quit();
                            break;
                        }
                    }

                }

                if (formatTime.equals(classThreeStartTimeString)) {

                    WebDriver driver = new ChromeDriver();

                    driver.get(urlString);

                    System.out.println("Teams Opened");

                    driver.manage().window().maximize();

                    System.out.println("Window Maximised");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("i0116")).sendKeys(usernameString);

                    System.out.println("Username Entered");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idSIButton9")).click();
                    System.out.println("Next Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("i0118")).sendKeys(passwordString);
                    System.out.println("Password Entered");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idSIButton9")).click();
                    System.out.println("Login Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.id("idBtn_Back")).click();
                    System.out.println("No Button Clicked");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    String profileSelectionString = "//*[@data-test-id='" + usernameString + "'']";

                    driver.findElement(By.xpath(profileSelectionString)).click();
                    System.out.println("Login Profile Selected");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    String teamSelectionString = "//div[@aria-label='" + teamNameThreeString + "']";

                    driver.findElement(By.xpath(teamSelectionString)).click();
                    System.out.println("Team Opened");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    driver.findElement(By.xpath("//*[@aria-label='Join']")).click();
                    System.out.println("Meeting Joined");

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    // Give Permissions for mic and camera
                    // Click Join Button

                    while (true) {

                        LocalDateTime currenTime1 = LocalDateTime.now();

                        DateTimeFormatter myFormatter1 = DateTimeFormatter.ofPattern("HH:mm");

                        String formatTime1 = currenTime1.format(myFormatter1);

                        if (formatTime1.equals(classThreeMaxTimeString)) {

                            driver.quit();
                            break;
                        }

                    }

                }

            }
        }

    }

}
