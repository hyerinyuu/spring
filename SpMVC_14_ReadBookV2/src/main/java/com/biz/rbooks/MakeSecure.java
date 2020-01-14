package com.biz.rbooks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

//oracle userID와 password를 암호화 시키려고 만든 class
public class MakeSecure {

	private final static String saltPass = "YOUR SALT";
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		// 운영체제 환경변수 가져오기
		Map<String,String> envList = System.getenv();
				
		
		System.out.print("UserID >> ");
		String pUserId = scanner.nextLine();
		
		System.out.print("Password >> ");
		String pPassword = scanner.nextLine();
		
		// 운영체제 환경변수 중 ORACLE_PASS로 등록된 값 가져오기
		String envString = envList.get("ORACLE_PASS");
		
		// 값이 있으면 그값을 쓰고 없으면 saltPass사용
		if(envString == null) envString = saltPass;
		
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envString);
		
		// 평문을 비밀번호 암호화 시키기
		String encUserId = pbEnc.encrypt(pUserId);
		String encPassword = pbEnc.encrypt(pPassword);
		
		System.out.printf("userid : %s, %s\n" , pUserId, encUserId);
		System.out.printf("password : %s, %s\n", pPassword, encPassword);
		
		String strUserId = String.format("oracle.username=ENC(%s)", encUserId);
		String strPassword = String.format("oracle.password=ENC(%s)", encPassword);
		
		String proFileName = "./src/main/webapp/WEB-INF/spring/oracle.sec.properties";
		
		try {
			PrintWriter out = new PrintWriter(proFileName);
			out.println(strUserId);
			out.println(strPassword);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.close();
		
	}
	
	
}
