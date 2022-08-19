package com.example.userbackend.service;

import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// folder uploads
// folder theo userId -> chứa file

@Service
public class FileService {
    // Chỉ định folder gốc để upload file
    private Path rootPath = Paths.get("uploads");

    public FileService() {
        createFolder(rootPath.toString());
    }

    // Tạo folder
    public void createFolder(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    public String uploadFile(int id, MultipartFile file) {
        // Tạo folder tương ứng với userId
        Path userPath  = rootPath.resolve(String.valueOf(id));
        createFolder(userPath.toString());

        // Validate file
        validateFile(file);

        // Tạo fileId
        String fileId = String.valueOf(System.currentTimeMillis());
        File serverFile = new File(userPath + "/" + fileId);

        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/" + id + "/files/" + fileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    // Validate file
    public void validateFile(MultipartFile file) {
        // Kiểm tra tên
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không được để trống");
        }

        // Kiểm tra extension
        // avatar.png -> png
        // image.jpg -> jpg
        String fileExtension = getExtensionFile(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new BadRequestException("File không hợp lệ");
        }

        // Kiểm tra size
        // Upload không vượt quá 3MB
        double size = (double) file.getSize() / 1_000_000;
        if(size > 3) {
            throw new BadRequestException("Kích thước file không được vượt quá 3MB");
        }
    }

    // Lấy đuôi file
    public String getExtensionFile(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    // Kiểm tra đuôi file
    public boolean checkFileExtension(String fileExtension) {
        List<String> extensions = new ArrayList<>(List.of("png", "jpg", "jpeg"));
        return extensions.contains(fileExtension);
    }


    public byte[] readFile(int id, String fileId) {
        // Lấy ra đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // Kiểm tra xem đường dẫn file có tồn tại hay không
        if(!Files.exists(userPath)) {
            throw new NotFoundException("Không thể đọc file " + fileId);
        }

        try {
            // Lấy ra đường dẫn file tương ứng với user_id và file_id
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                InputStream stream = resource.getInputStream();
                byte[] bytes = StreamUtils.copyToByteArray(stream);

                stream.close();
                return bytes;
            } else {
                throw new RuntimeException("Không thể đọc file " + fileId);
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file " + fileId);
        }
    }

    public List<String> getFiles(int id) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn file có tồn tại hay không
        // Nếu không tồn tại -> user chưa upload ảnh -> trả về danh sách rỗng
        if (!Files.exists(userPath)) {
            return new ArrayList<>();
        }

        // Lấy ds file trong folder
        List<File> files = List.of(userPath.toFile().listFiles());

        // Tra về ds đường dẫn với từng file
        List<String> filesPath = files
                .stream()
                .map(File::getName)
                .sorted(Comparator.reverseOrder())
                .map(file -> "/api/v1/users/" + id + "/files/" + file)
                .toList();

        return filesPath;
    }

    public void deleteFile(int id, String fileId) {
        // Lấy ra đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve(String.valueOf(id));

        // Kiểm tra folder chứa file có tồn tại hay không
        if(!Files.exists(userPath)) {
            throw new NotFoundException("File " + fileId + " không tồn tại");
        }

        // Lấy ra đường dẫn file tương ứng với user_id và file_id
        Path file = userPath.resolve(fileId);

        // Kiểm tra file có tồn tại hay không
        if(!file.toFile().exists()) {
            throw new NotFoundException("File " + fileId + " không tồn tại");
        }

        // Tiến hành xóa file
        file.toFile().delete();
    }
}
