/*
Purpose : Class that handles the Calculations that are necessary for the application
*/

public class Calculations
{
        public double M; // Monthly Payment needed to be paid
        double P; // Principal, the initial amount of the loan
        double J; // Monthly Interest to pe paid
        double N; // Number of terms (months)
        double I; // Annual interest rate

        double H; // Current Monthly Interest
        double C; // Amount of Principal paid for that month
        double Q; // New balance of Principal after that month

        public double totalM;
        double totalC,totalH; // Total of each value
    
        int i; // Counter for Payment Number

	public String construct_table[][]; // Chart to contain the amortization schedule
	
	/*
	Calculations Constructor

	Input : Principal, Interest, Term
	Output : Constructs a two dimensional array that contains the amortization schedule 

	*/

	Calculations(double principal, double interest, double term)
	{
		P = principal;
                I = interest;
                N = term;

                // Calculating Monthly Interest (J)

                J = I / (12.00 * 100.00);

                // Calculating the monthly payment 

                M = (P * J) / (1-Math.pow(1+J,-N));
                M = Math.round(M * 100.00) / 100.00; // Rounding off M to two decimal places
                i = 1;
                Q = P; // Initially Q is equal to P

                totalM = 0.00;
                totalC = 0.00;
                totalH = 0.00;

                //double rH, rC, rQ;
                int t = (int)term;

                // Creating arrays to be used in Table Construction

                construct_table = new String[t][5];
		for(;i<=t;)
                {
                        H = P * J;
                        H = Math.round(H*100.00) / 100.00;
                        C = M - H;	
                        C = Math.round(C*100.00) / 100.00;
                        Q = P - C;
                        Q = Math.round(Q*100.00) / 100.00;
			
                        totalM = totalM + M;
                        totalC = totalC + C;
                        totalH = totalH + H;
                        if(Q<1) Q=0.00;
                        // Adding all values to the table
			// This array will contain the Amortization Schedule

                        construct_table[i-1][0] = Integer.toString(i);
                        construct_table[i-1][1] = Double.toString(M);
                        construct_table[i-1][2] = Double.toString(C);
                        construct_table[i-1][3] = Double.toString(H);
                        construct_table[i-1][4] = Double.toString(Q);

                        //System.out.println("[" + i + ", " + M + ", " + C + ", " + H + ", " + Q + "]");
                        P = Q;
                        i++;

                }
		totalM = Math.round(totalM * 100.00) / 100.00;
                totalH = Math.round(totalH * 100.00) / 100.00;


	}
}
