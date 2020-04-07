import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data <Name Surname LastName dd.mm.yyyy> ('exit' to close): ");
        String[] fields;
        boolean check_fail = false;
        LocalDate birthday;
        StringBuilder result;
        while (!check_fail) {
            check_fail = true;
            String data = sc.nextLine();
            if(data.equals("exit")) return;
            fields = data.split(" ");
            if (fields.length != 4){
                System.out.println("Wrong number of parameters, try again!");
                check_fail = false;
                continue;
            }
            for (int i = 0; i < 3; i++){
                check_fail &= !Pattern.matches("^.*([^a-zA-Z]+).*$", fields[i]);
                System.out.println(Pattern.matches("^.*([^a-zA-Z]+).*$", fields[i]));
            }
            if (!check_fail){
                System.out.println("Wrong names, try again!");
                continue;
            }
            try {
                birthday = LocalDate.parse(fields[3], dtf);
            } catch (DateTimeException e) {
                check_fail = false;
                System.out.println("Wrong date, try again!");
                continue;
            }
            LocalDate date_current = LocalDate.now();
            assert birthday != null;
            Period period = Period.between(birthday, date_current);
            if (period.getYears() < 0){
                check_fail = false;
                System.out.println("Birthday cant be more than current date), try again!");
            }
            else{
                result = new StringBuilder();
                for (int i = 0; i < 3; i++){
                    result.append(Character.toUpperCase(fields[i].charAt(0)));
                }
                System.out.println("Result: " + result + " : " + period.getYears() + " years");
            }
        }
    }
}
