package me.momarija.bioui.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class StorageService {

	private final Path rootLocation = Paths.get("upload-dir");

	public String store(MultipartFile file){
		try {
			String name= new Date().getTime()+"";
			Files.copy(file.getInputStream(), this.rootLocation.resolve(name));
			return name;
		} catch (Exception e) {
			throw new RuntimeException("Enregistrement de la photo a échouée, veuillez réessayer.");
		}
	}

	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else{
				throw new RuntimeException("La récuperation de la photo a échouée, veuillez réessayer.");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("La récuperation de la photo a échouée, verifier l'url.");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void deleteFile(String filename){
		if (filename.equals("photo1.jpg"))
			return;
		try {
			Files.deleteIfExists(rootLocation.resolve(filename));
		} catch (IOException e) {
			throw new RuntimeException("Operation échouée, impossible de supprimer la photo.");
		}
	}

	public void init() {
		try {
			if (!Files.isDirectory(rootLocation))
				Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize file storage!");
		}
	}

}
