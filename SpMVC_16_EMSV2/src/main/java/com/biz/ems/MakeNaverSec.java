package com.biz.ems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MakeNaverSec {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		// 운영체제에 들어있는 환경변수 가져오는 method
		Map<String, String> envList = System.getenv();
		
		// 환경변수에 세팅한 NAVER path
		String saltPass = envList.get("NAVER");
		
		System.out.println("Salt Pass : " + saltPass);
		
		// Naver setting
		System.out.print("Naver ID >> " );
		String naverId = scanner.nextLine();
		System.out.print("Naver Password >> ");
		String naverPass = scanner.nextLine();
	
		
		// DB Setting
		System.out.print("DB User >> " );
		String dbUserId = scanner.nextLine();
		System.out.print("DB Password >> " );
		String dbPassword = scanner.nextLine();
		
		
		
		
		// 암호화 설정 부분
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(saltPass);
		
		// Naver Setting
		String encNaverId = pbEnc.encrypt(naverId);
		String encNaverPass = pbEnc.encrypt(naverPass);
		
		// DB Setting
		String encDBUserId = pbEnc.encrypt(dbUserId);
		String encDBPassword = pbEnc.encrypt(dbPassword);
		
		System.out.printf("Naver : %s, %s\n", naverId, naverPass);
		System.out.printf("EncNaver : %s, %s\n", encNaverId, encNaverPass);

		// Naver
		String saveNaverId = String.format("naver.username=ENC(%s)", encNaverId);
		String saveNaverPass = String.format("naver.password=ENC(%s)", encNaverPass);
		
		// DB
		String saveDBUserID = String.format("mysql.user=ENC(%s)", encDBUserId);
		String saveDBPassword = String.format("mysql.password=ENC(%s)", encDBPassword);
		
		String proFileName = "./src/main/webapp/WEB-INF/spring/naver.email.secret.properties";
		
		
		String WEB_INF = "./src/main/webapp/WEB-INF/spring";
		String naver_pro = "naver.email.secret.properties";
		
		// 변수 a : 경로
		// 변수 b : 파일 이름
		// File => 실행되는 운영체제마다 경로를 맞춰서 변경해주는 역할을 함
		File proFile = new File(WEB_INF, naver_pro);		
		
		try {
			// PrintWriter out = new PrintWriter(proFileName);
			PrintWriter out = new PrintWriter(proFile);
			
			// Naver
			out.println(saveNaverId);
			out.println(saveNaverPass);
			
			// DB 
			out.println(saveDBUserID);
			out.print(saveDBPassword);
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.close();
		
	}
	
	
}

