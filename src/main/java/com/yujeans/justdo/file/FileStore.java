//package com.yujeans.justdo.file;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileStore {
//
//	// 파일 경로, properties에 파일을 실제로 저장해둘 경로 지정
//	@Value("${file.dir}/")
//	private String fileDirPath;
//
//	// 파일을 저장하는 데 있어서 파일의 확장자를 붙여 저장하면 파일을 구분하는데 도움이 됨
//	// : 파일의 확장자를 추출하는 메소드 구성
//	private String extractExt(String originalFilename) {
//		int idx = originalFilename.lastIndexOf(".");
//		String ext = originalFilename.substring(idx);
//		return ext;
//	}
//	
//	// 저장할 파일 이름 구성
//	private String createStoreFilename(String originalFilename) {
//		
//		// 파일 이름은 겹칠 수 있기 때문에 UUID를 통해 설정
//		String uuid = UUID.randomUUID().toString();
//		String ext = extractExt(originalFilename);
//		String storeFilename = uuid + ext;
//		
//		return storeFilename;
//	}
//	
//	// 파일 경로 구성
//	// 파일을 실제로 저장하기 위해 경로를 구성하는 부분
//	// 파일을 저장하는데 있어서 이미지 파일을 저장할 경로와 일반적인 파일을 저장할 경로를 구분하기 위해
//	// FileType을 파라미터로 입력받아 파일의 경로를 설정
//	public String createPath(String storeFilename, FileType fileType) {
//		String viaPath = (fileType == FileType.IMAGE) ? "images/" : "generals/";
//		
//		return fileDirPath+viaPath+storeFilename;
//	}
//	
//	// 파일 저장 로직(실제로 파일을 저장하는 부분)
//	public File storFile(MultipartFile multipartFile, FileType fileType) throws IOException{
//		if(multipartFile.isEmpty()) {
//			return null;
//		}
//		
//		//multipartFile의 getOriginalFilename()메소드를 활용하여 이름 추출
//		// 추후 파일을 불러오기 위해 원본 파일의 이름과 저장 파일의 이름과 타입을 통해 File을 구성하여 반환
//		String originalFilename = multipartFile.getOriginalFilename();
//		String storeFilename = createStoreFilename(originalFilename);
//		multipartFile.transferTo(new java.io.File(createPath(storeFilename, fileType)));
//		
//		return File.builder()
//				.originFilename(originalFilename)
//				.storeFilename(storeFilename)
//				.fileType(fileType)
//				.build();
//		
//	}
//	
//	// 전체 파일 저장, 입력받은 수 많은 multipartFile들에 대해 storeFile 메소드를 수행하면서 파일 저장 진행
//	// 반환 받은 attachment를 list로 구성하여 반환
//	public List<File> storeFiles(List<MultipartFile> multipartFiles, FileType fileType)
//		throws IOException{
//		List<File> files = new ArrayList<File>();
//		for(MultipartFile multipartFile : multipartFiles) {
//			if(!multipartFile.isEmpty()) {
//				files.add(storFile(multipartFile, fileType));
//			}
//		}
//		
//		return files;
//		
//	}
//}
