package org.eclipse.swt.snippets;

/*
 * Control example snippet: update a status line when an item is armed
 *
 * For a list of all SWT example snippets see
 * http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Snippet152 {

	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		FormLayout layout = new FormLayout ();
		shell.setLayout (layout);
		final Label label = new Label (shell, SWT.BORDER);
		Listener armListener = new Listener () {
			public void handleEvent (Event event) {
				MenuItem item = (MenuItem) event.widget;
				label.setText (item.getText ());
				label.update ();
			}
		};
		Listener showListener = new Listener () {
			public void handleEvent (Event event) {
				Menu menu = (Menu) event.widget;
				MenuItem item = menu.getParentItem ();
				if (item != null) {
					label.setText (item.getText ());
					label.update ();
				}
			}
		};
		Listener hideListener = new Listener () {
			public void handleEvent (Event event) {
				label.setText ("");
				label.update ();
			}
		};
		FormData labelData = new FormData ();
		labelData.left = new FormAttachment (0);
		labelData.right = new FormAttachment (100);
		labelData.bottom = new FormAttachment (100);
		label.setLayoutData (labelData);
		Menu menuBar = new Menu (shell, SWT.BAR);
		shell.setMenuBar (menuBar);
		MenuItem fileItem = new MenuItem (menuBar, SWT.CASCADE);
		fileItem.setText ("File");
		fileItem.addListener (SWT.Arm, armListener);
		MenuItem editItem = new MenuItem (menuBar, SWT.CASCADE);
		editItem.setText ("Edit");
		editItem.addListener (SWT.Arm, armListener);
		Menu fileMenu = new Menu (shell, SWT.DROP_DOWN);
		fileMenu.addListener (SWT.Hide, hideListener);
		fileMenu.addListener (SWT.Show, showListener);
		fileItem.setMenu (fileMenu);
		String [] fileStrings = {"New", "Close", "Exit" };
		for (int i = 0; i < fileStrings.length; i++) {
			MenuItem item = new MenuItem (fileMenu, SWT.PUSH);
			item.setText (fileStrings [i]);
			item.addListener (SWT.Arm, armListener);
		}
		Menu editMenu = new Menu (shell, SWT.DROP_DOWN);
		editMenu.addListener (SWT.Hide, hideListener);
		editMenu.addListener (SWT.Show, showListener);
		String [] editStrings = {"Cut", "Copy", "Paste" };
		editItem.setMenu (editMenu);
		for (int i = 0; i < editStrings.length; i++) {
			MenuItem item = new MenuItem (editMenu, SWT.PUSH);
			item.setText (editStrings [i]);
			item.addListener (SWT.Arm, armListener);
		}
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}
