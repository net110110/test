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

public class MyUnzip {//��Ŀ¼��ΪҶ�ڵ�ʱ���ᱻ������ļ�

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
			} // ����������Ŀ¼�������������ʹ��OutputStream�Զ��������һ�����ļ�
			
			//  ��һ������������Ŀ¼��Ϊ����һ�����ֿ��ļ���Ŀ¼
//			if (!file.exists()) {
//				if (entry.isDirectory()) {// ����ѹ���ļ�����Ŀ������������Ǵ���Ŀ¼�����ļ�
//					file.mkdir();
//				} else {
//					file.createNewFile();// �������Ŀ���ļ�
//				}
//			}
			
			if (!entry.isDirectory()) {
				in = zipFile.getInputStream(entry);
				out = new FileOutputStream(file);// OutputStream�Դ������ļ����ܣ���ֻ�ܴ���һ���ļ��������ļ��ĸ�Ŀ¼�����
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
		System.out.println("����");
	}

}
