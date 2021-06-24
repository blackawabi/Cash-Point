# Cash-Point
Assignment of Java course\
ATM made by JOptionPane

#Cash Point properties:
• Cash balance is stored as floating-point numbers, display up to two decimal places.\
• Deposit user input is assumed to be integer, no type checking is needed. Only check for a valid range.\
• Withdrawal user input is assumed to be integer, no type checking; Round & breakdown amount based on
provided banknotes. Converted HKD might be of floating point. Check if balance is enough first before
actual withdrawal.\
• If user clicks OK to finish the balance checking, deposit or withdrawal, return to main menu.\
• If user clicks Cancel, No or Close dialogue buttons, it means "regret", so do nothing and return to the
previous menu.\
• If any choice or amount text input is invalid, pop a warning dialog and then request to input again.\
