package com.nervcv.utis_traffic_info_parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrafiInfoFileParse {

	private static String path() {
		File file = new File("src/java_1_8/file/file/parse/traf");
		String path = file.getAbsolutePath();
		// String path = FileCopySample.class.getResource("/file").getPath(); // bin 폴더
		return path;
	}

	public static int byteToint(byte[] arr) {
		return (arr[0] & 0xff) << 24 | (arr[1] & 0xff) << 16 | (arr[2] & 0xff) << 8 | (arr[3] & 0xff);
	}

	public static void main(String[] args) {

		String filePath = path();

		FileInputStream fis = null;

		BufferedReader reader = null;
		try {
			File file = new File(filePath + "/TRAF_INFO");
			int trafFileSize = (int) file.length();
			fis = new FileInputStream(file);

			reader = new BufferedReader(new InputStreamReader(fis));
			System.out.println(">> TRAF_INFO File 사이즈 : " + trafFileSize + " byte");
			byte b[] = new byte[trafFileSize];

			for (int i = 0; i < b.length; i++) {
				b[i] = (byte) reader.read();
			}

			int inci_count = byteToint(b);

			System.out.println(">> Link 전체 개수 : " + inci_count);
			System.out.print(">> 소통정보 생성시간 : ");
			for (int i = 4; i < 18; i++) {
				System.out.print((char) (b[i]));
			}
			System.out.println();
			System.out.println("=============================");

			System.out.print("01. LinkID : ");

			for (int i = 18; i < 22; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("02. 속도 : ");

			for (int i = 22; i < 23; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("03. 도로등급 : ");

			for (int i = 23; i < 24; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("04. 혼잡도 : ");

			for (int i = 24; i < 25; i++) {
				System.out.print(b[i]);
			}

			System.out.println();
			System.out.print("05. 통과시간 : ");

			for (int i = 25; i < 27; i++) {
				System.out.print(b[i]);
			}

			System.out.println();
			System.out.print("06. 패턴여부 : ");

			for (int i = 27; i < 28; i++) {
				System.out.print(b[i]);
			}

			System.out.println();
			System.out.println("==============================");
			System.out.print("01. LinkID : ");
			for (int i = 28; i < 32; i++) {
				System.out.print((b[i]));
			}
			System.out.println();
			System.out.print("02. 속도 : ");
			for (int i = 32; i < 33; i++) {
				System.out.print((b[i]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
