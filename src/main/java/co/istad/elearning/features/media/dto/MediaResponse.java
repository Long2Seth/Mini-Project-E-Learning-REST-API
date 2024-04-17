package co.istad.elearning.features.media.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        String filename,
        String fullUrl ,
        String fileType,
        float size
) {
}
