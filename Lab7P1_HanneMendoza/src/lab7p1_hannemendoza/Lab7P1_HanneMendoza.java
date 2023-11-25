/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

//Fila 02 Asiento 04
package lab7p1_hannemendoza;
import java.util.Scanner;
import java.util.Random;
public class Lab7P1_HanneMendoza {
    
static Scanner kore = new Scanner(System.in);
static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("****MENU****");
        System.out.println("1. Tres en Raya");
        System.out.println("2. Puntos de silla");       
        System.out.println("Ingrese su opción: ");
        int opcion = kore.nextInt();
        char res  = 's';
        
        while(opcion > 0 && opcion <3){
            switch(opcion){
                
                case 1:
                    System.out.println("-------Bienvenido al tres en raya-------");
                    System.out.println("Tablero actual");
                    char fila = 3;
                    char columna = 3;
                    char [][] matriz = new char [fila][columna];
                    while(res == 's' || res== 'S'){                        
                    
                        char J1= 'X';
                        char J2= '0';
                        char vacio = '-';
                        boolean turno = true;                        
                       
                        char tablero [][] = new char [3][3];
                        rellenarTablero(tablero, vacio);
                        boolean posvalida, corecto; 
                        
                        while(!verificarVictoria(tablero, vacio)){
                            TurnoJugador(turno);
                            generarTablero(tablero);


                            System.out.print("Ingrese la fila(0,1,2): ");
                            int fil = kore.nextInt();
                            System.out.print("Ingrese la columna (0,1,2): ");
                            int col = kore.nextInt();
                            System.out.print("El usuario ha elegido la posición: " + "(" + fil + ", " + col + ")");
                            
                            posvalida = verificarPosicionValida(tablero, fila, columna);
                            if(posvalida){
                               if(seEncuentra(tablero, fila, columna, vacio)){
                                   corecto  = true;
                               }else{
                                   System.out.println("POsición no válida o ya ocupada. Inténtelo de nuevo.");
                               }
                            }
                            
                            

                            if(turno){
                               insertar(tablero,fila, columna, J1);

                            }else{
                                insertar(tablero,fila, columna, J2);
                            }


                            turno =! turno;

                            generarTablero(tablero);
                            mostrarGanador(tablero, J1, J2, vacio);

                        }  
                        
                        System.out.println("\nQuieres jugar otra vez? (s/n): ");
                        res = kore.next().charAt(0);
                               
                    }// while interno
                    
                    
                    break;
                
                case 2:
                    System.out.println("Ingrese el número de filas: ");
                    int fil = kore.nextInt();
                    System.out.println("Ingrese el número de columnas: ");
                    int colum = kore.nextInt();                      
                    
                    System.out.println("Matriz generada: ");
                    int [][] matriz2 = generarMatrizAleatoria(fil,colum);
                    imprimirMatrices(matriz2);
                    System.out.println("");
                    System.out.print("Punto de Silla en Matriz");                 
                                      
                                      
                    
                    break;
                
                
            }// switch
            
        System.out.println("\n****MENU****");
        System.out.println("1. Tres en Raya");
        System.out.println("2. Puntos de silla");       
        System.out.println("Ingrese su opción: ");
        opcion = kore.nextInt();    
        }// while
    }// main
    
    
    public static void generarTablero(char [][] matriz){
        for(int i = 0 ; i<matriz.length; i++){
            for(int j  =0 ; j<matriz.length; j++){
                System.out.print(matriz[i][j]+ " ");
            }
            System.out.println("");
        }
    }
    
    public static void rellenarTablero(char [][] matriz ,char simbolo){
        for(int i = 0 ; i<matriz.length; i++){
            for(int j  =0 ; j<matriz.length; j++){
               matriz[i][j]= simbolo; 
            }
        }
    }       
    
    public static void TurnoJugador(boolean turno){
        if(turno){
            System.out.println("Es el turno de X");
        }else{
            System.out.println("Es el turno de 0");            
        }
    }
        
    public static boolean matrizLlena(char [][]  matriz, char signo){
        for(int i = 0 ; i< matriz.length; i++){
            for(int j  =0 ; j< matriz[0].length ; j++){
                if(matriz[i][j]== signo){
                    return false;
                }
            }
            
        }
        return true;
    }
    
    public static boolean verificarPosicionValida(char [][] tablero, int fila, int columna){
        if(fila >= 0 && fila<tablero.length && columna >=0 && columna<tablero.length){
            return true;
        }
        return false;
    }
    
    public static char coincidenciaLinea(char [][] matriz, char signo){
        char simbolo;
        boolean coincidencia;
        for(int i  =0 ; i< matriz.length ; i++){
            coincidencia  = true;
            simbolo = matriz [i][0];
            if(simbolo != signo){
               for(int j =0 ; j<matriz[0].length ; j++){
                   if(simbolo != matriz[i][j]){
                      coincidencia = false; 
                   }
            }
               if(coincidencia){
                   return simbolo;
               }
            }
            
        }
        
    return signo;
    }   
    
    public static char coincidenciaColumna(char [][] matriz, char signo){
      char simbolo;
        boolean coincidencia;
        
        for(int j = 0 ; j < matriz.length ; j++){
            coincidencia  = true;
            simbolo = matriz [0][j];
            if(simbolo != signo){
               for(int i =1 ; i<matriz[0].length ; i++){
                   if(simbolo != matriz[i][j]){
                      coincidencia = false; 
                   }
            }
               if(coincidencia){
                   return simbolo;
               }
            }
            
        }
        
    return signo;  
    }   
       
    public static char coincidenciaDiagonal(char [][] matriz, char signo){
        char simbolo;
        boolean coincidencia = true;        
        // diagonal normal
        simbolo = matriz [0][0];
        if(matriz [0][0] != signo){
          for(int i = 0 ; i < matriz.length ; i++){
              if(simbolo != matriz [i][i]){
                  coincidencia = false;
              }            
        }
         if(coincidencia){
            return simbolo; 
         } 
        }       
        // diagonal inversa
        simbolo = matriz [0][2];
        if(simbolo != signo){
            for(int i = 1 ; i< matriz.length ; i++){
                for(int j =1 ; j<matriz[i].length ; j--){
                    if(simbolo != matriz[i][j]){
                        coincidencia = false;
                    }
                }
                if(coincidencia){
                  return simbolo;  
                }
            }
        }      
               
    return simbolo;  
    }
    
    public static boolean seEncuentra(char [][] matriz, int fila, int columna, char simbolo){
       if(matriz[fila][columna] != simbolo){
           return true;
       } 
       return false;
    }
    
    public static void insertar(char [][] matriz , int fila , int columna, char simbolo){
        matriz [fila][columna]= simbolo;
    }
    
    
    public static boolean verificarVictoria(char [][] matriz, char signo){
      if(matrizLlena(matriz, signo) ||
              coincidenciaLinea(matriz, signo)!= signo ||
              coincidenciaColumna(matriz, signo)!= signo ||
              coincidenciaDiagonal(matriz, signo)!= signo){
          return true;
      }  
      return false;
    }
    
    public static void mostrarGanador(char [][] matriz ,  char J1, char J2 , char signo){
       
       char simbolo = coincidenciaLinea(matriz, signo);
       if(simbolo != signo){
           if(simbolo == J1){
               System.out.println("Ha ganado la X");
           }else{
               System.out.println("Ha ganado la 0");
           }
           return;
       }
       
       
       simbolo = coincidenciaColumna(matriz, signo);
       if(simbolo != signo){
           if(simbolo == J1){
               System.out.println("Ha ganado la X");
           }else{
               System.out.println("Ha ganado la 0");
           }
           return;
       }
              
       
       simbolo = coincidenciaDiagonal(matriz, signo);
       if(simbolo != signo){
           if(simbolo == J1){
               System.out.println("Ha ganado la X");
           }else{
               System.out.println("Ha ganado la 0");
           }
           return;
       }
       
       System.out.println("Hay empate");
   }     
    
    //case 2
    
    public static int [][] generarMatrizAleatoria(int fila, int columna){
        int temporal [][] = new int [fila][columna];
        for(int i = 0 ; i < fila ; i++){
            for(int j = 0; j < columna; j++){
               temporal[i][j] = 1 + rand.nextInt(100); 
            }
        }
        return temporal;
    }
    
    public static void imprimirMatrices(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public static int [] encontrarPuntosSilla(int matriz2 [][]){
        int [] indices = new int[matriz2.length];
        int posx = 0;
        int posy = 0;
        int posfinal = 0;
       
        for(int i = 0 ; i < matriz2.length; i++){
            for(int j = 0 ; j < matriz2.length; j++){
            if(posx < matriz2[i].length && posy > matriz2[j].length){
                indices [i] += posfinal;
            }else{
                System.out.println("");
            }    
        }     
        }
        
        
      return indices;          
    }
        
        
    
    
}//clase
