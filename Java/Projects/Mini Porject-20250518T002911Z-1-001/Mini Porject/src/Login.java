import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Login extends JFrame{
    private JTextField userNFieldLogin;
    private JPasswordField passwordFieldLogin;
    private JPanel LoginPanel;
    private JButton loginButtonLogin;
    private JButton clearButtonLogin;
    private JCheckBox showPasswordCheckBoxLogin;
    private JPanel RegisterPanel;
    private JPanel LoginOrRegisterPanel;
    private JButton loginButtonMain;
    private JButton signUpButtonMain;
    private JButton signUpButtonRegister;
    private JButton clearButtonRegister;
    private JTextField userNFieldRegister;
    private JPasswordField passwordFieldRgstr;
    private JPasswordField cnfrmPasswordFieldRgster;
    private JPanel LoginOrSignUpPanel;
    private JPanel MainPanel;
    private JButton guestButton;
    private JCheckBox showPasswordCheckBoxRegister;
    private JButton goBackButtonRegister;
    private JButton goBackButtonLogin;
    private JButton backButton;
    private static Path folderPath;

    public Login(String title){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocation(650, 300);
        this.setSize(400,210);
        this.setVisible(true);
        LoginPanel.setVisible(false);
        RegisterPanel.setVisible(false);


        loginButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] wow = passwordFieldLogin.getPassword();
                String woo = new String(wow);
                if (woo.isEmpty() || userNFieldLogin.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Fill out all information!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
//                    if(woo.equals("Admin5064") && userNFieldLogin.getText().equals("Admin")){
//
//                    }
                        searchForAccount();


                }
            }
        });

        clearButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNFieldLogin.setText("");
                passwordFieldLogin.setText("");
            }
        });

        showPasswordCheckBoxLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBoxLogin.isSelected()) {
                    passwordFieldLogin.setEchoChar('\0');
                } else {
                    passwordFieldLogin.setEchoChar('\u2022');
                }
            }
        });
        showPasswordCheckBoxRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBoxRegister.isSelected()) {
                    passwordFieldRgstr.setEchoChar('\0');
                    cnfrmPasswordFieldRgster.setEchoChar('\0');
                } else {
                    passwordFieldRgstr.setEchoChar('\u2022');
                    cnfrmPasswordFieldRgster.setEchoChar('\u2022');
                }
            }
        });

        clearButtonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNFieldRegister.setText("");
                passwordFieldRgstr.setText("");
                cnfrmPasswordFieldRgster.setText("");
            }
        });

        signUpButtonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pass1char = passwordFieldRgstr.getPassword();
                String password1 = new String(pass1char).trim();
                char[] pass2char = cnfrmPasswordFieldRgster.getPassword();
                String password2 = new String(pass2char).trim();
                String username = userNFieldRegister.getText();
                if(!(   username.isEmpty()
                        || password1.isEmpty()
                        || password2.isEmpty()
                    )
                ) {
                    if(!password1.equals(password2)){
                        JOptionPane.showMessageDialog(null,
                                "Passwords doesn't match",
                                "",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        createAccount();
                        clearButtonRegister.doClick();
                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please fill out all the missing fields",
                            "",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
        });

        loginButtonMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOrRegisterPanel.setVisible(false);


                LoginPanel.setVisible(true);
            }
        });

        signUpButtonMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOrRegisterPanel.setVisible(false);
                RegisterPanel.setVisible(true);
            }
        });

        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();

                if(key == '\n'){
                    loginButtonLogin.doClick();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Welcome!",
                        "",
                        JOptionPane.CLOSED_OPTION
                );

                boolean proceed = false;
                if (choice == JOptionPane.OK_OPTION) {

                    proceed = true;
                } else {
                    proceed = true;
                }
                if (proceed) {
                    VendingMUser.main(null);
                    VendingMUser run = new VendingMUser("Vending Machine");
                    run.setIsUserAGuest(true);
                    dispose();
                }
            }
        });
        goBackButtonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPanel.setVisible(false);
                clearButtonRegister.doClick();
                LoginOrRegisterPanel.setVisible(true);
            }
        });
        goBackButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel.setVisible(false);
                clearButtonLogin.doClick();
                LoginOrRegisterPanel.setVisible(true);
            }
        });
    }
    public void createUserFolder(){
        String usernameRgstr = userNFieldRegister.getText() + "Folder";
        try {
            // Define parent folder
            String parentFolder = System.getProperty("user.dir");
            parentFolder += "\\Mini Project Users\\Users";
            // Define subfolder inside the parent folder
            String subFolder = parentFolder + "\\" + usernameRgstr ; // Creates "SubFolder" inside "MyFiles"

            // Create the folder structure
            folderPath = Path.of(subFolder);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath); // Creates folder if it doesn’t exist
                System.out.println("Folder created at: " + folderPath.toAbsolutePath());
                createAccountPassAndUserN();
            } else {
                JOptionPane.showMessageDialog(null,
                        "This username is already taken. \nPlease choose a different one.",
                        "",
                        JOptionPane.OK_OPTION
                );
            }
        } catch (IOException e) {

        }

    }
    public void createAccountPassAndUserN(){
        String usernameRgstr = userNFieldRegister.getText();
        char[] passwordRgstr = passwordFieldRgstr.getPassword();
        String passwordRgstrStrng = new String(passwordRgstr);
        try {
            String content = usernameRgstr + "\n" + passwordRgstrStrng;
            String directoryPath = String.valueOf(folderPath); // Change this to your desired folder
            String fileName = usernameRgstr;

            // Ensure directory exists
            Path dirPath = Path.of(directoryPath);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath); // Creates folder if it doesn’t exist
            }

            // Write to the file inside the directory
            Path filePath = Path.of(directoryPath, fileName);
            Files.write(filePath, content.getBytes());

            System.out.println("File saved at: " + filePath.toAbsolutePath());
        } catch (IOException e) {

        }
        JOptionPane.showMessageDialog(null,
                "Welcome aboard!",
                "",
                JOptionPane.NO_OPTION);
    }
    public void createAccount(){
        createUserFolder();
    }

    public void searchForAccount() {
        String FolderName = userNFieldLogin.getText().trim() + "Folder";
        String name = userNFieldLogin.getText().trim();
        char[] passwordChar = passwordFieldLogin.getPassword();
        String passwordString = new String(passwordChar).trim();
        Path parentFolder = Path.of(System.getProperty("user.dir")+"\\Mini Project Users\\Users");
        Path targetFolder = parentFolder.resolve(FolderName);

        if (Files.exists(targetFolder) && Files.isDirectory(targetFolder)) {
            System.out.println(FolderName + " found inside Users!");
            Path filePath = Path.of(targetFolder + "\\" + name);
            //C:\Coding practice and progress VScode\Intermediate-programming-Activity\Mini Porject\src\Users\123Folder\123
            System.out.println(filePath);
            if (Files.exists(filePath)) {
                try {
                    List<String> lines = Files.readAllLines(filePath);
                    lines.forEach(System.out::println); // Print file content
                    if(passwordString.equals(lines.get(1))){
                       int choice = JOptionPane.showConfirmDialog(
                                null,
                                "Welcome " + name + "!",
                                "",
                                JOptionPane.CLOSED_OPTION
                        );
                        boolean proceed = false;
                        if(choice == JOptionPane.OK_OPTION) {
                            proceed = true;
                        } else {
                            proceed = true;
                        }
                        if(proceed){
                            VendingMUser.main(null);
                            VendingMUser run = new VendingMUser("Vending Machine");
                            run.setUserAccount(userNFieldLogin.getText());
                            run.setIsUserAGuest(false);
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Incorrect Passowrd!",
                                "",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                createUserFolder();
                System.out.println(FolderName + " does not exist inside folderA.");
                JOptionPane.showMessageDialog(null,
                        "Incorrect Password!",
                        "",
                        JOptionPane.OK_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "This account does not exist. \nPlease check your username or sign up.",
                    "",
                    JOptionPane.OK_OPTION);
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Would you like to create an account?",
                    "",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION){
                LoginPanel.setVisible(false);
                RegisterPanel.setVisible(true);
            } else if (choice == JOptionPane.NO_OPTION){

                passwordFieldLogin.setText("");
            }
        }



    }



    public static void main(String[] args) {
        JFrame myFrame = new Login("Login");
    }
}
