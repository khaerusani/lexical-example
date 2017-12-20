import java.util.*;
import java.io.*;
class lexanly{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama File source :");
        String filename=sc.nextLine();
        String line=null;
        String function_table="tpdt.txt";
        String linef=null;
        String keyword_table="ky.txt";
        String linek=null;
        char s[]=new char[500]; 
        int i,j,k,found,id=0,match=0;
        int var[]=new int[4];
        String idf[]=new String[50]; 
        try{
            FileReader fr=new FileReader(filename);
            BufferedReader br=new BufferedReader(fr);
            File file = new File("output.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            while((line=br.readLine())!=null){
                s=line.toCharArray();
                for(i=0,j=0;i<line.length();i++){
                    found=0;
                    var=spopsy(s[i]);
                    if(var[0]!=0 || s[i]==' '){
                        FileReader frf=new FileReader(function_table);
                        BufferedReader brf=new BufferedReader(frf);
                        FileReader frk=new FileReader(keyword_table);
                        BufferedReader brk=new BufferedReader(frk);
                        for(k=0;(linef=brf.readLine())!=null && found==0;k++) 
                            if(linef.equals(line.substring(j,i))){
                                bw.write("TipeData#"+(k+1)+" ");
                                found=1;
                            }
                            for(k=0;(linek=brk.readLine())!=null && found==0;k++)
                            if(linek.equals(line.substring(j,i))){
                                bw.write("Keyword#"+(k+1)+" ");
                                found=1;
                            }
                            if(found==0 && id==0 && s[i]!=' ') {
                                idf[0]=line.substring(j,i);
                                id++;
                            }
                            for(k=0;k<id && found==0 && s[i]!=' ';k++){                          
                                if(idf[k].equals(line.substring(j,i))){
                                    match=1;
                                    break;
                                }
                                else
                                    match=0;
                            }
                            if(match==0 && found==0 && s[i]!=' ' && j!=i){
                                idf[id]=line.substring(j,i);
                                id++;
                            }
                            if(found==0 && s[i]!=' ' && j!=i){
                                bw.write("Identifier#"+(k+1)+" "); 
                                found=1;
                            } 
                            if(var[0]==1)
                                bw.write("Symbol#"+var[1]+" ");
                            if(var[0]==2)
                                bw.write("Operator#"+var[1]+" ");
                            j=i+1; //function,keyword or identifier
                            brf.close();
                            brk.close();
                        }
                    }
                    bw.newLine();
                }
                br.close();
                bw.close(); 
            }
            catch(FileNotFoundException ex){
                System.out.println("File tidak Ditemukan ");
            }
            catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("Identifier Terdeteksi");
            for(k=0;k<id;k++)
                System.out.println(idf[k]);
                System.out.println("Hasil Scanning berada di output.txt");
            }
    static int[] spopsy(char c){
        String sp_symbol_table="spsy.txt";
        String linessy=null;
        String operator_table="op.txt";
        String lineo=null;
        int found=0,i,k;
        int var[]=new int[2];
        try{
            FileReader frs=new FileReader(sp_symbol_table);
            BufferedReader brs=new BufferedReader(frs);
            FileReader fro=new FileReader(operator_table);
            BufferedReader bro=new BufferedReader(fro);
            for(k=0;(linessy=brs.readLine())!=null && found==0;k++){
                if(linessy.equals(Character.toString(c))){
                    var[0]=1;
                    var[1]=k+1;
                    found=1;
                }
            }
            brs.close();
            for(k=0;(lineo=bro.readLine())!=null && found==0;k++)
                if(lineo.equals(Character.toString(c))){
                    var[0]=2;
                    var[1]=k+1;
                    found=1;
                }
                bro.close();
            }
            catch(FileNotFoundException ex){
                System.out.println("File Tidak Ditemukan ");
            }
            catch(IOException ex){
                System.out.println("Error membaca File ");
            }
            return var;
        }
}