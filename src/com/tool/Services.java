/**
 * @explain	
 * @author Lambert.Shi
 * @date 2020年4月9日 上午11:45:16
 */
package com.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * @explain	实现
 * @author Lambert.Shi
 * @date 2020年4月9日 上午11:45:16
 */
public class Services {

	/**
	 * 开始执行
	 * @param startPaths	需要转变编码的文件路径集
	 * @param endPath		保存路径
	 * @param startEncoding	开始字符
	 * @param endEncoding	保存字符
	 */
	public static Integer execute(List<String> startPaths, String endPath,String startEncoding, String endEncoding){
		if (startEncoding == null || endEncoding == null){
			return 0;
		}
		for (String startPath : startPaths) {
			recursion(startPath,endPath,startEncoding,endEncoding);
		}
		return 1;
	}

	/**
	 * 遍历文件实现拷贝
	 * @param startPath		需要转变编码的文件路径
	 * @param endPath		保存路径
	 * @param startEncoding	开始字符
	 * @param endEncoding	保存字符
	 */
	public static void recursion(String startPath, String endPath,String startEncoding, String endEncoding) {
		File file = new File(startPath);
		if (file.isDirectory()) {
			endPath = endPath + File.separator + file.getName();
			File createFile = new File(endPath);
			createFile.mkdir();
			String[] strArray = file.list();
			for (String str : strArray) {
				recursion(startPath + File.separator + str, endPath,startEncoding,endEncoding);
			}
		} else {
			endPath = endPath + File.separator + file.getName();
			copy(startPath, endPath,startEncoding,endEncoding);
		}
	}

	/**
	 * 拷贝文件
	 * @param startPath		需要转变编码的文件路径
	 * @param endPath		保存路径
	 * @param startEncoding	开始字符
	 * @param endEncoding	保存字符
	 */
	private static void copy(String startPath, String endPath,String startEncoding, String endEncoding) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(startPath), startEncoding));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(endPath), endEncoding));
			String str = null;
			while ((str = br.readLine()) != null) {
				bw.write(str);
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
