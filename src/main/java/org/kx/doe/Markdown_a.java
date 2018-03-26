package org.kx.doe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * create by sunkx on 2018/3/6
 */
public class Markdown_a {


    public static void main(String[] args) throws Exception {
        deleteNULLLine("   jjj");
        //System.out.println(Integer.parseInt("00"));
    }


    //<body><h2>系统安全</h2>  <h2 id="文件压缩与解压"><a href="javascript:void(0)"  class="anchor">文件压缩与解压</a></h2>
    //<h3>syslog命令</h3>

    public static void deleteNULLLine(String name) throws  Exception {
        File f = new File("/Users/sunkaixiang/IdeaProjects/antd-demor/"+name.trim()+".html");
        BufferedReader br = new BufferedReader(new FileReader(f));//构造一个BufferedReader类来读取文件
        boolean start = false ;
        String s = null;
        StringBuilder sb =new StringBuilder();

        StringBuilder sb2 =new StringBuilder();

        boolean code = false;
        //int x =1;
        while((s = br.readLine())!=null ){//使用readLine方法，一次读一行
            String s1 = s;//s.trim();
            if(s1.isEmpty()) continue;
            if(s1.contains("{"))s1=  s1.replace("{","{this.s}");
            String h =null;
            String c =null;
            Integer l = 0;
            Integer deafultl = 2;
            if(s1.startsWith("<body>")){
                start = true;
                s1 = s1.substring(6);

            }
            if(s1.startsWith("</body>")  || s1.startsWith("</html>")){
                continue;
            }
            if(!start) continue;
            int index = s1.indexOf("<h");
            if(index>=0){
              char x = s1.charAt(index+2);
                if(Character.isDigit(x)){
                    l = Integer.parseInt(""+x);
                    int index2 = s1.indexOf("</h");
                    c = s1.substring(index+4,index2) ;
                    //<h2 id="文件压缩与解压"><a href="javascript:void(0)"  class="anchor">文件压缩与解压</a></h2>
                    //  h="<h+"+l +" " + "id=\""+c+ "\"" +"><a href=\"javascript:void(0)\" class=\"anchor\">"+c+"</a></h2>";
                    sb.append("<h").append(l).append(" ").append("id=\"");
                    sb.append(c).append("\"><a href=\"javascript:void(0)\" class=\"anchor\">").append(c);
                    sb.append("</a></h").append(l).append(">\n") ;
                }else {
                    sb.append(s1).append("\n");
                }

            }else{
                if(s1.startsWith("</code></pre>")){
                    c= s1;
                    code = false;
                }


                if(s1.contains("src/imgs")  ){
                    int starte = s1.indexOf("img src=") ;
                    int ends = s1.indexOf("/>");  //src={require('../../img/20161013182456810.png')}
                    int end2 = ends ;

                    if(s1.contains("alt")){
                        end2 = s1.indexOf("alt");
                    }

                    String to = s1.substring(s1.indexOf("src/imgs")+8,end2);
                    //String TARGET = "{require('../../../imgs"+to+")}" ;
                    String TARGET = "{require('../../imgs"+to+")}" ;
                    s1 = s1.substring(0,starte+8)+TARGET+s1.substring(ends);

                }


                if(code){
                  //  s1.replace(" ","&nbsp;")；
                    c=  s1.replace(" ","&nbsp;") +"<br/>" ;
                }

                if(s1.startsWith("<pre><code>")){
                    c = s1+"<br/>" ;
                    code = true ;
                }
                if(c ==null ) c=s1;
                sb.append(c).append("\n");
            }
            /*if(l == deafultl){
                if(sb2.length() >0){
                    sb2.append("    </Link>\n");
                }
                sb2.append("<Link href=\"#"+c+"\" title=\""+c+"\">\n");
            }else if(l>deafultl){
                deafultl = l;
                sb2.append("    <Link href=\"#"+c+"\" title=\""+c+"\">\n");
            }else {
                deafultl = l;
                sb2.append("    </Link>\n");
                sb2.append("</Link>\n");
            }*/
            if(l>0) {
                if (l == 2) {
                    sb2.append("<Link href=\"#" + c + "\" title=\"" + c + "\"/>\n");
                }else if (l == 3){
                    sb2.append("-<Link href=\"#" + c + "\" title=\"" + c + "\"/>\n");
                }else   if(l ==  4){
                    sb2.append("--<Link href=\"#" + c + "\" title=\"" + c + "\"/>\n");
                }else   if(l ==  5){
                    sb2.append("---<Link href=\"#" + c + "\" title=\"" + c + "\"/>\n");

                }else {
                    sb2.append(l+"----<Link href=\"#"+c+"\" title=\""+c+"\"/>\n");
                }

            }



        }
        br.close();
        String word= sb.toString();
        System.out.println(word);
        System.out.println("--------------");
        System.out.println(sb2.toString());
    }






}




