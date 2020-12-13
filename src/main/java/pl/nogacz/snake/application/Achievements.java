package pl.nogacz.snake.application;


import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
public class Achievements {

    private int[] achieved;
   private double[] progress;
    Design design;

    public Achievements(Design design){
        this.design=design;
        achieved=new int[4];
        progress=new double[4];

        try{
            String path="src/main/AchievementData/Achievements.txt";
            File achievements = new File(path);
            Scanner sc =new Scanner(achievements);

            String pathB="src/main/AchievementData/Counter.txt";
            File counter = new File(pathB);
            Scanner scnr =new Scanner(counter);



            for(int count=0;count<4;count++){
                int done=Integer.parseInt(sc.nextLine());
                achieved[count]=done;
                double state=Double.parseDouble(scnr.nextLine());
                progress[count]=state;


            }

        }catch (Exception e){e.printStackTrace();}



    }
    public void AchievementCheck(){
        boolean Change=false;
        
        if(progress[0]==(achieved[0]+1)*50){
            Change=true;
            achieved[0]++;
            printDialog(0);
        }
        if(progress[1]==(achieved[1]+1)*100){
            Change=true;
            achieved[1]++;
            printDialog(1);
        }
        if(progress[2]>=(achieved[2]+1)*60){
            Change=true;
            achieved[2]++;
            printDialog(2);
        }
        if(progress[3]>=(achieved[3]+1)*5){
            Change=true;
            achieved[3]++;
            printDialog(3);
        }

        if(Change) {

            updateAchievements();


        }

    }

    public void updateAchievements(){
        String achievements=achieved[0]+"\n"+achieved[1]+"\n"+achieved[2]+"\n"+achieved[3]+"\n";
        System.out.println(achievements);
        try{
            File table=new File("src/main/AchievementData/Achievements.txt");
            FileWriter fw=new FileWriter(table);
            fw.write(achievements);
            fw.close();
        }catch(Exception e){e.printStackTrace();}

    }
    public void updateProgress(){
        String progs=progress[0]+"\n"+progress[1]+"\n"+progress[2]+"\n"+0+"\n";
        System.out.println(progs);
        try{
            File table=new File("src/main/AchievementData/Counter.txt");
            FileWriter fw=new FileWriter(table);
            fw.write(progs);
            fw.close();
        }catch(Exception e){e.printStackTrace();}
    }


    public void printDialog(int Select) {

        String message="";
        switch(Select) {
            case 0:
                message="ACHIEVEMENT UNLOCKED: EAT " + achieved[Select] * 50 + " APPLES.";
                break;
            case 1:
                message="ACHIEVEMENT UNLOCKED: MAKE " + achieved[Select] * 100 + " TURNS.";
                break;
            case 2:
                message= "ACHIEVEMENT UNLOCKED: PLAY " + achieved[Select] * 1 + " MINUTES";
                break;
            case 3:
                message="ACHIEVEMENT UNLOCKED: REACH LENGTH OF " + achieved[Select] * 5 +" IN A GAME";
                break;

        }

        try{
         //Obtain only one instance of the SystemTray object
         SystemTray tray = SystemTray.getSystemTray();

         //If the icon is a file
         Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
         //Alternative (if the icon is on the classpath):
         //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
 
         TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
         //Let the system resize the image if needed
         trayIcon.setImageAutoSize(true);
         //Set tooltip text for the tray icon
         trayIcon.setToolTip("System tray icon demo");
         tray.add(trayIcon);
 
         trayIcon.displayMessage("Congratulations!", message, MessageType.INFO);
    }catch(Exception e){e.printStackTrace();}




        
    }

    public String toString(){
        String toStr="";
        
            for(int count=0;count<4;count++){

                for(int i=1;i<=10;i++){
                        switch(count){
                            case 0:
                                if(achieved[count]<i){
                                    toStr+="--EAT "+(i*50)+" APPLES : NOT ACHIEVED\n";
                                }else{
                                    toStr+="++EAT "+(i*50)+" APPLES : IS ACHIEVED \n";
                                }
                            break;

                            case 1:
                                if(achieved[count]<i){

                                    toStr+="--MAKE "+(i*100)+" TURNS : NOT ACHIEVED\n";
                                }else{
                                    toStr+="++MAKE "+(i*100)+" TURNS : IS ACHIEVED \n";
                                }
                            break;

                            case 2:
                                if(achieved[count]<i){

                                    toStr+="--PLAY "+(i*1)+" MINUTES: NOT ACHIEVED\n";
                                }else{
                                    toStr+="++PLAY "+(i*1)+" MINUTES: IS ACHIEVED \n";
                                }
                            break;

                            case 3:
                                if(achieved[count]<i){

                                    toStr+="--REACH LENGTH OF "+(i*5)+" IN A GAME: NOT ACHIEVED\n";
                                }else{
                                    toStr+="++REACH LENGTH OF "+(i*5)+" IN A GAME: IS ACHIEVED \n";
                                }
                            break;

                        }

                    
                }
            }



        return toStr;
    }



    public void resetAchievements(){
        for(int count=0;count<4;count++){
            achieved[count]=0;
            progress[count]=0;
        }
        updateAchievements();
        updateProgress();
    }

    public void addApple(){
        progress[0]++;
    }
    public void addTurn(){
        progress[1]++;
    }
    public void addTime(){
        progress[2]+=0.14;
    }
    public void addLength(){
        progress[3]++;
    }

    public String AchievementName(int i,int x){
        return "";
    }



}