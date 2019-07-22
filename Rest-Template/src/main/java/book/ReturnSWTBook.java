package book;


import RestTemplateService.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class ReturnSWTBook {

        RestTemplateService restTemplateService = new RestTemplateService();

        public ReturnSWTBook(final Display display) {
            final int result = 0;
            final Shell shell = new Shell(display);
            shell.setSize(1000, 700);
            shell.setText("Book Return");
            shell.setMaximized(true);

            Label lbl= new Label(shell, SWT.NONE);
            lbl.setText("Enter the Borrower Id to delete");
            lbl.setBounds(50,50,250,30);
            Text text= new Text(shell, SWT.NONE);
            text.setBounds(250,50,250,30);
            text.setFocus();
            Button btn= new Button(shell, SWT.PUSH);
            btn.setText("delete");
            btn.setBounds(100,100,100,40);
            btn.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent selectionEvent) {
                    String id=text.getText();
                    restTemplateService.deleteBorrower(id);
                    MessageBox msg2 = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY);
                    msg2.setMessage("Book returned successfully");
                    msg2.open();
                    if (id.isEmpty()){
                        MessageBox msg1 = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY);
                        msg1.setText("Warning");
                        msg1.setMessage("please enter the Borrower id");
                        msg1.open();
                    }
                    shell.dispose();
                }
            });
            Button showAllbtn = new Button(shell, SWT.PUSH);
            showAllbtn.setText("Show all Borrowers List");
            showAllbtn.setBounds(400, 250, 200, 40);
            showAllbtn.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent selectionEvent) {
                    new BorrowerListSWT(display);
                }
            });

            Button back = new Button(shell, SWT.PUSH);
            back.setBounds(250, 250, 100, 40);
            back.setText("Home page");
            back.setBackground(new Color(display, 255, 0, 255));
            back.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent selectionEvent) {
                    shell.dispose();
                }
            });

            shell.open();
        }
    }

