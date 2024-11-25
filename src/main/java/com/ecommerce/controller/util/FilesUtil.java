/**
 * 
 */
package com.ecommerce.controller.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.imageio.ImageIO;

import jakarta.servlet.http.Part;

/**
 * @author 04-14
 *
 */
public class FilesUtil {
	public static String processFile(Part filePart, String directory) throws IOException {
		String extension = ".jpg"; // get from image
		String filename = UUID.randomUUID().toString() + extension;
		InputStream fileContent = filePart.getInputStream();
		
		Path uploadPath = Paths.get(directory + File.separator + filename);
		Files.copy(fileContent, uploadPath, StandardCopyOption.REPLACE_EXISTING);
		generateThumbnail(uploadPath.toFile(), filename, directory);
		return filename;
	}
	
	public static void generateThumbnail(File originalImage, String filename, String directory) throws IOException {
		BufferedImage original = ImageIO.read(originalImage);
		int width = 150;
		int height= 150;
		BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = thumbnail.createGraphics();
		g.drawImage(original.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		g.dispose();
		
		File thumbnailFile = new File(directory + File.separator + "thumbnail", filename);
		ImageIO.write(thumbnail, "jpg", thumbnailFile);
	}
}
