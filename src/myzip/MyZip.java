package myzip;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;

public class MyZip {

	public static void zip(File file, ZipOutputStream zip) throws Exception {
		MyZip.folderZip(file, zip, file.getName());
	}

	private static void folderZip(File file, ZipOutputStream zip, String fileName) throws Exception {
		if (file.isDirectory()) {// 目录
			File[] fileList = file.listFiles();
			if (fileList == null || fileList.length == 0) {// 空目录
				zip.putNextEntry(new ZipEntry(fileName + File.separator));
//				zip.closeEntry();//可省略，因为putNextEntry和getNextEntry方法都会检查当前entry是否为空，不为空时会自动close
			} else {
				for (File f : fileList) {
					MyZip.folderZip(f, zip, fileName + File.separator + f.getName());
//					if (f.isDirectory()) {// 子目录
//						MyZip.folderZip(f, zip, fileName + File.separator + f.getName());
//					} else {// 子文件
//						MyZip.folderZip(f, zip, fileName + File.separator + f.getName());
//					}
				}
			}
		} else {// 文件
			System.out.println(fileName);
			FileInputStream in = new FileInputStream(file);
			zip.putNextEntry(new ZipEntry(fileName));
			int temp = 0;
			while ((temp = in.read()) != -1) {
				zip.write(temp);
			}
			in.close();
//			zip.closeEntry();//可省略，因为putNextEntry和getNextEntry方法都会检查当前entry是否为空，不为空时会自动close
		}
	}

	public static void main(String[] args) throws Exception {
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(new File("d:/temp/a.zip")));
		MyZip.zip(new File("d:/temp/a"), zip);
		zip.close();
//		File file = new File("d:/temp/f");
//		System.out.println(file.getName());
	}

}
