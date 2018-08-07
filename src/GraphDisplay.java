import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GraphDisplay extends JFrame
{
	int t;
	int balance[]; // y axis values
	int months[]; // x axis values
	
	GraphDisplay(String table[][], double term, double principal)
	{
		t = (int)term;
		balance = new int[t];
		months = new int[t];
		
		// The following loop is used to scale down the values so that it can be plotted as a graph
		for(int i=1;i<=t;i++)
		{
			balance[i-1] =(int)((Double.parseDouble(table[i-1][4]) / principal) * 500);
			months[i-1] = (int)((Double.parseDouble(table[i-1][0]) / term) * 500);
		}
		JPanel panel = new JPanel(){
		public void paintComponent (Graphics g) 
		{
			Graphics2D graphComponent = (Graphics2D)g;	
			graphComponent.setColor(Color.BLACK);

			// Shifting the origin to (50,50)

			graphComponent.drawLine(50,550,550,550); // Horizontal Axis
			graphComponent.drawLine(50,50,50,550); // Vertical Axis

			// Setting Stroke to increase the thickness of points and lines

			Stroke k = new BasicStroke(4);
			graphComponent.setStroke(k);

			int i;
			// Plotting Lines
			for(i=0;i<t-1;i++)
			{
				graphComponent.setColor(Color.GREEN);
				// +50 is used as the origin has been shifted from (0,0) to (50,50) in the screen for plotting
				graphComponent.drawLine(months[i]+50,(500-balance[i])+50,months[i+1]+50,(500-balance[i+1])+50);
			}
			
			// Plotting Points
				for(i=0;i<t;i++)
				{
					graphComponent.setColor(Color.RED);
					graphComponent.drawLine(months[i]+50,(500-balance[i])+50,months[i]+50,(500-balance[i])+50);
				}
				graphComponent.setColor(Color.BLACK);	
				graphComponent.drawString("X Axis: Term (in Months)",250,575);
				graphComponent.drawString("Y Axis: Remaining Balance ($)",25,50);
				graphComponent.drawString("Plot Showing Decline of Remaining Balance over time",80,20);
			}

		};
		add(panel);
		setSize(630,630);
		setTitle("Graph Display");
		setDefaultLookAndFeelDecorated(true);
		setResizable(false);
		
		// Move the frame center screen

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

       		// Determine the new location of the window
        	int w = this.getSize().width;
        	int h = this.getSize().height;
        	int x = (dim.width-w)/2;
        	int y = (dim.height-h)/2;

        	// Move the window
        	setLocation(x, y);

		setVisible(true);
	}
}
