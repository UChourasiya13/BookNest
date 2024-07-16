package com.api.book.helper;


import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelp {
		
	public final String UPLOAD_DIR = "E:\\Spring_Workspace\\D-RestAPI"
			+ "\\src\\main\\resources\\static\\image";

	public boolean uploadFile(MultipartFile file)
	{
		boolean status = false;
		
		try
		{
			
			InputStream istream = file.getInputStream();
			
			byte data[] = new byte[istream.available()];
			istream.read(data);
//			
			FileOutputStream ostream = new FileOutputStream(UPLOAD_DIR+"//"
										+file.getOriginalFilename());
//			
			ostream.write(data);
			ostream.flush();
			istream.close();
			status = true;
			
//			Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//			
			
		}catch(Exception e)
		{
			
		}
		
		return status;
	}
}
