package myzip;

import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.OutputStream;

public class MyUnzip {//空目录作为叶节点时，会被处理成文件

	public static void unzip(String zipFrom, String fileTo) throws Exception {
		ZipInputStream zipStream = new ZipInputStream(new FileInputStream(new File(zipFrom)));
		ZipFile zipFile = new ZipFile(new File(zipFrom));
		InputStream in = null;
		OutputStream out = null;
		File file = null;
		for (ZipEntry entry = zipStream.getNextEntry(); entry != null; entry = zipStream.getNextEntry()) {
			file = new File(fileTo + entry.getName());
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			} // 单独创建父目录，方便下面可以使用OutputStream自动创建最低一级的文件
			
			//  上一步单独创建父目录是为了这一步区分开文件和目录
//			if (!file.exists()) {
//				if (entry.isDirectory()) {// 根据压缩文件中条目的类别，来决定是创建目录还是文件
//					file.mkdir();
//				} else {
//					file.createNewFile();// 创建输出目标文件
//				}
//			}
			
			if (!entry.isDirectory()) {
				in = zipFile.getInputStream(entry);
				out = new FileOutputStream(file);// OutputStream自带创建文件功能，但只能创建一级文件，即该文件的父目录需存在
				int temp;
				while ((temp = in.read()) != -1) {
					out.write(temp);
				}
				in.close();
				out.close();
			}
		}
		zipStream.close();
	}

	public static void main(String[] args) throws Exception {
		MyUnzip.unzip("d:/temp/a.zip", "d:/temp/");
//		ZipInputStream zip = new ZipInputStream(new FileInputStream(new File("d:/temp/d.zip")));
//		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
////			if(entry.isDirectory())
//				System.out.println(entry.getName());
//		}
		System.out.println("在线更新");
		System.out.println("本地库已更新");
	}

}
