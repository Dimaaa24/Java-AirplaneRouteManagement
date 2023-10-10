package isp.lab8.airways;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise {

    static void menu()
    {
        System.out.println("\t\t========Airplane Route Management========");
        System.out.println("1.Create new route");
        System.out.println("2.Add waypoint to the route");
        System.out.println("3.Display route+distance");
        System.out.println("4.Delete route");
        System.out.println("5.List all routes");
        System.out.println("6.EXIT");

    }

    static void folderCreate(String name)
    {
        File file=new File("routes"+File.separator+name);
        file.mkdirs();
    }

    static void fileCreate(String foldername,Waypoint x) throws IOException
    {
        ObjectMapper waypoint=new ObjectMapper();
        String nameway=x.getName();
        waypoint.writeValue(new FileWriter("routes"+File.separator+foldername+File.separator+nameway+".json"), x);
    }

    static void deleteFolder(String name)
    {
        File file=new File("routes"+File.separator+name);
        File files[]=file.listFiles();
        for(int i=0;i<files.length;i++)
            files[i].delete();
        file.delete();
    }

    static void displayRoutes()
    {
        File file=new File("routes");
        String routes[]=file.list();
        for(int i=0;i<routes.length;i++)
        {
            System.out.println(routes[i]);
        }
    }

    static void loadRoute(String name) throws IOException
    {
        ObjectMapper obj=new ObjectMapper();
        Route x=new Route(name);
        File file=new File("routes"+File.separator+name);
        String ctr[]=file.list();
        System.out.println("Route:"+name);
        for(int i=0;i<ctr.length;i++)
        {

            Waypoint y=obj.readValue(new FileReader("routes"+File.separator+name+File.separator+ctr[i]),Waypoint.class);
            System.out.println(y.toString());
            x.addWaypoint(y);
        }
        System.out.println("Total distance:"+x.routeDistance());
    }


    public static void main(String[] args)
    {
        Scanner cin=new Scanner(System.in);
        String name;
        int index,altitude;
        double latitude,longitude;
        menu();
        int opt=cin.nextInt();
        while(true)
        {
            switch (opt){
                case 1:
                    System.out.println("Input route name:");
                    name=cin.next();
                    folderCreate(name);
                    System.out.println("Route created!");
                    break;
                case 2:
                    System.out.println("Select route:");
                    String name2=cin.next();
                    try{
                    System.out.println("Input index:");
                    index=cin.nextInt();
                    System.out.println("Input name:");
                    name=cin.next();
                    System.out.println("Input lattitude:");
                    latitude=cin.nextDouble();
                    System.out.println("Input longitude:");
                    longitude=cin.nextDouble();
                    System.out.println("Input altitude:");
                    altitude=cin.nextInt();
                    Waypoint x=new Waypoint(index,name,latitude,longitude,altitude);
                        fileCreate(name2, x);
                        System.out.println("Waypoint added!");
                    }
                    catch(InputMismatchException f)
                    {
                        System.out.println("Wrong input!");
                    }
                    catch(IOException e)
                    {
                        e.getMessage();
                    }
                    break;
                case 3:
                    System.out.println("Select route to display:");
                    name=cin.next();
                    try {
                        loadRoute(name);
                    }
                    catch(IndexOutOfBoundsException y){
                        System.out.println("Can't form a route with 1 waypoint!");
                    }
                    catch(IOException e)
                    {
                        e.getMessage();
                    }
                    break;
                case 4:
                    System.out.println("Select route to delete:");
                    name=cin.next();
                    deleteFolder(name);
                    break;
                case 5:
                    displayRoutes();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
            menu();
            try {
                opt = cin.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Wrong input!");
            }

        }
    }
}
