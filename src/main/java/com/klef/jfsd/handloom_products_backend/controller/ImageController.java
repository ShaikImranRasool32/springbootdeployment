//package com.klef.jfsd.handloom_products_backend.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;

//
//import com.klef.jfsd.handloom_products_backend.model.Image;
//import com.klef.jfsd.handloom_products_backend.repository.ImageRepository;
//import com.klef.jfsd.handloom_products_backend.service.ImageService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/seller/images")
//public class ImageController {
//
//    private final Path rootLocation = Paths.get("uploads");
//
//    @Autowired
//    private ImageRepository imageRepository;
//    @Autowired
//    private ImageService imageService;
//   
//    @PostMapping("/upload")
//    public ResponseEntity<String> handleFileUpload(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("sellerGmail") String sellerGmail,
//            @RequestParam("itemName") String itemName,
//            @RequestParam("itemPrice") Double itemPrice,
//            @RequestParam("itemSummary") String itemSummary) {
//        try {
//            if (!Files.exists(rootLocation)) {
//                Files.createDirectories(rootLocation);
//            }
//
//            Path destinationFile = rootLocation.resolve(Paths.get(file.getOriginalFilename()))
//                    .normalize().toAbsolutePath();
//            file.transferTo(destinationFile);
//
//            Image image = new Image();
//            image.setName(file.getOriginalFilename());
//            image.setFilePath(destinationFile.toString());
//            image.setSellerGmail(sellerGmail);
//            image.setItemName(itemName);
//            image.setItemPrice(itemPrice);
//            image.setItemSummary(itemSummary);
//
//            imageRepository.save(image);
//
//            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
//        }
//    }
//
//    // Retrieve all images for a specific seller
//    @GetMapping("/seller/{sellerGmail}")
//    public ResponseEntity<?> getImagesBySeller(@PathVariable String sellerGmail) {
//        return ResponseEntity.ok(imageRepository.findBySellerGmail(sellerGmail));
//    }
//
//    // Serve image by name
//    @GetMapping("/{imageName}")
//    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
//        File file = new File("uploads/" + imageName);
//        if (file.exists()) {
//            String extension = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
//            MediaType mediaType = MediaType.IMAGE_JPEG; // Default fallback
//
//            // Set appropriate media type based on file extension
//            if (extension.equals("png")) {
//                mediaType = MediaType.IMAGE_PNG;
//            } else if (extension.equals("gif")) {
//                mediaType = MediaType.IMAGE_GIF;
//            }
//
//            Resource resource = new FileSystemResource(file);
//            return ResponseEntity.ok()
//                    .contentType(mediaType)
//                    .body(resource);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Update image details
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateImage(
//            @PathVariable Long id,
//            @RequestBody Image updatedImage) {
//        Optional<Image> existingImage = imageRepository.findById(id);
//
//if (existingImage.isPresent()) {
//            Image image = existingImage.get();
//            image.setItemName(updatedImage.getItemName());
//            image.setItemPrice(updatedImage.getItemPrice());
//            image.setItemSummary(updatedImage.getItemSummary());
//            imageRepository.save(image);
//            return ResponseEntity.ok("Image updated successfully");
//        } else {
//            return ResponseEntity.status(404).body("Image not found");
//        }
//    }
//
//    // Delete an image
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
//        Optional<Image> existingImage = imageRepository.findById(id);
//
//        if (existingImage.isPresent()) {
//            imageRepository.deleteById(id);
//            return ResponseEntity.ok("Image deleted successfully");
//        } else {
//            return ResponseEntity.status(404).body("Image not found");
//        }
//    }
////    @GetMapping("/api/products")
////    public List<Image> getAllImages() {
////        return imageService.getAllImages();
////    }
//  @GetMapping("/all")
//  public List<Image> getAllImages() {
//      return imageService.getAllImages();
//  }
//
//}
//package com.klef.jfsd.handloom_products_backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.klef.jfsd.handloom_products_backend.model.Image;
//import com.klef.jfsd.handloom_products_backend.service.ImageService;
//
//import java.io.File;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/seller/images")
//public class ImageController {
//
//    @Autowired
//    private ImageService imageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("sellerGmail") String sellerGmail,
//            @RequestParam("itemName") String itemName,
//            @RequestParam("itemPrice") Double itemPrice,
//            @RequestParam("itemSummary") String itemSummary) {
//        try {
//            String filePath = imageService.saveImage(file);
//
//            Image image = new Image();
//            image.setName(file.getOriginalFilename());
////            image.setFilePath(filePath);
//            image.setFilePath("/uploads/" + file.getOriginalFilename()); // Save only the relative path
//
//            image.setSellerGmail(sellerGmail);
//            image.setItemName(itemName);
//            image.setItemPrice(itemPrice);
//            image.setItemSummary(itemSummary);
//
//            imageService.saveImageDetails(image);
//
//            return ResponseEntity.ok("Image uploaded successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Image upload failed: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/seller/{sellerGmail}")
//    public ResponseEntity<List<Image>> getImagesBySeller(@PathVariable String sellerGmail) {
//        List<Image> images = imageService.getImagesBySeller(sellerGmail);
//        return ResponseEntity.ok(images);
//    }
//
//    @GetMapping("/uploads/{imageName}")
//  public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
//      File file = new File("uploads/" + imageName);
//      if (file.exists()) {
//          String extension = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
//          MediaType mediaType = MediaType.IMAGE_JPEG; // Default fallback
//
//          // Set appropriate media type based on file extension
//          if (extension.equals("png")) {
//              mediaType = MediaType.IMAGE_PNG;
//          } else if (extension.equals("gif")) {
//              mediaType = MediaType.IMAGE_GIF;
//          }
//
//          Resource resource = new FileSystemResource(file);
//          return ResponseEntity.ok()
//                  .contentType(mediaType)
//                  .body(resource);
//      } else {
//          return ResponseEntity.notFound().build();
//      }
//  }
////    @GetMapping("/{imageName}")
////  public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
////      File file = new File("uploads/" + imageName);
////      if (file.exists()) {
////          String extension = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
////          MediaType mediaType = MediaType.IMAGE_JPEG; // Default fallback
////
////          // Set appropriate media type based on file extension
////          if (extension.equals("png")) {
////              mediaType = MediaType.IMAGE_PNG;
////          } else if (extension.equals("gif")) {
////              mediaType = MediaType.IMAGE_GIF;
////          }
////
////          Resource resource = new FileSystemResource(file);
////          return ResponseEntity.ok()
////                  .contentType(mediaType)
////                  .body(resource);
////      } else {
////          return ResponseEntity.notFound().build();
////      }
////  }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateImageDetails(
//            @PathVariable Long id,
//            @RequestBody Image updatedImage) {
//        if (imageService.updateImageDetails(id, updatedImage)) {
//            return ResponseEntity.ok("Image updated successfully");
//        } else {
//            return ResponseEntity.status(404).body("Image not found");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
//        try {
//            imageService.deleteImage(id);
//            return ResponseEntity.ok("Image deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body("Image not found");
//        }
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Image>> getAllImages() {
//        return ResponseEntity.ok(imageService.getAllImages());
//    }
//}
package com.klef.jfsd.handloom_products_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.klef.jfsd.handloom_products_backend.model.Image;
import com.klef.jfsd.handloom_products_backend.repository.ImageRepository;
import com.klef.jfsd.handloom_products_backend.service.ImageService;



@RestController
@RequestMapping("/api/seller/images")
public class ImageController {

    private final Path rootLocation = Paths.get("uploads");

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("sellerGmail") String sellerGmail,
            @RequestParam("itemName") String itemName,
            @RequestParam("itemPrice") Double itemPrice,
            @RequestParam("itemSummary") String itemSummary) {
        try {
            String relativeFilePath = imageService.saveImage(file);

            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setFilePath(relativeFilePath); // Save relative path
            image.setSellerGmail(sellerGmail);
            image.setItemName(itemName);
            image.setItemPrice(itemPrice);
            image.setItemSummary(itemSummary);

            imageRepository.save(image);

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    // @GetMapping("/{imageName}")
    // public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
    //     try {
    //         Path filePath = rootLocation.resolve(imageName).normalize();
    //         Resource resource = new UrlResource(filePath.toUri());

    //         if (resource.exists() && resource.isReadable()) {
    //             String contentType = Files.probeContentType(filePath);
    //             MediaType mediaType = contentType != null ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM;

    //             return ResponseEntity.ok()
    //                     .contentType(mediaType)
    //                     .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
    //                     .body(resource);
    //         } else {
    //             return ResponseEntity.notFound().build();
    //         }
    //     } catch (IOException e) {
    //         return ResponseEntity.status(500).body(null);
    //     }
    // }
    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            Path filePath = rootLocation.resolve(imageName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                MediaType mediaType = contentType != null ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM;

                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
   

    @GetMapping("/images/{imageName}")
public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
    try {
        Path filePath = rootLocation.resolve(imageName).normalize();
        System.out.println("Requested file path: " + filePath); // Log the path
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            String contentType = Files.probeContentType(filePath);
            MediaType mediaType = contentType != null ? MediaType.parseMediaType(contentType) : MediaType.APPLICATION_OCTET_STREAM;
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (IOException e) {
        return ResponseEntity.status(500).body(null);
    }
}

    @GetMapping("/all")
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @GetMapping("/seller/{sellerGmail}")
    public ResponseEntity<List<Image>> getImagesBySeller(@PathVariable String sellerGmail) {
        return ResponseEntity.ok(imageService.getImagesBySeller(sellerGmail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestBody Image updatedImage) {
        if (imageService.updateImageDetails(id, updatedImage)) {
            return ResponseEntity.ok("Image updated successfully");
        } else {
            return ResponseEntity.status(404).body("Image not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error deleting image: " + e.getMessage());
        }
    }
}

