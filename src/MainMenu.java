/*
Purpose : Handles the User Interface Of Loan Amortization Application
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainMenu extends JFrame implements DocumentListener
{

     // Declaration of Swing Componenets to be used

     JLabel lbl_title;
     JLabel lbl_principal;
     JLabel lbl_term;
     JLabel lbl_interest;
	
     // Labels that will be displayed when either the 'Chart' button or 'Graph' Button is clicked 

     JLabel total_payments;
     JLabel no_of_payments;

     // Labels that will used to display validation information

     JLabel val_principal;
     JLabel val_term;
     JLabel val_interest;
	
     JTextField txt_principal;
     JTextField txt_term;
     JTextField txt_interest;

     JButton chart;
     JButton graph;

     JPanel input;
     JPanel buttons;

     boolean val1,val2,val3;
     int l1,l2,l3;

     public void MainMenu()
     {	
	// Initializing the JLabels used
	
	l1=l2=l3=0;
	lbl_title = new JLabel("Loan Amortization Application");
	lbl_principal = new JLabel("Principal: ",JLabel.TRAILING);
	lbl_term = new JLabel("Term (in months): ",JLabel.TRAILING);
	lbl_interest = new JLabel("Interest (%): ",JLabel.TRAILING);

	val_principal = new JLabel();
	val_term = new JLabel();
	val_interest = new JLabel();
	
	total_payments = new JLabel();
	no_of_payments = new JLabel();

	// Initializing the JTextFields to be used and Setting their maximum text length

	// Document Listener is used to validate the text fields 

	txt_principal = new JTextField();
	txt_principal.setDocument(new JTextFieldLimit(10));

	txt_principal.getDocument().addDocumentListener(this);

	txt_term = new JTextField(5);
	txt_term.setDocument(new JTextFieldLimit(6));

	txt_term.getDocument().addDocumentListener(this);

	txt_interest = new JTextField(5);
	txt_interest.setDocument(new JTextFieldLimit(5));
	txt_interest.getDocument().addDocumentListener(this);

	// Two buttons to be used in the application

	chart = new JButton("Chart");
	graph = new JButton("Graph");
	chart.setEnabled(false);
	graph.setEnabled(false);

	//Making the buttons to handle click event

	ButtonEventHandler btnHandler = new ButtonEventHandler();
	chart.addActionListener(btnHandler);
	graph.addActionListener(btnHandler); 

	//Layout Management for the Swing Components used in the application Using a GridBagLayout	

	Container container;
	container = new Container();
	container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints gc = new GridBagConstraints();
	
	input = new JPanel();
	input.setLayout(layout);
	
	buttons = new JPanel();
	buttons.setLayout(new FlowLayout());
	
	//Defining GridBag Constraints and add components to a JPanel

	gc.gridx = 0;
	gc.gridy = 0;
	gc.fill = GridBagConstraints.HORIZONTAL;
	gc.insets = new Insets(5,5,5,5);
	input.add(lbl_principal,gc);
	gc.gridx = 1;
	gc.gridy = 0;
	input.add(txt_principal,gc);
	gc.gridx = 2;	
	gc.gridy = 0;
	input.add(val_principal,gc);

	gc.gridx = 0;
	gc.gridy = 1;
	input.add(lbl_term,gc);
	gc.gridx = 1;
	gc.gridy = 1;
	input.add(txt_term,gc);
	gc.gridx = 2;	
	gc.gridy = 1;
	input.add(val_term,gc);

	gc.gridx = 0;
	gc.gridy = 2;
	input.add(lbl_interest,gc);
	gc.gridx = 1;
	gc.gridy = 2;
	input.add(txt_interest,gc);	
	gc.gridx = 2;	
	gc.gridy = 2;
	input.add(val_interest,gc);

	buttons.add(chart);
	buttons.add(graph);
	gc.gridx = 1;
	gc.gridy = 4;
	input.add(total_payments,gc);

	gc.gridx = 1;
	gc.gridy = 5;
	input.add(no_of_payments,gc);

	gc.gridx = 1;
	gc.gridy = 6;
	input.add(buttons,gc);

	// Adding the components to a Container

	container.add(input);
	add(container,BorderLayout.WEST);
	setTitle("Loan Amortization Application");
	setSize(650,300);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Making the JFrame to appear in the center screen of user's computer

        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        setLocation(x, y);

	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
     
}

	/* Document Listener Methods 

	Purpose : Ensures that the buttons get enabled only if all text fields are not empty and all entries are valid

	*/

	// This is called when an an entry is made in the text field

	public void insertUpdate(DocumentEvent aEvent)
	{
		// Defining a Regular Expression to accept only valid characters
		//String regex = "[.]*[1-9]+[.]?[0-9]*";
		String regex = "[0-9]+[.]?[0-9]*";
		String value;
		value = txt_principal.getText().trim();	
		l1 = value.length();
		if(!value.matches(regex) && l1 > 0)
		{
			val_principal.setText("Enter a valid input (A positive numeric value)");		
			val1 = false;
		}
		else
		{
			val_principal.setText("");
			val1 = true;
		}
		value = txt_term.getText().trim();	
		l2 = value.length();
		if(!value.matches(regex) && l2>0 )
		{		
			val_term.setText("Enter a valid input (A positive numeric value)");
			val2 = false;
		}
		else
		{
			val_term.setText("");
			val2 = true;
		}
		value = txt_interest.getText().trim();
		l3 = value.length();
		if(!value.matches(regex)  && l3 > 0)
		{		
			val_interest.setText("Enter a valid input (A positive numeric value)");
			val3 = false;
		}
		else
		{
			val_interest.setText("");
			val3 = true;
		}
		if(l1 > 0 && l2 > 0 && l3 > 0 && val1 && val2 && val3)
		{
			chart.setEnabled(true);
			graph.setEnabled(true);
		}   
		else
		{
			chart.setEnabled(false);
			graph.setEnabled(false);
		}
	}

	// This is called when a text is removed from the text field

	public void removeUpdate(DocumentEvent aEvent)
	{	 
		String regex = "[0-9]+[.]?[0-9]*";
		String value;
		value = txt_principal.getText().trim();	
		l1 = value.length();
		if(!value.matches(regex) && l1 > 0)
		{
			val_principal.setText("Enter a valid input (A positive numeric value)");		
			val1 = false;
		}
		else
		{
			val_principal.setText("");
			val1 = true;
		}
		value = txt_term.getText().trim();	
		l2 = value.length();
		if(!value.matches(regex) && l2>0 )
		{		
			val_term.setText("Enter a valid input (A positive numeric value)");
			val2 = false;
		}
		else
		{
			val_term.setText("");
			val2 = true;
		}
		value = txt_interest.getText().trim();
		l3 = value.length();
		if(!value.matches(regex)  && l3 > 0)
		{		
			val_interest.setText("Enter a valid input (A positive numeric value");
			val3 = false;
		}
		else
		{
			val_interest.setText("");
			val3 = true;
		}
		if(l1 > 0 && l2 > 0 && l3 > 0 && val1 && val2 && val3)
		{
			chart.setEnabled(true);
			graph.setEnabled(true);
		}   
		else
		{
			chart.setEnabled(false);
			graph.setEnabled(false);
		}
	}
	public void changedUpdate(DocumentEvent aEvent){}

	class ButtonEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == chart)
			{				
				// Converting the values from textfields to double
				double principal,interest,term;
				principal = Double.valueOf(txt_principal.getText().trim()).doubleValue();
				interest = Double.valueOf(txt_interest.getText().trim()).doubleValue();
				term = Double.valueOf(txt_term.getText().trim()).doubleValue();
				

				// Creating an object for the Calculations class 

				Calculations c = new Calculations(principal,interest,term);
				
				
				// Creating an object for the Chart Display class
				ChartDisplay chartDisp = new ChartDisplay(c.construct_table);

				no_of_payments.setText("Monthly Payment : $" + c.M);	
				total_payments.setText("Total Payment : $" + c.totalM);
			}
			else
			{
				double principal = Double.valueOf(txt_principal.getText().trim()).doubleValue();
				double interest = Double.valueOf(txt_interest.getText().trim()).doubleValue();
				double term = Double.valueOf(txt_term.getText().trim()).doubleValue();
				Calculations c = new Calculations(principal,interest,term);
				no_of_payments.setText("Monthly Payment : $" + c.M);	
				total_payments.setText("Total Payment : $" + c.totalM);
				new GraphDisplay(c.construct_table,term,principal);
			}
		}
	}
}

  
  // JTextFieldLimit :  Used to limit the number of characters that are permitted inside a JTextField

  class JTextFieldLimit extends PlainDocument 
  {
  	private int limit;
  
 	 // This creates a Plain Document with a maximum length called 'limit'
  
 	JTextFieldLimit(int limit) 
	{
    		super();
    		this.limit = limit;
  	}

  	JTextFieldLimit(int limit, boolean upper) 
	{
    		super();
    		this.limit = limit;
  	}

  	// Used to Insert a string to a plain document

  	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException 
	{
    		if (str == null)
      		return;
    	/*
		If the length of the string entered is less than the limit then it is allowed, else the string is not inserted into the Text Field
    	*/
        	if ((getLength() + str.length()) <= limit) 
		{
        		super.insertString(offset, str, attr);
    		}
  	}
  }
