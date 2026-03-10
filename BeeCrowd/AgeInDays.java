import java.io.IOException;
import java.util.Scanner;
 
/**
 * IMPORTANT: 
 *      O nome da classe deve ser "Main" para que a sua solução execute
 *      Class name must be "Main" for your solution to execute
 *      El nombre de la clase debe ser "Main" para que su solución ejecutar
 */
public class AgeInDays {
 
    public static void main(String[] args) throws IOException {
 
        /**
         * Escreva a sua solução aqui
         * Code your solution here
         * Escriba su solución aquí
         */
 
        Scanner NewScanner = new Scanner(System.in);
        int A = NewScanner.nextInt();

        int Years = A / 365;
        A = A % 365;
        int Months = A / 30;
        A = A % 30;
        int Days = A / 1;
        
        System.out.println(Years + " ano(s)");
        System.out.println(Months + " mes(es)");
        System.out.println(Days + " dia(s)");

    }
 
}