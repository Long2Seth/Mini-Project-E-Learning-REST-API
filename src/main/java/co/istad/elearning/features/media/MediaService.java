package co.istad.elearning.features.media;

import co.istad.elearning.features.media.dto.MediaResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    MediaResponse uploadSingleFile(MultipartFile file, HttpServletRequest request);
}
