package book;
import RestTemplateService.RestTemplateService;
import entity.Book;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class BookListDB {

    private Table tab;
    RestTemplateService restTemplateService= new RestTemplateService();

    void searchbook(String id, final Display display) {
        final String id1 = id;
        final Shell shell = new Shell();
        shell.setSize(700, 700);
        shell.setMaximized(true);
        shell.setLayout(new GridLayout(1, false));
        shell.setText("list of books");
        {
            tab = new Table(shell, SWT.VIRTUAL | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
            tab.setVisible(true);
            tab.setLinesVisible(true);
            tab.setHeaderVisible(true);
            tab.setItemCount(1);
            tab.addListener(SWT.SetData, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    try {
                       Book book=restTemplateService.getBooksById(id);
                        TableItem item = new TableItem(tab, SWT.NONE);
                        item.setText(new String[]
                                {String.valueOf(book.getBookID()), book.getAuthorname(), book.getTitle(), book.getIsbn(),
                                        book.getAcademic(), book.getTotalcount(), book.getStatus()});

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            Button btn1 = new Button(shell, SWT.PUSH);
            btn1.setText("Show list of Books");
            btn1.setBounds(400, 200, 100, 40);
            btn1.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent selectionEvent) {
                    new DisplayBooksSWT(display);
                }
            });

            tab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                TableColumn column1 = new TableColumn(tab, SWT.NONE);
                column1.setWidth(100);
                column1.setText("Book id");

            }

            {
                TableColumn column2 = new TableColumn(tab, SWT.NONE);
                column2.setWidth(100);
                column2.setText("Author name");
            }

            {
                TableColumn column3 = new TableColumn(tab, SWT.NONE);
                column3.setWidth(100);
                column3.setText("Book title");
            }

            {
                TableColumn column4 = new TableColumn(tab, SWT.NONE);
                column4.setWidth(150);
                column4.setText("isbn");
            }

            {
                TableColumn column5 = new TableColumn(tab, SWT.NONE);
                column5.setWidth(100);
                column5.setText("academic");
            }
            {
                TableColumn column6 = new TableColumn(tab, SWT.NONE);
                column6.setWidth(100);
                column6.setText("stock");
            }
            {
                TableColumn column7 = new TableColumn(tab, SWT.NONE);
                column7.setWidth(150);
                column7.setText("status");
            }

        }

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(250, 200, 100, 40);
        homebtn.setText("Prev page");
        homebtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                shell.dispose();
            }
        });

        shell.open();
    }
}
