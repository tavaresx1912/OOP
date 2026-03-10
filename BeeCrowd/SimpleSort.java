import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
 
/**
 * IMPORTANT: 
 *      O nome da classe deve ser "Main" para que a sua solução execute
 *      Class name must be "Main" for your solution to execute
 *      El nombre de la clase debe ser "Main" para que su solución ejecutar
 */
public class SimpleSort {
 
    public static void main(String[] args) throws IOException {
 
        /**
         * Escreva a sua solução aqui
         * Code your solution here
         * Escriba su solución aquí
         */
        Scanner sc = new Scanner(System.in);
        int array[] = new int[3];
        int array2[] = new int[3];

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        array[0] = A;
        array[1] = B;
        array[2] = C;
        array2[0] = A;
        array2[1] = B;
        array2[2] = C;

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;                
                }   
            }
        }


        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println(array[2]);

        System.out.println();

        System.out.println(array2[0]);
        System.out.println(array2[1]);
        System.out.println(array2[2]);

 
    }
 
}