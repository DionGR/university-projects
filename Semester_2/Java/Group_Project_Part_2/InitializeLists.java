class InitializeLists {
    // Creating 3 Type A expenses and 3 Type B expenses.
    public static ExpenseTypeA Hotel = new ExpenseTypeA("01A","Hotel Stay", 750d, 75d, "Nights Spent");
    public static ExpenseTypeA Travel = new ExpenseTypeA("02A", "Car Travel", 160d, 1.60, "Liters of Fuel Spent");
    public static ExpenseTypeA Overtime = new ExpenseTypeA("03A", "Overtime performed by Employee", 540d, 27d, "hour");
    public static ExpenseTypeB Taxi = new ExpenseTypeB("01B","Cabfare", 100d, 75d);
    public static ExpenseTypeB PT = new ExpenseTypeB("02B","Public Transportation", 50d, 100d);
    public static ExpenseTypeB Meal = new ExpenseTypeB("03B", "Meal on work Hours", 120, 80);
    // Creating 4 Employees.
    public static Employee John = new Employee("John","Doe",1200d);
    public static Employee Mark = new Employee("Mark","Alex",800d);
    public static Employee Peter = new Employee("Peter","Dinklage",920d);
    public static Employee Nikola = new Employee("Nikola","Tesla", 650d);
    public static Databases database = mainApp.database;

    public static void initializeExpenses() {
        database.addExpenseType(Hotel);
        database.addExpenseType(Travel);
        database.addExpenseType(Overtime);
        database.addExpenseType(Taxi);
        database.addExpenseType(PT);
        database.addExpenseType(Meal);
    }// Adding 3 Type A expenses and 3 Type B expenses to the database.

    public static void initializeEmployees() {
        database.addEmployee(John);
        database.addEmployee(Mark);
        database.addEmployee(Peter);
        database.addEmployee(Nikola);
    }// Adding 4 Employees to the database.

    public static void initializeEmployeeExpenses() {
        EmployeeExpense A01_JOHN_HOTEL = new EmployeeExpense(John, Hotel,"4 Seasons Croatia, 8 nights.", 14d);
        EmployeeExpense A02_JOHN_TAXI = new EmployeeExpense(John, Taxi,"Taxi to work.", 150d);
        EmployeeExpense A03_JOHN_TRANSPORT = new EmployeeExpense(John, PT,"Monthly transportation card.", 45d);
        EmployeeExpense A04_JOHN_MEAL = new EmployeeExpense(John, Meal,"Client lunch.", 300d);
        database.addEmployeeExpense(A01_JOHN_HOTEL);
        database.addEmployeeExpense(A02_JOHN_TAXI);
        database.addEmployeeExpense(A03_JOHN_TRANSPORT);
        database.addEmployeeExpense(A04_JOHN_MEAL);

        EmployeeExpense B01_MARK_TRAVEL = new EmployeeExpense(Mark, Travel,"Trip to Sofia.", 20d);
        EmployeeExpense B02_MARK_HOTEL = new EmployeeExpense(Mark, Hotel, "Hilton Hotel Sofia.", 5d);
        EmployeeExpense B03_MARK_OVERTIME = new EmployeeExpense(Mark, Overtime,"Worked on holidays.", 10d);
        EmployeeExpense B04_MARK_MEAL = new EmployeeExpense(Mark, Meal,"Lunch in Sofia.", 30d);
        database.addEmployeeExpense(B01_MARK_TRAVEL);
        database.addEmployeeExpense(B02_MARK_HOTEL);
        database.addEmployeeExpense(B03_MARK_OVERTIME);
        database.addEmployeeExpense(B04_MARK_MEAL);

        EmployeeExpense C01_PETER_HOTEL = new EmployeeExpense(Peter, Hotel,"Hilton Hotel Thessaloniki.", 3d);
        EmployeeExpense C02_PETER_TAXI = new EmployeeExpense(Peter, Taxi,"Taxi to work", 25d);
        EmployeeExpense C03_PETER_OVERTIME = new EmployeeExpense(Peter, Overtime,"Worked 10 extra hours.", 10d);
        EmployeeExpense C04_PETER_MEAL = new EmployeeExpense(Peter, Travel,"Trip to our HQ in Thessaloniki.",15d);
        database.addEmployeeExpense(C01_PETER_HOTEL);
        database.addEmployeeExpense(C02_PETER_TAXI);
        database.addEmployeeExpense(C03_PETER_OVERTIME);
        database.addEmployeeExpense(C04_PETER_MEAL);

        EmployeeExpense D01_NIKOLA_TRANSPORT = new EmployeeExpense(Nikola, PT,"Monthly transportation card.", 45d);
        EmployeeExpense D02_NIKOLA_OVERTIME = new EmployeeExpense(Nikola, Overtime,"Worked 3 extra hours.", 3d);
        EmployeeExpense D03_NIKOLA_HOTEL = new EmployeeExpense(Nikola, Hotel,"Grand Britannia Athens", 5d);
        EmployeeExpense D04_NIKOLA_TAXI = new EmployeeExpense(Nikola, Taxi,"Client lunch",20d);
        database.addEmployeeExpense(D01_NIKOLA_TRANSPORT);
        database.addEmployeeExpense(D02_NIKOLA_OVERTIME);
        database.addEmployeeExpense(D03_NIKOLA_HOTEL);
        database.addEmployeeExpense(D04_NIKOLA_TAXI);

        database.createActivities();
    }//Adding 4 Expenses per employee to the database.
}