import java.io.IOException;
import java.util.Scanner;
 
/**
 * IMPORTANT: 
 *      O nome da classe deve ser "Main" para que a sua solução execute
 *      Class name must be "Main" for your solution to execute
 *      El nombre de la clase debe ser "Main" para que su solución ejecutar
 */
public class TimeConversion {
 
    public static void main(String[] args) throws IOException {
 
        /**
         * Escreva a sua solução aqui
         * Code your solution here
         * Escriba su solución aquí
         */

        Scanner NewScanner = new Scanner(System.in);
        int A = NewScanner.nextInt();

        int Hours = A / 3600;
        A = A % 3600;
        int Minutes = A / 60;
        A = A % 60;
        int Seconds = A / 1;
        System.out.println(Hours + ":" + Minutes + ":" + Seconds);

 
    }
 
}