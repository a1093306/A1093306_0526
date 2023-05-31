import java.io.*;
import java.util.*;
class Student implements Serializable{
    private String name;
    private int EScore;
    private int MScore;
    private int JScore;
    public Student(String name,int EScore,int MScore,int JScore){
        this.name=name;
        this.EScore=EScore;
        this.MScore=MScore;
        this.JScore=JScore;
    }
    public Student(){}
    public String getN(){
        return name;
    }
    public int getE(){
        return EScore;
    }
    public int getM(){
        return MScore;
    }
    public int getJ(){
        return JScore;
    }
    public double getAvg(){
        return (EScore+MScore+JScore)/3.0;
    }
}

public class A1093306_0526_1 {
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        System.out.println("請輸入要建立的學生成績檔檔名");
        System.out.print("->");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String filename=br.readLine();
        ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(filename));
        String str=new String();
        int counter=0;
        do{
            counter++;
            System.out.print("請輸入學生姓名:");
            String name=br.readLine();
            System.out.print("請輸入英文分數:");
            str=br.readLine();
            int EScore=Integer.parseInt(str);
            System.out.print("請輸入數學分數:");
            str=br.readLine();
            int MScore=Integer.parseInt(str);
            System.out.print("請輸入Java分數:");
            str=br.readLine();
            int JScore=Integer.parseInt(str);
            Student ss=new Student(name, EScore, MScore, JScore);
            os.writeObject(ss);
            System.out.print("還需要輸入另一筆資料嗎(y/n):");
            str=br.readLine();
        }while(str.equalsIgnoreCase("Y"));
        os.flush();
        os.close();
        System.out.println("\n已寫入 "+counter+" 筆學生資料至檔案 "+filename);
        System.out.println("-".repeat(50));

        int count=0;
        double Esum=0;
        double Msum=0;
        double Jsum=0;
        Student sss=new Student();
        System.out.println("\n姓名\t英文\t數學\tJava\t平均");
        System.out.println("-".repeat(50));
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            while(true){
                sss=(Student)ois.readObject();
                count++;
                Esum=Esum+sss.getE();
                Msum=Msum+sss.getM();
                Jsum=Jsum+sss.getJ();
                System.out.println(sss.getN()+'\t'+sss.getE()+'\t'+sss.getM()+'\t'+sss.getJ()+'\t'+sss.getAvg());
            }
        }catch(EOFException e){
            System.out.println("\n已從檔案 "+filename+" 讀取 "+count+" 筆學生資料");
            System.out.println("\n全員英文平均:"+(Esum/count));
            System.out.println("全員數學平均:"+(Msum/count));
            System.out.println("全員Java平均:"+(Jsum/count));
        }
    }
}
