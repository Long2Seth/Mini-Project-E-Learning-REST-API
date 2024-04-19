package co.istad.elearning.features.course.dto;


public record CourseDetailsResponse(
        String alias,
        String description,
        Boolean isDeleted,
        Boolean isFree,
        String thumbnail,
        String title
//        CategorySnippingResponse category,
//        InstructorSnippingResponse instructor

) {
}
