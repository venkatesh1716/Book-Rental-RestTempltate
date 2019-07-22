package book;

import RestTemplateService.RestTemplateService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class DeleteSWTBook {

    RestTemplateService restTemplateService = new RestTemplateService();

    public DeleteSWTBook(final Display display) {
        final int result = 0;
        final Shell shell = new Shell(display);
        shell.setSize(1000, 700);
        shell.setText("Book Insert");
        shell.setMaximized(true);

        Label lbl= new Label(shell, SWT.NONE);
        lbl.setText("Enter the book id to delete");
        lbl.setBounds(50,50,250,30);
        Text text= new Text(shell, SWT.NONE);
        text.setBounds(250,50,250,30);
        text.setFocus();
        Button btn= new Button(shell, SWT.PUSH);
        btn.setText("delete");
        btn.setBounds(100,100,100,30);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                String id=text.getText();
                restTemplateService.deleteBook(id);
                MessageBox msg2 = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY);
                msg2.setMessage("Book deleted successfully");
                msg2.open();
                if (id.isEmpty()){
                    MessageBox msg1 = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY);
                    msg1.setText("Warning");
                    msg1.setMessage("please enter the book id");
                    msg1.open();
                }
                  shell.dispose();
                }
        });

        shell.open();
}
}
