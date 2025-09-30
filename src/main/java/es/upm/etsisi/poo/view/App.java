package es.upm.etsisi.poo.view;

import java.util.Scanner;

/*
Aqui va help, exit, etc... Las fnc visuales
help (lista los comandos)
echo “<texto>” (imprime el texto en el valor texto)
exit
 */
public class App 
{
    public App(){

    }
    public static void main( String[] args )
    {
        App aplicacion=new App();
        //app.iniciar();
        aplicacion.run();

    }

    private static void run() {

        Scanner scanner=new Scanner(System.in);
        boolean bucle=true;
        while(bucle) {
            //System.out.print(TASK_APP);
            String line=scanner.nextLine();
            if (System.getenv("debugFile")!=null)
                System.out.println(line);
            String[] lineSepSpace = line.split(" ");
            switch (lineSepSpace[0]) {
                case "help":
                    //printhelp();
                    break;
                case "exit":
                    bucle=false;
                    break;
                case "prod":
                    switch (lineSepSpace[1]){
                        case "add":
                            break;
                        case "list":
                            break;
                        case "update":
                            break;
                        case "remove":
                            break;
                        default:
                            break;
                    }
                    break;
                case "ticket":
                    switch (lineSepSpace[1]){
                        case "new":
                            break;
                        case "add":
                            break;
                        case "remove":
                            break;
                        case "print":
                            break;
                        default:
                            break;
                    }
                    break;
                case "print":
                    break;
                default:
                    break;
            }

        }
    }
}
