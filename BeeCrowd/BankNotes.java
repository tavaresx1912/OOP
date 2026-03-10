import java.io.IOException;
import java.util.Scanner;
 
/**
 * IMPORTANT: 
 *      O nome da classe deve ser "Main" para que a sua solução execute
 *      Class name must be "Main" for your solution to execute
 *      El nombre de la clase debe ser "Main" para que su solución ejecutar
 */
public class BankNotes {
 
    public static void main(String[] args) throws IOException {
 
        /**
         * Escreva a sua solução aqui
         * Code your solution here
         * Escriba su solución aquí
         */
        Scanner NewScanner = new Scanner(System.in);
        int A = NewScanner.nextInt();

        System.out.println(A);

        int notas100 = A / 100;
        A = A % 100;
        int notas50 = A / 50;
        A = A % 50;
        int notas20 = A / 20;
        A = A % 20;
        int notas10 = A / 10;
        A = A % 10;
        int notas5 = A / 5;
        A = A % 5;
        int notas2 = A / 2;
        A = A % 2;
        int notas1 = A / 1;

        System.out.println(notas100 + " nota(s) de R$ 100,00");
        System.out.println(notas50 + " nota(s) de R$ 50,00");
        System.out.println(notas20 + " nota(s) de R$ 20,00");
        System.out.println(notas10 + " nota(s) de R$ 10,00");
        System.out.println(notas5 + " nota(s) de R$ 5,00");
        System.out.println(notas2 + " nota(s) de R$ 2,00");
        System.out.println(notas1 + " nota(s) de R$ 1,00");
 
    }
 
}