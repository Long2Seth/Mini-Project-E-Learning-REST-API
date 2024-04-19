package co.istad.elearning.features.user.dto;

import lombok.Builder;

@Builder
public record RoleNameResponse(
    String name
) {
}
