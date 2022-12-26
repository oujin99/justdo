package com.yujeans.justdo.images.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yujeans.justdo.images.Images;
import com.yujeans.justdo.images.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
//	public FileService(FileRepository fileRepository) {
//		this.fileRepository = fileRepository;
//	}
	
	public Long saveFile(MultipartFile imageFile) {
		if(imageFile.isEmpty()) {
			return null;
		}
		
		// 원래 파일 ㅇ이름 추출
		String origName = imageFile.getOriginalFilename();
		
		// 파일 이름으로 쓸 uuid 생성
		String uuid = UUID.randomUUID().toString();
		
		// 확장자 추출(ex : .png)
		String extension = origName.substring(origName.lastIndexOf("."));
		
		// uuid와 확장자 결합
		String savedName = uuid + extension;
		
		// 파일을 불러올 때 사용할 파일 경로
	    String userDirectory = System.getProperty("user.dir");
	    userDirectory += "\\src\\main\\resources\\static\\dogether\\";
	    String savedPath = userDirectory + savedName;
	    System.out.println("userDirectory : " + userDirectory);
	    
		// 파일 엔티티 생성
		Images image = Images.builder()
				.orgNm(origName)
				.savedNm(savedName)
				.savedPath(savedPath)
				.build();
		
		// 실제로 로컬에 uuid를 파일명으로 저장
		try {
			imageFile.transferTo(new File(savedPath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 데이터베이스에 파일 정보 저장
		Images savedFile = imageRepository.save(image);
		
		return savedFile.getId();
	}
	
	public Images getFileInfo(Long id) {
		
		return imageRepository.getFileInfo(id);
	}
	
//	// id 값을 사용하여 파일에 대한 정보를 가져옴(getFile())
//	public FileDto getFile(Long id) {
//		File file = fileRepository.findById(id).get();
//		
//		FileDto fileDto = FileDto.builder()
//					.id(id)
//					.originFilename(file.getOriginFilename())
//					.storeFilename(file.getStoreFilename())
//					.filePath(file.getFilePath())
//					.build();
//		
//		return fileDto;
//	}
	
	
	
	
	//----------------------------------------------
//	public List<File> saveFiles(Map<FileType, List<MultipartFile>>
//			multipartFileListMap) throws IOException{
//		
//		
//		List<File> imageFiles = fileStore.storeFiles(multipartFileListMap.get(FileType.IMAGE), FileType.IMAGE);
//		List<File> generalFiles = fileStore.storeFiles(multipartFileListMap
//				.get(FileType.GENERAL), FileType.GENERAL);
//		List<File> result = Stream.of(imageFiles, generalFiles)
//							.flatMap(f -> f.stream())
//							.collect(Collectors.toList());
//		
//		return result;
//		
//	}
}
