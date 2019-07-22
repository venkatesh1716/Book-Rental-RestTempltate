package book;

import RestTemplateService.RestTemplateService;
import entity.Book;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class DisplayBooksSWT {
    private Table tab;
    RestTemplateService restTemplateService=new RestTemplateService();


    public DisplayBooksSWT(Display display) {

        final Shell shell = new Shell(Display.getDefault());
        shell.setSize(1000, 700);
        shell.setLayout(new GridLayout(1, false));
        shell.setText("list of books");
        shell.setMaximized(true);
        {
            tab = new Table(shell, SWT.VIRTUAL | SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
            tab.setVisible(true);
            tab.setLinesVisible(true);
            tab.setHeaderVisible(true);
            tab.setItemCount(1);
            tab.addListener(SWT.SetData, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    try {
                      List<Book>books = restTemplateService.getAllBooks();

                        for (Book book:books) {
                            TableItem item = new TableItem(tab, SWT.NONE);
                            item.setText(new String[]
                                    {String.valueOf(book.getBookID()), book.getAuthorname(), book.getTitle(), book.getIsbn(),
                                            book.getAcademic(), book.getTotalcount(), book.getStatus()});
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            tab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                TableColumn book_id = new TableColumn(tab, SWT.NONE);
                book_id.setWidth(150);
                book_id.setText("Book Id");
            }

            {
                TableColumn author_name = new TableColumn(tab, SWT.NONE);
                author_name.setWidth(150);
                author_name.setText("Author Name");
            }

            {
                TableColumn book_name = new TableColumn(tab, SWT.NONE);
                book_name.setWidth(150);
                book_name.setText("Book Name");
            }

            {
                TableColumn isbn = new TableColumn(tab, SWT.NONE);
                isbn.setWidth(150);
                isbn.setText("isbn");
            }

            {
                TableColumn BookType = new TableColumn(tab, SWT.NONE);
                BookType.setWidth(150);
                BookType.setText("Academic/Non-Academic");
            }
            {
                TableColumn stock2 = new TableColumn(tab, SWT.NONE);
                stock2.setWidth(150);
                stock2.setText("Stock");
            }
            {
                TableColumn status = new TableColumn(tab, SWT.NONE);
                status.setWidth(150);
                status.setText("status");
            }
        }

        Button homebtn = new Button(shell, SWT.PUSH);
        homebtn.setBounds(250, 380, 100, 40);
        homebtn.setText("prev page");
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

