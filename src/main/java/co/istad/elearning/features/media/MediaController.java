package co.istad.elearning.features.media;

import co.istad.elearning.features.media.dto.MediaResponse;
import co.istad.elearning.util.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/uploadImage")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<MediaResponse> uploadSingleFile(
            @RequestPart("file") MultipartFile file, HttpServletRequest request
    ) {
        return BaseResponse
                .<MediaResponse>createSuccess()
                .setPayload(mediaService.uploadSingleFile(file, request));
    }
}
