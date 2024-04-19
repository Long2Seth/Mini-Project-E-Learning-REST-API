package co.istad.elearning.features.course.dto;


public record CourseResponse(
        String alias,
        String title,
        String description,
        Boolean isDeleted,
        Boolean  isFree,
        String thumbnail

) {
}
