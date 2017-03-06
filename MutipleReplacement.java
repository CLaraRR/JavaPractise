package caesar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * ��������ܽ����㷨����Ӣ�����ã��ɷֿ��ɻ�ϣ�
 * �����ĵ�ÿһλ���������Կ��0-9��
 * @author ����
 *
 */
public class MutipleReplacement {
	public static void main(String[] args) throws IOException {
		int i,len;
		String str,miwen,mingwen;
		String go;
		System.out.println("һ��һ�ܼ��ܡ������㷨ʾ����");
		Scanner input = new Scanner(System.in);
		MutipleReplacement test=new MutipleReplacement();
		do{
			System.out.print("���������ģ�");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str=br.readLine();
//			str=strtemp.toCharArray();
			len = str.length();
			System.out.print("����Ϊ��");
			for(i=0;i<len;i++){
				System.out.print(str.charAt(i));
			}
			System.out.println();
			
			int[] key=new int[len];
			key=test.getKey(len);
			
			System.out.println();
			miwen=test.encryption(str,key);
			System.out.print("����Ϊ��");
			
			System.out.print(miwen);
			System.out.println();
			mingwen=test.decryption(miwen,key);
			System.out.print("����Ϊ��");
			
			System.out.print(mingwen);
			System.out.println();
			System.out.print("�Ƿ����(y/n):");
			go=input.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("���������");
	}
	
	/**
	 * ��Կ��������
	 * @param strlen ���ĵĳ���
	 * @return ��Կ���������ÿһ���ַ�����Կ��
	 */
	
	private int[] getKey(int strlen) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int[] key=new int[strlen];
		System.out.print("�˴μ��ܽ��ܵ���ԿΪ��");
		for(int i=0;i<strlen;i++){
			key[i]=r.nextInt(10); //�����������ΧΪ0-9
			System.out.print(key[i]+" ");
		}
		return key;
	}


	/**
	 * ���ܺ���
	 * @param miwen ����
	 * @param key2 ��Կ
	 * @return ���ܵõ�������
	 */
	public  String decryption(String miwen, int[] key) {
		// TODO Auto-generated method stub
		int len,i;
		StringBuilder wen=new StringBuilder();
        len = miwen.length();
        for(i=0;i<len;i++){
        	int chr1=(char)miwen.charAt(i);
        	if(chr1>=19968&&chr1<=171941){//����Ǻ���
        		chr1-=key[i];
        		String hexLastUnicode = Integer.toHexString(chr1);
        		String ss = StringEscapeUtils.unescapeJava("\\u" +hexLastUnicode);
        		wen.append(ss);
        	}else{//�������ĸ����ͨ�ַ�
        		char temp=(char)(miwen.charAt(i)-key[i]);
                wen.append(temp);
        	}
        	
        }
        return wen.toString();
	}
	
	/**
	 * ���ܺ���
	 * @param str ����
	 * @param key2 ����õ�����Կ��ÿһ���ַ���Ӧһ����Կ��
	 * @return ���ܵõ�������
	 */
	public  String encryption(String str, int[] key) {
		// TODO Auto-generated method stub
		int len,i;
        StringBuilder wen=new StringBuilder();
        len = str.length();
        for(i=0;i<len;i++){
        	int chr1=(char)str.charAt(i);
        	if(chr1>=19968&&chr1<=171941){//����Ǻ���
        		chr1+=key[i];
        		String hexLastUnicode = Integer.toHexString(chr1);
        		String ss = StringEscapeUtils.unescapeJava("\\u" + hexLastUnicode);
        		wen.append(ss);
        	}else{//�������ĸ����ͨ�ַ�
        		char temp=(char)(str.charAt(i)+key[i]);
                wen.append(temp);
        	}
        	
        }
        return wen.toString();
	}
}
