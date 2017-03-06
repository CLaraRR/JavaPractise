package caesar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 多替代加密解密算法（中英文适用，可分开可混合）
 * 对明文的每一位随机赋予密钥（0-9）
 * @author 宁润
 *
 */
public class MutipleReplacement {
	public static void main(String[] args) throws IOException {
		int i,len;
		String str,miwen,mingwen;
		String go;
		System.out.println("一次一密加密、解密算法示例！");
		Scanner input = new Scanner(System.in);
		MutipleReplacement test=new MutipleReplacement();
		do{
			System.out.print("请输入明文：");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str=br.readLine();
//			str=strtemp.toCharArray();
			len = str.length();
			System.out.print("明文为：");
			for(i=0;i<len;i++){
				System.out.print(str.charAt(i));
			}
			System.out.println();
			
			int[] key=new int[len];
			key=test.getKey(len);
			
			System.out.println();
			miwen=test.encryption(str,key);
			System.out.print("加密为：");
			
			System.out.print(miwen);
			System.out.println();
			mingwen=test.decryption(miwen,key);
			System.out.print("解密为：");
			
			System.out.print(mingwen);
			System.out.println();
			System.out.print("是否继续(y/n):");
			go=input.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("程序结束！");
	}
	
	/**
	 * 密钥产生函数
	 * @param strlen 明文的长度
	 * @return 密钥（随机产生每一个字符的密钥）
	 */
	
	private int[] getKey(int strlen) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int[] key=new int[strlen];
		System.out.print("此次加密解密的密钥为：");
		for(int i=0;i<strlen;i++){
			key[i]=r.nextInt(10); //设置随机数范围为0-9
			System.out.print(key[i]+" ");
		}
		return key;
	}


	/**
	 * 解密函数
	 * @param miwen 密文
	 * @param key2 密钥
	 * @return 解密得到的明文
	 */
	public  String decryption(String miwen, int[] key) {
		// TODO Auto-generated method stub
		int len,i;
		StringBuilder wen=new StringBuilder();
        len = miwen.length();
        for(i=0;i<len;i++){
        	int chr1=(char)miwen.charAt(i);
        	if(chr1>=19968&&chr1<=171941){//如果是汉字
        		chr1-=key[i];
        		String hexLastUnicode = Integer.toHexString(chr1);
        		String ss = StringEscapeUtils.unescapeJava("\\u" +hexLastUnicode);
        		wen.append(ss);
        	}else{//如果是字母或普通字符
        		char temp=(char)(miwen.charAt(i)-key[i]);
                wen.append(temp);
        	}
        	
        }
        return wen.toString();
	}
	
	/**
	 * 加密函数
	 * @param str 明文
	 * @param key2 随机得到的密钥（每一个字符对应一个密钥）
	 * @return 加密得到的密文
	 */
	public  String encryption(String str, int[] key) {
		// TODO Auto-generated method stub
		int len,i;
        StringBuilder wen=new StringBuilder();
        len = str.length();
        for(i=0;i<len;i++){
        	int chr1=(char)str.charAt(i);
        	if(chr1>=19968&&chr1<=171941){//如果是汉字
        		chr1+=key[i];
        		String hexLastUnicode = Integer.toHexString(chr1);
        		String ss = StringEscapeUtils.unescapeJava("\\u" + hexLastUnicode);
        		wen.append(ss);
        	}else{//如果是字母或普通字符
        		char temp=(char)(str.charAt(i)+key[i]);
                wen.append(temp);
        	}
        	
        }
        return wen.toString();
	}
}
