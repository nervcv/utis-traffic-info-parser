package com.nervcv.utis_traffic_info_parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class InciInfoFileParse {

	private static String path() {
		File file = new File("src/file/parse/inci");
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
			File file = new File(filePath + "/INCI_INFO");

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252"));
			fis = new FileInputStream(file);
			int size = (int) file.length();
			System.out.println(">> INCI_INFO File 사이즈 : " + size);
			byte b[] = new byte[size];

			for (int i = 0; i < b.length; i++) {
				b[i] = (byte) reader.read();
			}

			int inci_count = byteToint(b);

			System.out.println(">> 돌발 전체 개수 : " + inci_count);
			System.out.println("=============================");

			System.out.print("01. 돌발ID : ");
			for (int i = 4; i < 16; i++) {
				System.out.print((char) (b[i]));
			}

			System.out.println();
			System.out.print("02. 발생시간 : ");
			for (int i = 16; i < 30; i++) {
				System.out.print((char) (b[i]));
			}

			System.out.println();
			System.out.print("03. 종료예상시간 : ");
			for (int i = 30; i < 44; i++) {
				System.out.print((char) (b[i]));
			}

			System.out.println();
			System.out.print("04. LinkID : ");

			// System.out.println((b[44] & 0xff)<<24 | (b[45] & 0xff)<<16 | (b[46] &
			// 0xff)<<8 | (b[47] & 0xff));

			for (int i = 44; i < 48; i++) {
				System.out.print((b[i]));
			}
			System.out.println();
			System.out.print("05. 도로등급 : ");

			for (int i = 48; i < 49; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("06. 돌발상황코드 : ");

			for (int i = 49; i < 51; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("07. 돌발제목 : ");

			byte[] byteString1 = new byte[512];

			for (int i = 0; i < byteString1.length; i++) {
				byteString1[i] = b[51 + i];
			}
			;

			System.out.println(new String(byteString1, "euc-kr"));

			System.out.print("08. 돌발내용 : ");

			byte[] byteString2 = new byte[512];

			for (int i = 0; i < byteString2.length; i++) {
				byteString2[i] = b[563 + i];
			}
			;

			System.out.println(new String(byteString2, "euc-kr"));

			System.out.print("09. X 좌표 : ");

			System.out.println((double) ((b[1075] & 0xff) << 24 | (b[1076] & 0xff) << 16 | (b[1077] & 0xff) << 8
					| (b[1078] & 0xff)) / 10000000);

			System.out.print("10. Y 좌표 : ");

			System.out.println((double) ((b[1079] & 0xff) << 24 | (b[1080] & 0xff) << 16 | (b[1081] & 0xff) << 8
					| (b[1082] & 0xff)) / 10000000);

			System.out.print("11. 도로명 : ");

			byte[] byteString3 = new byte[100];

			for (int i = 0; i < byteString3.length; i++) {
				byteString3[i] = b[1083 + i];
			}
			;

			System.out.println(new String(byteString3, "euc-kr"));

			System.out.println("=============================");

			System.out.print("01. 돌발ID : ");
			for (int i = 1183; i < 1195; i++) {
				System.out.print((char) (b[i]));
			}
			System.out.println();
			System.out.print("02. 발생시간 : ");
			for (int i = 1195; i < 1209; i++) {
				System.out.print((char) (b[i]));
			}

			System.out.println();
			System.out.print("03. 종료예상시간 : ");
			for (int i = 1209; i < 1223; i++) {
				System.out.print((char) (b[i]));
			}

			System.out.println();
			System.out.print("04. LinkID : ");

			// System.out.println((b[1223] & 0xff)<<24 | (b[1224] & 0xff)<<16 | (b[1225] &
			// 0xff)<<8 | (b[1226] & 0xff));

			for (int i = 1223; i < 1227; i++) {
				System.out.print((b[i]));
			}
			System.out.println();

			System.out.print("05. 도로등급 : ");

			for (int i = 1227; i < 1228; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("06. 돌발상황코드 : ");

			for (int i = 1228; i < 1230; i++) {
				System.out.print((b[i]));
			}

			System.out.println();
			System.out.print("07. 돌발제목 : ");

			byte[] byteString4 = new byte[512];

			for (int i = 0; i < byteString4.length; i++) {
				byteString4[i] = b[1230 + i];
			}
			;

			System.out.println(new String(byteString4, "euc-kr"));

			System.out.print("08. 돌발내용 : ");

			byte[] byteString5 = new byte[512];

			for (int i = 0; i < byteString5.length; i++) {
				byteString5[i] = b[1742 + i];
			}
			;

			System.out.println(new String(byteString5, "euc-kr"));

			System.out.print("09. X 좌표 : ");

			System.out.println((double) ((b[2254] & 0xff) << 24 | (b[2255] & 0xff) << 16 | (b[2256] & 0xff) << 8
					| (b[2257] & 0xff)) / 10000000);

			System.out.print("10. Y 좌표 : ");

			System.out.println((double) ((b[2258] & 0xff) << 24 | (b[2259] & 0xff) << 16 | (b[2260] & 0xff) << 8
					| (b[2261] & 0xff)) / 10000000);

			System.out.print("11. 도로명 : ");

			byte[] byteString6 = new byte[100];

			for (int i = 0; i < byteString6.length; i++) {
				byteString6[i] = b[2262 + i];
			}
			;

			System.out.println(new String(byteString6, "euc-kr"));

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
