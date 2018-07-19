package com.nervcv.utis_traffic_info_parser;

public class BitArrayTest {

	public static void main(String[] args) {
		int remainder = 320;
		int[] binaryRepresentation = new int[16];

		for (int i = 0; i < 16; i++) {
			binaryRepresentation[i] = remainder % 2;
			remainder = remainder / 2;
		}

		for (int i = 0; i < binaryRepresentation.length; i++) {
			System.out.println(binaryRepresentation[i]);
		}
	}

}
