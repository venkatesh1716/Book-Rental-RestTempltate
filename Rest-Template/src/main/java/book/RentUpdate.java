package book;

import RestTemplateService.RestTemplateService;
import entity.Borrower;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class RentUpdate {

    String str1;
    String str2;
    String str3;
    String str4;
    String str5;

    RestTemplateService restTemplateService=new RestTemplateService();

    Label[] labels = new Label[7];  // array of labels
    final Text[] textbox = new Text[7];//array of text boxes

    final Shell shell = new Shell();

    public RentUpdate(final Display display) {

        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setText("Rent Book");

        Button button = new Button(shell, SWT.PUSH);
        button.setText("Rent");
        button.setBounds(200, 400, 100, 40);
        button.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

        labels[0] = new Label(shell, SWT.BORDER);
        labels[0].setText("Enter First name");
        labels[0].setBounds(30, 40, 160, 30);
        textbox[0] = new Text(shell, SWT.BORDER);
        textbox[0].setBounds(200, 40, 200, 30);
        textbox[0].setFocus();

        labels[1] = new Label(shell, SWT.BORDER);
        labels[1].setText("Enter Last Name");
        labels[1].setBounds(30, 100, 160, 30);
        textbox[1] = new Text(shell, SWT.BORDER);
        textbox[1].setBounds(200, 100, 200, 30);

        labels[2] = new Label(shell, SWT.BORDER);
        labels[2].setText("Enter Email Id:");
        labels[2].setBounds(30, 160, 160, 30);
        textbox[2] = new Text(shell, SWT.BORDER);
        textbox[2].setBounds(200, 160, 200, 30);
        final Label lbl = new Label(shell, SWT.BORDER);
        lbl.setBounds(420, 160, 150, 30);
        lbl.setForeground(new Color(display, 252, 5, 16));
        textbox[2].addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent verifyEvent) {

                boolean status = EmailValidation.email_Valid(textbox[2].getText());
                if (status) {
                    lbl.setText("Email is accepted");
                } else {
                    lbl.setText("Email is not accepted");
                }
            }
        });

        labels[3] = new Label(shell, SWT.BORDER);
        labels[3].setText("Enter Book Id:*");
        labels[3].setBounds(30, 220, 160, 30);
        textbox[3] = new Text(shell, SWT.BORDER);
        textbox[3].setBounds(200, 220, 200, 30);
        textbox[3].setTextLimit(5);
        textbox[3].addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String allowedCharacters = "0123456789";
                String text = e.text;
                for (int index = 0; index < text.length(); index++) {
                    char character = text.charAt(index);
                    boolean isAllowed = allowedCharacters.indexOf(character) > -1;
                    if (!isAllowed) {
                        e.doit = false;
                        return;
                    }
                }
            }
        });


        labels[4] = new Label(shell, SWT.BORDER);
        labels[4].setText("Enter Rent days:");
        labels[4].setBounds(30, 300, 160, 30);
        textbox[4] = new Text(shell, SWT.BORDER);
        textbox[4].setBounds(200, 300, 200, 30);
        textbox[4].setTextLimit(5);


        textbox[4].addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String allowedCharacters = "0123456789";
                String text = e.text;
                for (int index = 0; index < text.length(); index++) {
                    char character = text.charAt(index);
                    boolean isAllowed = allowedCharacters.indexOf(character) > -1;
                    if (!isAllowed) {
                        e.doit = false;
                        return;
                    }
                }
            }
        });

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                str1 = textbox[0].getText();
                str2 = textbox[1].getText();
                str3 = textbox[2].getText();
                str4 = textbox[3].getText();
                str5 = textbox[4].getText();

                if (str1.isEmpty() || str2.isEmpty() || str3.isEmpty()
                        || str4.isEmpty() || str5.isEmpty()) {
                    MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING | SWT.CANCEL);
                    msg.setText("Warning!!!");
                    msg.setMessage("Please enter all details");
                    msg.open();
                }
                    try {
                        Borrower borrower = new Borrower(str1, str2, str3, str5, str4);
                        restTemplateService.RentBook(borrower,str4);
                        shell.dispose();
                    }catch (Exception e){
                        MessageBox msg1 = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
                        msg1.setMessage("Book Renting failed");
                        msg1.open();
                        shell.dispose();
                        e.printStackTrace();
                    }
            }
        });

        textbox[0].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {

                if (key.keyCode == SWT.CR) {
                    textbox[1].setFocus();
                }
            }
        });

        textbox[1].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    textbox[2].setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    textbox[0].setFocus();
                }
            }
        });

        textbox[2].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.CR) {
                    textbox[3].setFocus();
                } else if (key.keyCode == SWT.ESC) {
                    textbox[1].setFocus();
                }
            }
        });
        final Label lblMandatory = new Label(shell, SWT.NONE);
        lblMandatory.setBounds(30, 260, 300, 30);
        lblMandatory.setForeground(new Color(display, 252, 5, 16));
        textbox[3].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                str4 = textbox[3].getText();

                if (!str4.isEmpty()) {
                    if (key.keyCode == SWT.CR) {
                        textbox[4].setFocus();
                    } else if (key.keyCode == SWT.ESC) {
                        textbox[2].setFocus();
                    }
                } else {
                    lblMandatory.setText("* Marked fields are mandatory");
                }
            }
        });

        textbox[4].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.keyCode == SWT.ESC) {
                    textbox[3].setFocus();

                }
            }
        });

        Button showallbtn = new Button(shell, SWT.PUSH);
        showallbtn.setText("Show the Borrowers list");
        showallbtn.setBounds(350, 400, 200, 40);
        showallbtn.setBackground(new Color(display, 198, 113, 113));
        showallbtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new BorrowerListSWT(display);

            }
        });

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(600, 400, 100, 40);
        homebtn.setText("HomePage");
        homebtn.setBackground(new Color(display, 255, 0, 255));

        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

        shell.open();
    }

}

