/*************************************************************************                      
 *  Compilation:  javac PrimeSieve.java
 *  Execution:    java PrimeSieve N                                                             
 *  Purpose:      Computes the prime numbers <= N                                               
 *  Approach:     The Sieve of Eratosthenes
 *  The task asked to modify this program so it kept track of only odd numbers
 *  but this left out 2, which is the only prime even number. I added a feature to 
 *  still include two for any input greater than 1 and then search through only
 *  odd numbers to bring down the time complexity significantly.
 *************************************************************************/
 
public class PrimeBrute {
    private static int tripCount; // our old instrument
    public static void markMultiples(int start, boolean[] isPrime) {
        int j = start * 2; // starting point for marking                                        
        int N = isPrime.length - 1;
        while (j <= N) {
            tripCount++;
            isPrime[j] = false;
            j = j + start;
        }
    }
    public static void main(String[] args) {
		if (args.length != 1) {
            System.out.println("Usage: java PrimeBrute N");
            System.exit(0);
		}
		int N = 0;
		try{
			N = Integer.parseInt(args[0]);
		} catch(NumberFormatException e){
			System.out.println("Sorry you did not enter a number");
			System.exit(0);
		}
		if(N < 0){
			System.out.println("You cannot use negative numbers");
			System.exit(1);
		}
        // initially assume all integers are prime                                              
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 3; i <= N; i++)
            isPrime[i] = true;
		int primes = 0;
        tripCount = 0;
        // mark non-primes <= N using Sieve of Eratosthenes 
		if(N > 1){
			System.out.print(2 + " ");
			tripCount++;
			primes++;
		}
        for (int i = 3; i*i <= N; i += 2)
            // if i is prime, then mark multiples of i as nonprime                              
            if (isPrime[i])
                markMultiples(i, isPrime);
 
        // count and display primes                                                             
        for (int i = 3; i <= N; i += 2) {
            tripCount++;
            if (isPrime[i]) {
                primes++;
                if (args.length == 2 && args[1].equals("-s")) ; // do nothing
                else
                    System.out.print(i + " ");
            }
        }
        System.out.println("\nNumber of times in the inner loop: " + tripCount);
        System.out.println("The number of primes <= " + N + " is " + primes);
    }
}

