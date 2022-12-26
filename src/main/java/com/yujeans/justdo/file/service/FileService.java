package com.yujeans.justdo.file.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yujeans.justdo.file.File;
import com.yujeans.justdo.file.FileType;
import com.yujeans.justdo.file.dto.FileDto;
import com.yujeans.justdo.file.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Service
public class FileService {
	private final FileRepository fileRepository;
//	private final FileStore fileStore;
	
	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	// 업로드한 파일에 대한 정보를 기록
	@Transactional
	public Long saveFile(FileDto fileDto) {
		return fileRepository.save(fileDto.toEntity()).getId();
	}
	
	// id 값을 사용하여 파일에 대한 정보를 가져옴(getFile())
	public FileDto getFile(Long id) {
		File file = fileRepository.findById(id).get();
		
		FileDto fileDto = FileDto.builder()
					.id(id)
					.originFilename(file.getOriginFilename())
					.storeFilename(file.getStoreFilename())
					.filePath(file.getFilePath())
					.build();
		
		return fileDto;
	}
	
	
	
	
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
