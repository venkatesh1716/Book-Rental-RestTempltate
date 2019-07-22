package book;
import RestTemplateService.RestTemplateService;
import entity.Borrower;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class BorrowerListSWT {

    private Table tab;
    RestTemplateService restTemplateService=new RestTemplateService();

    public BorrowerListSWT(final Display display) {

        final Shell shell = new Shell(display);
        shell.setSize(1000, 700);
        shell.setMaximized(true);
        shell.setLayout(new GridLayout(1, false));
        shell.setText("list of borrowers");

        {
            tab = new Table(shell, SWT.VIRTUAL);
            tab.setVisible(true);
            tab.setLinesVisible(true);
            tab.setHeaderVisible(true);
            tab.setItemCount(1);
            tab.addListener(SWT.SetData, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    try {
                        List<Borrower> borrowersList = restTemplateService.getAllBorrowers();
                        for (Borrower borrower:borrowersList) {
                            TableItem item = new TableItem(tab, SWT.NONE);
                            item.setText(new String[]
                                    {borrower.getFirstname(),borrower.getLastname(),borrower.getEmail(),
                                            borrower.getLoan(),borrower.getBid(),borrower.getStatus(), String.valueOf(borrower.getId())});
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            tab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                TableColumn column1 = new TableColumn(tab, SWT.NONE);
                column1.setWidth(100);
                column1.setText("First name");
            }

            {
                TableColumn column2 = new TableColumn(tab, SWT.NONE);
                column2.setWidth(100);
                column2.setText("last name");
            }

            {
                TableColumn column3 = new TableColumn(tab, SWT.NONE);
                column3.setWidth(150);
                column3.setText("email");
            }

            {
                TableColumn column4 = new TableColumn(tab, SWT.NONE);
                column4.setWidth(100);
                column4.setText("Book ID");
            }

            {
                TableColumn column5 = new TableColumn(tab, SWT.NONE);
                column5.setWidth(150);
                column5.setText("No of Days Rent");
            }
            {
                TableColumn column6 = new TableColumn(tab, SWT.NONE);
                column6.setWidth(150);
                column6.setText("Status");
            }
            {
                TableColumn column7 = new TableColumn(tab, SWT.NONE);
                column7.setWidth(150);
                column7.setText("borrower id");
            }
        }

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(250, 200, 100, 40);
        homebtn.setText("Prev page");
        homebtn.setBackground(new Color(display, 255, 0, 255));
        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

        shell.open();
        shell.layout();

    }
}


