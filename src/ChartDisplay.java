/*
Purpose : Handling the Chart Display for Loan Amortization Application
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChartDisplay extends JFrame
{
	JPanel jpanel;
	JScrollPane jscrollpane;
	JTable jtable;

	ChartDisplay(String construct_table[][])
	{

		String columnNames[] = {"Payment","Amount ($) ","Principal ($)","Interest ($) ","Balance ($)"};

		// Creating a JTable and adding it to a frame
		jtable = new JTable(construct_table,columnNames){

		// Disable editing in JTable as it is not required

		  public boolean isCellEditable(int rowIndex, int vColIndex) {
  		      return false;
    		}
		};
		setTitle( "Chart Display" );
		setSize( 400, 250 );
		setBackground( Color.gray );
		jpanel = new JPanel();
		jpanel.setLayout( new BorderLayout() );
		getContentPane().add( jpanel );

		//ScrollPane option to display scrollbars if required

		jscrollpane = new JScrollPane(jtable);
		jpanel.add(jscrollpane,BorderLayout.CENTER);
		
		// Position the Frame in Center screen

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        	// Determine the new location of the window
        	int w = this.getSize().width;
       		int h = this.getSize().height;
        	int x = (dim.width-w)/2;
        	int y = (dim.height-h)/2;

        	// Move the window
        	this.setLocation(x, y);

		setVisible(true);
	}

}
