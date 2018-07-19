package com.nervcv.utis_traffic_info_parser.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileParsingHelper<K> {
	public File file;

	public FileParsingHelper() {
	}

	public File relativePathCheck_and_createFileObject(String srcFilePath, boolean isRelativePath) throws Exception {
		if (isRelativePath) {
			this.file = new File(srcFilePath);
			this.file = new File(this.file.getAbsoluteFile().toString());
		} else {
			this.file = new File(srcFilePath);
		}
		return file;
	}

	public void fileVerification(File file, int initValue, int n) throws Exception {
		int fileSize = (int) file.length();

		if (fileSize == 0) {
			throw new Exception("존재하지 않는 파일이거나 파일의 크기가 0 입니다.");
		}

		if (calculatorFileSize(initValue, n, fileSize) == false) {
			throw new Exception("해당 파일의 파싱규칙과 일치하지 않습니다. 확인바랍니다.");
		}
	}

	public void convertToTextFile(ArrayList<K> parseDataList, String descFilePath) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(descFilePath), "UTF8"));

			for (int i = 0; i < parseDataList.size(); i++) {
				bw.write(((K) parseDataList.get(i)).toString());
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	private boolean calculatorFileSize(int initValue, int n, int fileSize) {
		return (fileSize - initValue) % n == 0;
	}

}