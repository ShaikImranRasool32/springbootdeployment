//package com.klef.jfsd.handloom_products_backend.service;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.klef.jfsd.handloom_products_backend.model.Image;
//import com.klef.jfsd.handloom_products_backend.repository.ImageRepository;
//
//@Service
//public class ImageService {
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    private final Path rootLocation = Paths.get("uploads"); // Directory where files are stored
//
//    public String saveImage(MultipartFile file) throws IOException {
//        // Ensure the upload directory exists
//        Files.createDirectories(rootLocation);
//
//        // Define the path where the file will be stored
//        String fileName = file.getOriginalFilename();
//        Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
//
//        // Save the file to the file system
//        file.transferTo(destinationFile);
//
//        // Return the file path which will be stored in the database
//        return destinationFile.toString();
//    }
//
//    public void saveImagePath(String filePath) {
//        // Create a new Image object and save the file path in the database
//        Image image = new Image();
//        image.setFilePath(filePath);
//        imageRepository.save(image);
//    }
//
//    public String getImagePath(Long id) {
//        // Fetch the file path from the database
//        Optional<Image> image = imageRepository.findById(id);
//        return image.map(Image::getFilePath).orElse(null);
//    }
//
//    public void deleteImage(Long id) throws IOException {
//        // Delete image both from the database and the file system
//        Optional<Image> imageOptional = imageRepository.findById(id);
//        if (imageOptional.isPresent()) {
//            String filePath = imageOptional.get().getFilePath();
//            Path path = rootLocation.resolve(filePath).normalize();
//            Files.deleteIfExists(path);
//            imageRepository.deleteById(id);
//        } else {
//            throw new IOException("Image not found");
//        }
//    }
//    public List<Image> getAllImages() {
//        return imageRepository.findAll();
//    }
//}
package com.klef.jfsd.handloom_products_backend.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.klef.jfsd.handloom_products_backend.model.Image;
//import com.klef.jfsd.handloom_products_backend.repository.ImageRepository;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ImageService {
//
//    private final Path rootLocation = Paths.get("uploads");
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    public String saveImage(MultipartFile file) throws IOException {
//        if (!Files.exists(rootLocation)) {
//            Files.createDirectories(rootLocation);
//        }
//
//        String fileName = file.getOriginalFilename();
//        Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
//        file.transferTo(destinationFile);
//
//        return "/uploads/" + fileName; // Return relative path
//    }
//
//    public void saveImageDetails(Image image) {
//        imageRepository.save(image);
//    }
//
//   
//
//    public List<Image> getImagesBySeller(String sellerGmail) {
//        return imageRepository.findBySellerGmail(sellerGmail);
//    }
//
//    public boolean updateImageDetails(Long id, Image updatedImage) {
//        Optional<Image> existingImage = imageRepository.findById(id);
//        if (existingImage.isPresent()) {
//            Image image = existingImage.get();
//            image.setItemName(updatedImage.getItemName());
//            image.setItemPrice(updatedImage.getItemPrice());
//            image.setItemSummary(updatedImage.getItemSummary());
//            imageRepository.save(image);
//            return true;
//        }
//        return false;
//    }
//
//    public void deleteImage(Long id) throws IOException {
//        Optional<Image> imageOptional = imageRepository.findById(id);
//        if (imageOptional.isPresent()) {
//            String filePath = imageOptional.get().getFilePath();
//            Files.deleteIfExists(Paths.get("uploads", filePath));
//            imageRepository.deleteById(id);
//        } else {
//            throw new IOException("Image not found");
//        }
//    }
//
//    public List<Image> getAllImages() {
//        return imageRepository.findAll();
//    }
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.handloom_products_backend.model.Image;
import com.klef.jfsd.handloom_products_backend.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Service
public class ImageService {

    private final Path rootLocation = Paths.get("uploads");

    @Autowired
    private ImageRepository imageRepository;

    public String saveImage(MultipartFile file) throws IOException {
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        String fileName = file.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
        file.transferTo(destinationFile);

        return "/uploads/" + fileName; // Return relative path
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<Image> getImagesBySeller(String sellerGmail) {
        return imageRepository.findBySellerGmail(sellerGmail);
    }

    public boolean updateImageDetails(Long id, Image updatedImage) {
        Optional<Image> existingImage = imageRepository.findById(id);
        if (existingImage.isPresent()) {
            Image image = existingImage.get();
            image.setItemName(updatedImage.getItemName());
            image.setItemPrice(updatedImage.getItemPrice());
            image.setItemSummary(updatedImage.getItemSummary());
            imageRepository.save(image);
            return true;
        }
        return false;
    }

    public void deleteImage(Long id) throws IOException {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            Path filePath = rootLocation.resolve(Paths.get(image.getName())).normalize();
            Files.deleteIfExists(filePath);
            imageRepository.deleteById(id);
        } else {
            throw new IOException("Image not found");
        }
    }
}
