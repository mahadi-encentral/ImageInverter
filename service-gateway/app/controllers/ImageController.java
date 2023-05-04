package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamt4real.app.api.IUpload;
import com.mamt4real.app.model.Upload;
import models.Pixel;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Transactional
public class ImageController extends Controller {

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private IUpload iUpload;

    public Result uploadImage() throws IOException {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        if(body.getFiles().isEmpty())
            return notFound("No file submitted");

        final var upload = body.getFile("image");
        String contentType =upload.getContentType();
        if(!(contentType.contains("image"))){
            return badRequest("File is not an Image");
        }
        String extension = contentType.substring(contentType.lastIndexOf("/") + 1).toLowerCase();
        BufferedImage image = ImageIO.read(upload.getFile());
        String name = upload.getFilename().replaceAll("\\s", "_").toLowerCase();
        int width = image.getWidth();
        int height = image.getHeight();

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Pixel pixel = new Pixel(image.getRGB(i, j));
                pixel.invertPixel();
                image.setRGB(i, j, pixel.getPoint());
            }
        }

        String date_suffix = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String filepath = String.format("C:/Users/MAHADI/Desktop/uploads/%s_%s.%s",
                name.replaceAll("\\.", "_"),
                date_suffix.replaceAll("[:\\-\\.]", "_"), extension);
        File saved = new File(filepath);
        saved.mkdir();
        ImageIO.write(image, extension, upload.getFile());
        try {
            Files.createFile(saved.toPath());
            Files.move(upload.getFile().toPath(), saved.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException io){
            System.out.print(io);
            throw io;
        }
        Upload dbDoc = iUpload.addUpload(new Upload(saved.getPath()));
        return created("Image saved successfully with id: " + dbDoc.getId());
    }

    public Result getAllImages() throws JsonProcessingException {
        final var images = iUpload.getUploads();
        return ok(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(images));
    }

    public Result downloadImage(Long id){
        final var found = iUpload.getUpload(id);
        if(found.isEmpty()){
            return notFound("No Image saved with ID: " + id);
        }
        Upload upload = found.get();
        File image = new File(upload.getFilepath());
        response().setContentType("image");
        return ok(image);
    }
}
