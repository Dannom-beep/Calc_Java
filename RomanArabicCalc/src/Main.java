import java.util.ArrayList;
import java.util.Scanner;

class Test{
        
    private final String text_input;
    private final String expected_result;

    public Test (String text_input, String expected_result){

        this.text_input = text_input;
        this.expected_result = expected_result;
    }

    public String get_text_input() {
        return text_input;
    }

    public String get_expected_result() {
        return expected_result;
    }

}

public class Main {


    public static void main(String[] args) {
        System.out.println("Введите строку Пример: 1 + 2  ");
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        sc.close(); 

        try{
            System.out.println(calc(user_input));
        }
        catch(Exception e){
            System.out.println(e);
        }


        // Для тестов
        ArrayList<Test> tests = new ArrayList<>();
        Test test_try;

        test_try = new Test("I - I","В римской системе нет нуля");
        tests.add(test_try);
        test_try = new Test("I + 1","Используются одновременно разные системы счисления");
        tests.add(test_try);
        test_try = new Test("VI / III","II");
        tests.add(test_try);
        test_try = new Test("I - II","В римской системе нет отрицательных чисел");
        tests.add(test_try);
        test_try = new Test("1","строка не является математической операцией");
        tests.add(test_try);
        test_try = new Test("1 + 2 + 2","формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        tests.add(test_try);

        
        for (Test printTest : tests){
            boolean state = false;
            try {   
                String rss = calc(printTest.get_text_input());
                if ( rss.equals( printTest.get_expected_result() )  ){
                    state = true;
                }   
                    System.out.println("________________________________________________");
                    System.out.println("| In: " + printTest.get_text_input());
                    System.out.println("| Ex: " + printTest.get_expected_result());
                    System.out.println("| Get: " + calc(printTest.get_text_input()) );
                    System.out.println("| St : " + state) ;
                
            } catch (Exception e) {

                if ( e.getMessage().equals(printTest.get_expected_result()) ){
                    state = true;
                }
                System.out.println("________________________________________________");
                System.out.println("| In: " + printTest.get_text_input() );
                System.out.println("| Ex: " + printTest.get_expected_result());
                System.out.println("| Get: " + e.getMessage() );
                System.out.println("| St : " + state) ;
            }
        }
    }
    //Основная функция для работы со строкой
    public static String calc(String input) throws Exception {
        String s_rtr = "default";
        boolean haveValidOperation = false;
        boolean isRoman_f = false;
        boolean isBothOperandValid = false;
        //Делим строку по пробелу
        String [] operands = input.split(" ");
        

        // Проверка на число элементов в массиве.
        if (operands.length < 3){
            throw new Exception("строка не является математической операцией");
        }
        if ((operands.length > 3)){
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        
        //Проверка что второй операнд - это знак операции
        haveValidOperation =  operands[1].matches("[\\-,\\+,\\*,\\/]");
        if (!haveValidOperation){
            throw new Exception("Не найден знак операции");
        }

        // Проверка два ли числа арабские от 1 до 10
        if ( isNumeric(operands[0] ) & ( isNumeric(operands[2]) ) ){
            isRoman_f = false;
            isBothOperandValid = true;
        }
        // Проверка что оба числа римские от 1 до 10
        if ( inRomanEnum(operands[0]) & inRomanEnum(operands[2]) ) {
            isRoman_f = true;
            isBothOperandValid = true;
        }
        // Даны не два арабских или римских числа от 1 до 10
        if (isBothOperandValid == false){
            throw new Exception("Используются одновременно разные системы счисления");
        }
        else{

          s_rtr =   calculate(  operands[0] , // Первое число
                                operands[2] , // Второе число
                                operands[1] , // Операнд
                                isRoman_f);  // Оба числа римские
            

        }

        return s_rtr;

    }
    // Функция калькулятор
    static String calculate(String a, String b, String operand, boolean Flag) throws Exception {

        int a_int = 0;
        int b_int = 0;
        int i_rtr = 0;
        String s_rtr = "";

        // 
        if (Flag == true){
            a_int = RomanNumber.valueOf(a).value;
            b_int = RomanNumber.valueOf(b).value;
        }
        else{ // 
            a_int = Integer.parseInt(a);
            b_int = Integer.parseInt(b);
        }

        switch (operand){
            case "+":{
                i_rtr = a_int + b_int;
                s_rtr = Integer.toString(i_rtr);
                break;
                
        
            }
            case "-":{
                // Если переданы два римских числа, выкидываем исключение если первое < второго
                if (Flag == true){ 
                    if (a_int < b_int){
                        throw new Exception("В римской системе нет отрицательных чисел");
                    }    
                }
                i_rtr = a_int - b_int;
                s_rtr = Integer.toString(i_rtr);
                break;
                
            }
            case "*":{
                i_rtr = a_int * b_int;
                s_rtr = Integer.toString(i_rtr);
                break;
                
            }
            case "/":{
                i_rtr = a_int / b_int;
                s_rtr = Integer.toString(i_rtr);
                break;
                
            }
        }
        
        if (Flag == true ){
            if ( s_rtr.equals("0") ){
                throw new Exception("В римской системе нет нуля");
            }
            return intToRoman(s_rtr);
        }
        else{
            return s_rtr;
        }
    }
    //Проверить если число находиться в enum
    static boolean inRomanEnum(String str){
        for (RomanNumber r:RomanNumber.values()) {
            if (r.name().equals(str)){
                return true;
            }
        }

        return false;
    }
    // Римские цифры от 1 до 10 
    static enum RomanNumber{
        I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10);

        private final int value;

        RomanNumber(int value){
            this.value = value;
        }

    }  
    // Проверка на арабское число от 1 до 10.
    static boolean isNumeric(String str){
        if (str == null){
            return false;
        }
        return str.matches("[1-9]|10");
        
        
    }
    // Перевод арабского числа в римское
    static String intToRoman(String int_str){
        
        int int_number = Integer.parseInt(int_str);
        int[]    values       = {1000,900,500,400,100,90,50,40,10,9,5,4,1};  
        String[] romanNumbers = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};  
        String rom_str = "";

        for(int i=0;i<values.length;i++){
            while (int_number >= values[i]) {
                int_number = int_number - values[i];
                rom_str = rom_str + romanNumbers[i];

            }
        }
          
        return rom_str;
        
    }

}



